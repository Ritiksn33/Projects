package com.ckd.CollegeKaDost;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ExitService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {



        Toast.makeText(getApplicationContext(), "exit exit exit", Toast.LENGTH_SHORT).show();
        System.out.println("EXIT EXIT EXIT PLEASE");

        super.onTaskRemoved(rootIntent);
        this.stopSelf();


    }
}
