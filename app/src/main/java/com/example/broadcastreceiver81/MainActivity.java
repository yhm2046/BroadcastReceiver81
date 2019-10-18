package com.example.broadcastreceiver81;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.joor.Reflect;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private static final String BROADCAST_PERMISSION_DISC
            ="com.example.broadcastreceiver81.permissions.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC
            ="android.intent.action.gmt.logupload";;

    private MyReceiver receiver=new MyReceiver();
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//静态广播显示声明
        Intent intent=new Intent(this,MyService.class);
//        intent.addFlags(0x01000000);
        int includeBackground = Reflect.on(Intent.class).field("FLAG_RECEIVER_INCLUDE_BACKGROUND").get();
        intent.setFlags(intent.getFlags()| includeBackground);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //发送隐式广播
    private static void sendImplicitBroadcast(Context ctxt, Intent i) {
        PackageManager pm=ctxt.getPackageManager();
        List<ResolveInfo> matches=pm.queryBroadcastReceivers(i, 0);

        for (ResolveInfo resolveInfo : matches) {
            Intent explicit=new Intent(i);
            ComponentName cn=
                    new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName,
                            resolveInfo.activityInfo.name);

            explicit.setComponent(cn);
            ctxt.sendBroadcast(explicit);
        }
    }
}
