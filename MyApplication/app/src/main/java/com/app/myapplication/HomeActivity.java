package com.app.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import com.app.myapplication.MedicineReminderActivity;

public class HomeActivity extends AppCompatActivity {

    private CardView cvMedReminder, cvHealthTips, cvKnowYourMed, cvFirstAid, cvNearbyHospitals;
    private TextView tvUserName, tvHealthTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        cvMedReminder = findViewById(R.id.cvMedReminder);
        cvHealthTips = findViewById(R.id.cvHealthTips);
        cvKnowYourMed = findViewById(R.id.cvKnowYourMed);
        cvFirstAid = findViewById(R.id.cvFirstAid);
        cvNearbyHospitals = findViewById(R.id.cvNearbyHospitals);
        tvUserName = findViewById(R.id.tvUserName);
        tvHealthTip = findViewById(R.id.tvHealthTip);

        // Set user name
        String userName = getSharedPreferences("CareForUPrefs", MODE_PRIVATE)
                .getString("userName", "User");
        tvUserName.setText("Hi, " + userName);

        // Set random health tip
        String[] healthTips = {
                "Quitting smoking🚭",
                "Drink 8 glasses of water daily 💧",
                "Exercise for 30 minutes daily 🏃‍♂️",
                "Reduce screen time before bed 😴",
                "Eat more fruits and vegetables 🥗"
        };
        tvHealthTip.setText(healthTips[(int) (Math.random() * healthTips.length)]);

        // Set click listeners for feature cards
        cvMedReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MedicineReminderActivity.class));
            }
        });

        cvHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthTipsActivity.class));
            }
        });

        cvKnowYourMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, KnowYourMedicineActivity.class));
            }
        });

        cvFirstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FirstAidActivity.class));
            }
        });

        cvNearbyHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NearbyHospitalsActivity.class));
            }
        });

        // Add chatbot button functionality
        ImageButton btnChatbot = findViewById(R.id.btnChatbot);
        btnChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChatbotActivity.class));
            }
        });
    }

    public static class MedicationAlarmReceiver extends BroadcastReceiver {

        private static final String CHANNEL_ID = "CareForUChannel";
        private static final int NOTIFICATION_ID = 1;

        @Override
        public void onReceive(Context context, Intent intent) {
            // Get medication name from intent
            String medicationName = intent.getStringExtra("MEDICATION_NAME");
            if (medicationName == null) {
                medicationName = "your medication";
            }

            // Create notification channel for Android 8.0+
            createNotificationChannel(context);

            // Create notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("Medicine Reminder")
                    .setContentText("Time to take " + medicationName)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);

            // Create intent to open app when notification is tapped
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            builder.setContentIntent(pendingIntent);

            // Show notification
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

        private void createNotificationChannel(Context context) {
            // Create the notification channel (required for Android 8.0+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Smart Care";
                String description = "Medicine reminder notifications";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}