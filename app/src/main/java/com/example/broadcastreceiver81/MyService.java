package com.example.broadcastreceiver81;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String TAG="wangp";
    private MyReceiver receiver=new MyReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"定时6小时执行===================");
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.intent.action.gmt.logupload");
        registerReceiver(receiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
