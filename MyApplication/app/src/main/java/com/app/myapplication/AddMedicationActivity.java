package com.app.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.myapplication.models.Medication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText etMedicationName;
    private EditText etDosage;
    private RadioGroup rgDosageType;
    private RadioGroup rgFrequency;
    private CheckBox cbMorning, cbAfternoon, cbEvening, cbNight;
    private Button btnPickTime;
    private Button btnSave;
    private String selectedTime = "8:00 AM";
    private int selectedHour = 8;
    private int selectedMinute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_add_medication);

            // Initialize just the essential buttons first
            btnPickTime = findViewById(R.id.btnPickTime);
            btnSave = findViewById(R.id.btnSave);

            // Initialize other views
            etMedicationName = findViewById(R.id.etMedicationName);
            etDosage = findViewById(R.id.etDosage);
            rgDosageType = findViewById(R.id.rgDosageType);
            rgFrequency = findViewById(R.id.rgFrequency);
            cbMorning = findViewById(R.id.cbMorning);
            cbAfternoon = findViewById(R.id.cbAfternoon);
            cbEvening = findViewById(R.id.cbEvening);
            cbNight = findViewById(R.id.cbNight);

            // Set up time picker button
            btnPickTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePickerDialog();
                }
            });

            // Set up save button
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createMedication();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error initializing: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void showTimePickerDialog() {
        try {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // Save selected time values
                            selectedHour = hourOfDay;
                            selectedMinute = minute;

                            // Format time for display
                            String amPm = hourOfDay >= 12 ? "PM" : "AM";
                            int hour12 = hourOfDay > 12 ? hourOfDay - 12 : (hourOfDay == 0 ? 12 : hourOfDay);
                            selectedTime = String.format("%d:%02d %s", hour12, minute, amPm);
                            btnPickTime.setText("SET REMINDER TIME: " + selectedTime);
                        }
                    }, hour, minute, false);
            timePickerDialog.show();
        } catch (Exception e) {
            Toast.makeText(this, "Error showing time picker: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Add this new method
    private void createMedication() {
        try {
            // Get name and dosage
            String name = etMedicationName.getText().toString().trim();
            String dosage = etDosage.getText().toString().trim();

            // Basic validation
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter medication name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dosage.isEmpty()) {
                Toast.makeText(this, "Please enter dosage", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get dosage type (form)
            String form = "Tablet"; // Default
            if (rgDosageType != null) {
                int checkedId = rgDosageType.getCheckedRadioButtonId();
                if (checkedId == R.id.rbTablet) {
                    form = "Tablet";
                } else if (checkedId == R.id.rbCapsule) {
                    form = "Capsule";
                } else if (checkedId == R.id.rbLiquid) {
                    form = "Liquid";
                } else if (checkedId == R.id.rbInjection) {
                    form = "Injection";
                }
            }

            // Get time slots
            List<String> timeSlots = new ArrayList<>();
            if (cbMorning != null && cbMorning.isChecked()) timeSlots.add("Morning");
            if (cbAfternoon != null && cbAfternoon.isChecked()) timeSlots.add("Afternoon");
            if (cbEvening != null && cbEvening.isChecked()) timeSlots.add("Evening");
            if (cbNight != null && cbNight.isChecked()) timeSlots.add("Night");

            // If no time slots selected, use Morning as default
            if (timeSlots.isEmpty()) {
                timeSlots.add("Morning");
            }

            // Get frequency
            String frequency = "Daily"; // Default
            if (rgFrequency != null) {
                int checkedId = rgFrequency.getCheckedRadioButtonId();
                if (checkedId == R.id.rbDaily) {
                    frequency = "Daily";
                } else if (checkedId == R.id.rbWeekly) {
                    frequency = "Weekly";
                } else if (checkedId == R.id.rbMonthly) {
                    frequency = "Monthly";
                } else if (checkedId == R.id.rbAsNeeded) {
                    frequency = "As Needed";
                }
            }

            // Create medication with unique ID
            int id = (int) (System.currentTimeMillis() % 10000);

            // Create medication object - make sure parameter order matches your constructor
            Medication medication = new Medication(
                    id,
                    name,
                    dosage,
                    form,
                    frequency,
                    selectedTime,
                    timeSlots.toArray(new String[0]),
                    true
            );

            // Try to schedule alarm
            try {
                scheduleAlarm(id, name);
            } catch (Exception e) {
                Toast.makeText(this, "Warning: Could not set alarm. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                // Continue even if alarm fails
            }

            // Return result
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_MEDICATION", medication);
            setResult(RESULT_OK, resultIntent);

            Toast.makeText(this, "Medication added successfully", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "Error creating medication: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void scheduleAlarm(int medicationId, String medicationName) {
        try {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, HomeActivity.MedicationAlarmReceiver.class);

            // Put medication details in the intent
            intent.putExtra("MEDICATION_NAME", medicationName);

            // Create a PendingIntent
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    medicationId, // unique request code
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            // Set alarm time
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);
            calendar.set(Calendar.SECOND, 0);

            // If time is in the past, set for tomorrow
            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            // Schedule the alarm
            if (alarmManager != null) {
                alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, // repeat daily
                        pendingIntent
                );

                Toast.makeText(this, "Reminder set for " + selectedTime, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Just log the error, don't prevent medication from being added
            e.printStackTrace();
            throw e; // rethrow to caller
        }
    }
}












