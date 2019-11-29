package com.example.iedmaps;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class ledControl extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btnDis;
    String address = null;
    String address2 = null;
    TextView lumn;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    static BluetoothSocket btSocket = null;
    BluetoothSocket btSocket2 = null;

    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        text = (TextView) findViewById(R.id.txt);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS);
        address2 = newint.getStringExtra("EXTRA_ADDRESS2");

        setContentView(R.layout.activity_led_control);

        btn1 = (Button) findViewById(R.id.button2);
        btn2 = (Button) findViewById(R.id.button3);
        btn3 = (Button) findViewById(R.id.button5);
        btn4 = (Button) findViewById(R.id.button6);
        btn5 = (Button) findViewById(R.id.button7);
        btnDis = (Button) findViewById(R.id.button4);
        lumn = (TextView) findViewById(R.id.textView2);

        new ConnectBT().execute();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendSignal("1");
                sendSignal2("2");
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Disconnect();
            }
        });

        byte[] buffer = new byte[256];
        int bytes;
//
        InputStream tempIn = null;
        if ( btSocket != null ) {
            try {
                tempIn = btSocket.getInputStream();

                DataInputStream mmIS = new DataInputStream(tempIn);

                bytes = mmIS.read(buffer);
                String readMessage = new String(buffer, 0, bytes);

                text.setText(readMessage);
            } catch (IOException e) {
                msg("Error");}
        }
    }

    public static void sendSignal ( String number ) {
        if ( btSocket != null ) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());
            } catch (IOException e) {

            }
        }
    }

    private void sendSignal2 ( String number ) {
        if ( btSocket != null ) {
            try {
                btSocket2.getOutputStream().write(number.toString().getBytes());
            } catch (IOException e) {

            }
        }
    }

    private void receive () {
        byte[] buffer = new byte[256];
        int bytes;

        InputStream tempIn = null;
        if ( btSocket != null ) {
            try {
                tempIn = btSocket.getInputStream();

                DataInputStream mmIS = new DataInputStream(tempIn);

                bytes = mmIS.read(buffer);
                String readMessage = new String(buffer, 0, bytes);

                text.setText(readMessage);
            } catch (IOException e) {
                msg("Error");}
            }
        }

    private void Disconnect () {
        if ( btSocket!=null ) {
            try {
                btSocket.close();
                btSocket2.close();
            } catch(IOException e) {
//                msg("Error");
            }
        }

        finish();
    }

    private void msg (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(ledControl.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground (Void... devices) {
            try {
                if ( btSocket==null || !isBtConnected ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    BluetoothDevice dispositivo2 = myBluetooth.getRemoteDevice(address2);

                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    btSocket2 = dispositivo2.createInsecureRfcommSocketToServiceRecord(myUUID);

                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                    btSocket2.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected");
                isBtConnected = true;
            }

            progress.dismiss();
        }
    }

//    public void onReceive(Context context, Intent intent) {
//
//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//
//        //Toast.makeText(context, "SOMETHING", Toast.LENGTH_SHORT).show();
//
//        try {
//
////            System.out.println("Receiver start");
////            Toast.makeText(context, " Receiver start ", Toast.LENGTH_SHORT).show();
//            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
//                Toast.makeText(context,"Ringing State Number is -"+incomingNumber,Toast.LENGTH_SHORT).show();
//            }
//            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
//                Toast.makeText(context,"Received State",Toast.LENGTH_SHORT).show();
//            }
//            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
////                Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
}
