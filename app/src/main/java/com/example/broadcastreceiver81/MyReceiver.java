package com.example.broadcastreceiver81;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String BOAD= "android.intent.action.gmt.logupload";
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(action.equals(BOAD)){
            Log.i("wangp","=================receiver BOAD======");
        }if(action.equals(ACTION_BOOT)){
            Log.i("wangp","========receiver bootservice, start back service======");
//            Intent intent=new Intent(this,MyService.class);
//            startService(intent);
        }
    }
}
