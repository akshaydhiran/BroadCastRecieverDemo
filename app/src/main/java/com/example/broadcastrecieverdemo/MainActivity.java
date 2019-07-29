package com.example.broadcastrecieverdemo;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new MyReceiver();

        sendBroadcast(new Intent("com.example.NOTIFY"),Manifest.permission.SEND_SMS);

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(receiver, filter, Manifest.permission.SEND_SMS,null );
    }

    public void onClickDhoka(View view){
        Intent intent = new Intent();
        intent.setAction("com.example.broadcastrecieverdemo.DHOKA_MILA");
        intent.putExtra("data","Dhoka mila re... Bareli k bazar me...");
        sendBroadcast(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction("com.example.broadcastrecieverdemo.DHOKA_MILA");
        this.registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
 //       this.unregisterReceiver(receiver);
    }
}