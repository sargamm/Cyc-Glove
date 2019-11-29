package com.example.iedmaps;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;
import java.util.UUID;

public class DeviceList extends AppCompatActivity {

    Button btnPaired;
    ListView devicelist;
    private ProgressDialog progress;

    String address = null;
    String address2 = null;
    String address3 = null;


    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";

    BluetoothSocket btSocket = null;
    BluetoothSocket btSocket2 = null;
    BluetoothSocket btSocket3 = null;

    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(DeviceList.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(DeviceList.this, Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(DeviceList.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                ActivityCompat.requestPermissions(DeviceList.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }
        } else {
            //do nothing
        }

        setContentView(R.layout.activity_device_list);

        btnPaired = (Button) findViewById(R.id.button);
        devicelist = (ListView) findViewById(R.id.listView);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if ( myBluetooth==null ) {
            Toast.makeText(getApplicationContext(), "Bluetooth device not available", Toast.LENGTH_LONG).show();
            finish();
        } else if ( !myBluetooth.isEnabled() ) {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = "00:21:13:00:12:C1";
//                address2 = "00:21:13:00:3A:7D";
//                address3 = "34:05:0B:AC:8B:8C";
                address2 = "00:18:E4:40:00:06";

//                new ConnectBT().execute();

                Intent i = new Intent(DeviceList.this, MainActivity.class);
                i.putExtra(EXTRA_ADDRESS, address);
                i.putExtra("EXTRA_ADDRESS2", address2);
//                i.putExtra("EXTRA_ADDRESS3", address3);

                startActivity(i);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(DeviceList.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    Toast.makeText(this, "NO permission granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

//    private void Disconnect () {
//        if ( btSocket!=null ) {
//            try {
//                btSocket.close();
//                btSocket2.close();
//            } catch(IOException e) {
//                msg("Error");
//            }
//        }
//
//        finish();
//    }
//
//    private void msg (String s) {
//        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//    }
//
//    private class ConnectBT extends AsyncTask<Void, Void, Void> {
//        private boolean ConnectSuccess = true;
//
//        @Override
//        protected  void onPreExecute () {
//            progress = ProgressDialog.show(DeviceList.this, "Connecting...", "Please Wait!!!");
//        }
//
//        @Override
//        protected Void doInBackground (Void... devices) {
//            try {
//                if ( btSocket==null || !isBtConnected ) {
//                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
//                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
//                    BluetoothDevice dispositivo2 = myBluetooth.getRemoteDevice(address2);
//
//                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
//                    btSocket2 = dispositivo2.createInsecureRfcommSocketToServiceRecord(myUUID);
//
//                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
//                    btSocket.connect();
//                    btSocket2.connect();
//                }
//            } catch (IOException e) {
//                ConnectSuccess = false;
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute (Void result) {
//            super.onPostExecute(result);
//
//            if (!ConnectSuccess) {
//                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
//                finish();
//            } else {
//                msg("Connected");
//                isBtConnected = true;
//            }
//
//            progress.dismiss();
//        }
//    }

//    private void pairedDevicesList () {
//        pairedDevices = myBluetooth.getBondedDevices();
//        ArrayList list = new ArrayList();
//
//        if ( pairedDevices.size() > 0 ) {
//            for ( BluetoothDevice bt : pairedDevices ) {
//                list.add(bt.getName().toString() + "\n" + bt.getAddress().toString());
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found", Toast.LENGTH_LONG).show();
//        }
//
//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
//        devicelist.setAdapter(adapter);
//        devicelist.setOnItemClickListener(myListClickListener);
//    }

//    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String info = ((TextView) view).getText().toString();
//            String address = info.substring(info.length()-17);
////            Toast.makeText(DeviceList.this, address, Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(DeviceList.this, ledControl.class);
//            i.putExtra(EXTRA_ADDRESS, address);
//            startActivity(i);
//        }
//    };
}
