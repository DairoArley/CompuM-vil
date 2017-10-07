package com.example.dairogarcia.serviceout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by dairo.garcia on 6/10/17.
 */


public class AutoArranque extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,  MemoryService.class);
        context.startService(service);
        Context context1 = null;
        Toast.makeText(context1, "ffff", Toast.LENGTH_SHORT).show();
    }

}
