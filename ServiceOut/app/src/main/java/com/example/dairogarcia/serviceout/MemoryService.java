package com.example.dairogarcia.serviceout;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Un {@link Service} que notifica la cantidad de memoria disponible en el sistema
 */

public class MemoryService extends Service {
    private static final String TAG = MemoryService.class.getSimpleName();
    private static final Object UPDATE_LISTENER = ;
    TimerTask timerTask;

    public MemoryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Servicio creado...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");


        final ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        final ActivityManager activityManager =
                (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        Timer timer = new Timer();



        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(UPDATE_LISTENER != null) {
                    handler.sendEmptyMessage(0);
                }
                activityManager.getMemoryInfo(memoryInfo);
                String availMem = memoryInfo.availMem / 1048576 + "MB";

                Log.d(TAG, availMem);
                //Toast.makeText(MemoryService.this, "ffff", Toast.LENGTH_SHORT).show();
                Intent localIntent = new Intent(Constants.ACTION_RUN_SERVICE)
                        .putExtra(Constants.EXTRA_MEMORY, availMem);

                // Emitir el intent a la actividad
                LocalBroadcastManager.
                        getInstance(MemoryService.this).sendBroadcast(localIntent);

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 10000);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        timerTask.cancel();
        Intent localIntent = new Intent(Constants.ACTION_MEMORY_EXIT);

        // Emitir el intent a la actividad
        LocalBroadcastManager.
                getInstance(MemoryService.this).sendBroadcast(localIntent);
        Log.d(TAG, "Servicio destruido...");
    }
}