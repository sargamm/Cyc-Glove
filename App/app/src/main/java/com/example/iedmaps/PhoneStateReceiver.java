package com.example.iedmaps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class PhoneStateReceiver extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        //Toast.makeText(context, "SOMETHING", Toast.LENGTH_SHORT).show();

        try {

//            System.out.println("Receiver start");
            //Toast.makeText(context, " Receiver start ", Toast.LENGTH_SHORT).show();
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"Ringing State Number is -" + incomingNumber, Toast.LENGTH_SHORT).show();
                MainActivity.sendSignal("5");
                MainActivity.sendSignal2("5");
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                Toast.makeText(context,"Received State", Toast.LENGTH_SHORT).show();
//                MainActivity.sendSignal("3");

            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Idle State", Toast.LENGTH_SHORT).show();
//                MainActivity.sendSignal("4");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void sendSignal ( String number ) {
//        if ( btSocket != null ) {
//            try {
//                btSocket.getOutputStream().write(number.toString().getBytes());
//            } catch (IOException e) {
////do nothing
//            }
//        }
//    }
//


}