package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
/*
Created by ArshadKhan
 */

public class MainActivity extends AppCompatActivity {
    public final String TAG="MainActivity";
    Button btnonoff,btndiscover,btnsend;
    ListView listView;
    TextView readmsgbox, connectionstatus;
    EditText writemsg;
    WifiManager wifiManager;
    WifiP2pManager p2pManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndiscover=(Button)findViewById(R.id.discover);
        btnonoff=(Button) findViewById(R.id.onOff);
        btnsend=(Button) findViewById(R.id.sendButton);
        listView=(ListView)findViewById(R.id.peerListView);
        readmsgbox=(TextView) findViewById(R.id.readMsg);
        connectionstatus=(TextView)findViewById(R.id.connectionStatus);
        writemsg=(EditText) findViewById(R.id.writeMsg);
        wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        p2pManager= (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel=p2pManager.initialize(this,getMainLooper(),null);
        mReceiver=new WifiBroadCastReceiver(mChannel,p2pManager,this);
        mIntentFilter=new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        Exeonoff();
    }

    private void Exeonoff() {
        btnonoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: onclickmethod");
                if (wifiManager.isWifiEnabled()){
                    Log.i(TAG, "onClick: wifi"+wifiManager.isWifiEnabled());
                    wifiManager.setWifiEnabled(false);
                    btnonoff.setText("ON");
                }else{
                    wifiManager.setWifiEnabled(true);
                    btnonoff.setText("OFF");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}