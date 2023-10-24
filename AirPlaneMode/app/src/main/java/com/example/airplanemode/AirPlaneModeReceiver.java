package com.example.airplanemode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirPlaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()!= null && intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        {
            boolean isAirPlaneModeon= intent.getBooleanExtra("state",false);
            String msg=isAirPlaneModeon? "Airplane Mode is On":"Airplane Mode is Off";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
