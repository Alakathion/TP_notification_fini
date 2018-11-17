package com.example.lenyssa.notif_app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button addNotificationBtn;
    private Button deleteNotificationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNotificationBtn = (Button) findViewById(R.id.add_notification);
        addNotificationBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getBaseContext(), "Ajout d'une notification", Toast.LENGTH_SHORT).show();
                createNotification();
            }
        });

        deleteNotificationBtn = (Button) findViewById(R.id.delete_notification);
        deleteNotificationBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getBaseContext(), "Suppression d'une notification", Toast.LENGTH_SHORT).show();
                deleteNotification();
            }
        });
    }


    private final void createNotification(){
        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        CharSequence cs = "Bonjour ceci \n est ma premiere notification etendu car je veux tester le rendu de celle ci \n <3";
        final Intent launchNotifiactionIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setTicker("premiere notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_desc))
                .setContentIntent(pendingIntent)
                .setStyle(new Notification.BigTextStyle().bigText(cs))
                .addAction(R.drawable.ic_launcher_background,"Open", PendingIntent.getActivity(getApplicationContext(), 0,
                        getIntent(), 0, null));

        mNotification.notify(1, builder.build());
    }


    private void deleteNotification(){
        final NotificationManager notificationManager = (NotificationManager)getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        //la suppression de la notification se fait grâce a son ID
        notificationManager.cancel(1);
    }

}
