package com.chyngyz.quizapp.cloud_masseging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.chyngyz.quizapp.MainActivity;
import com.chyngyz.quizapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;

public class FBcloudMassaging extends FirebaseMessagingService {
    private String channelId = "chanel_id";
    private String title;
    private String text;
    private Uri imgUrl;
    private String type;
    private int notificationId = 0;
    private Map<String, String> data;


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("FCM", "onNewToken: " + s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("FCM", "onMessageReceived: " + remoteMessage.getNotification());
        Log.e("FCM", "Data: " + remoteMessage.getData());
        if (remoteMessage.getNotification() != null) {

            /*Log.i("FCM", "Title: " + remoteMessage.getNotification().getTitle());
            Log.i("FCM", "Body : " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());*/
            onMessageExecute(remoteMessage);
        }

    }

    private void onMessageExecute(@NonNull RemoteMessage remoteMessage) {
        data = remoteMessage.getData();
        title = data.get("title");
        imgUrl = Objects.requireNonNull(remoteMessage.getNotification()).getImageUrl();
        if (remoteMessage.getData().containsKey("content") && remoteMessage.getData().get("content") != null) {
            text = data.get("content");
        }
        if (remoteMessage.getData().containsKey("id") && remoteMessage.getData().get("id") != null) {
            notificationId = Integer.parseInt(data.get("id"));
        }
        Intent intent = new Intent(this, PushNotifActivity.class);
        showNotification(intent);
        intent.putExtra("push_title", title);
        intent.putExtra("push_text", text);
        intent.putExtra("push_img", imgUrl);
        startActivity(intent);
    }

    private void showNotification(Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.vec)
                .setContentTitle(title)
                .setContentText(text)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        notification.tickerText = title;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //если активити открыт был на момент нажатия на смс то перезапустит прогу
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.vec)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}
