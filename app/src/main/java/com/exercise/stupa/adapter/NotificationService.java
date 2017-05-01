package com.exercise.stupa.adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.exercise.stupa.MainActivity;
import com.exercise.stupa.R;
import com.exercise.stupa.SplashActivity;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONObject;

/**
 * Created by anjas on 01/05/17.
 */

public class NotificationService  extends Service{
    String sChannel;
    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("DEBUGS", "ONCREATE");

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DEBUGS", "ONCOMMAND");
        sChannel = intent.getStringExtra("channel");
        PusherOptions opt = new PusherOptions();
        opt.setCluster("ap1");

        Pusher pusher = new Pusher("8f2f9ac0a4d4912ea40f", opt);
        // subscribe to our "messages" channel
        Channel channel = pusher.subscribe("student_channel_"+sChannel);
        Log.d("DEBUGS", sChannel);
        // listen for the "new_message" event
        channel.bind("data_event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {

                String course = "";
                String presence = "";
                try{
                    JSONObject object = new JSONObject(data);
                    course = object.getString("course_name");
                    presence = object.getString("presence");
                }catch (Exception e){

                }

                Intent targetIntent = new Intent(getApplicationContext(), SplashActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                Notification notification = new Notification.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setContentTitle(course)
                        .setContentText(presence)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setSmallIcon(R.mipmap.ic_launcher).build();
                notificationManager.notify(0, notification);
            }
        });

        // connect to the Pusher API
        pusher.connect();
        return START_STICKY;
    }
}
