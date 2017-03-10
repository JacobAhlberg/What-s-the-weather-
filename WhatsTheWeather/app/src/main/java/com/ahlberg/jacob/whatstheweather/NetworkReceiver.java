package com.ahlberg.jacob.whatstheweather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(checkInternet(context))  Toast.makeText(context, "Network Available",Toast.LENGTH_LONG).show();
        else                        Toast.makeText(context, "Network not available",Toast.LENGTH_LONG).show();
    }

    boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        return serviceManager.isNetworkAvailable();
    }

}
