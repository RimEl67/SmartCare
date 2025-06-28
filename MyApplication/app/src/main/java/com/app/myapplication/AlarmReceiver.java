package com.app.myapplication.receivers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.app.myapplication.MedicineReminderActivity;
import com.app.myapplication.R;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "MedicationAlarm";
    private static final String CHANNEL_ID = "medication_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm received!");

        // Get medication details from intent
        String medicationName = intent.getStringExtra("MEDICATION_NAME");
        if (medicationName == null) medicationName = "Your medication";

        int medicationId = intent.getIntExtra("MEDICATION_ID", 1);
        Log.d(TAG, "Medication: " + medicationName + ", ID: " + medicationId);

        // Create an intent to open the app when notification is clicked
        Intent notificationIntent = new Intent(context, MedicineReminderActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Use different request code for the PendingIntent to avoid conflicts
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                medicationId + 100, // Add offset to avoid conflict
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Create notification channel for Android 8.0+
        createNotificationChannel(context);

        // Set alarm sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        // Build notification with high priority
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Medicine Reminder")
                .setContentText("Time to take " + medicationName)
                .setPriority(NotificationCompat.PRIORITY_MAX) // Use MAX priority
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setSound(alarmSound)
                .setVibrate(new long[]{0, 500, 250, 500}) // More noticeable vibration pattern
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Show notification
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            Log.d(TAG, "Showing notification for: " + medicationName);
            notificationManager.notify(medicationId, builder.build());
        } else {
            Log.e(TAG, "NotificationManager is null");
        }
    }

    private void createNotificationChannel(Context context) {
        // Create notification channel for Android 8.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Medication Reminders",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("Notifications for medication reminders");
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}