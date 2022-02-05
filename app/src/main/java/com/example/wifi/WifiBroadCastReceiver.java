package com.example.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;
/*
created by Arshad
 */

public class WifiBroadCastReceiver extends BroadcastReceiver {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel mChannel;
    private MainActivity mActivity;


    public WifiBroadCastReceiver(WifiP2pManager.Channel mChannel, WifiP2pManager wifiP2pManager, MainActivity mActivity) {
        this.wifiP2pManager = wifiP2pManager;
        this.mChannel = mChannel;
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
      String Action=intent.getAction();
      if (wifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(Action)){
          int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
          if (state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
              Toast.makeText(context, "WIFI ON", Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(context, "WIFI OFF", Toast.LENGTH_SHORT).show();
          }

      }else if (wifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(Action)){

      }else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(Action)){

      }else  if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(Action)){

      }


    }
}
