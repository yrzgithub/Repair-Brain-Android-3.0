package com.example.repairbrain20;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.security.Permission;
import java.security.Permissions;

public class AlarmReceiver extends BroadcastReceiver {

    final String CHANNEL_NAME = "ask";
    final String ID = "ask";
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        NotificationManager manager = createChannel();

        Intent open = new Intent(context,LoginActivity.class);
        PendingIntent open_pending = PendingIntent.getActivity(context,100,open,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,ID)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText("Are you free?")
                .setContentIntent(open_pending)
                .setPriority(NotificationManager.IMPORTANCE_MAX);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        manager.notify(100, builder.build());

        Toast.makeText(context,"Received",Toast.LENGTH_LONG).show();
    }

    public NotificationManager createChannel()
    {
        NotificationChannel channel = new NotificationChannel(ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager = (NotificationManager) context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
        return manager;
    }

}
