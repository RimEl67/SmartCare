package com.app.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.adapters.MedicationAdapter;
import com.app.myapplication.models.Medication;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicineReminderActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMedications;
    private FloatingActionButton fabAddMedication;
    private MedicationAdapter adapter;
    private List<Medication> medicationList;

    private static final int ADD_MEDICATION_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_reminder);

        // Initialize views
        recyclerViewMedications = findViewById(R.id.recyclerViewMedications);
        fabAddMedication = findViewById(R.id.fabAddMedication);

        // Initialize medication list
        medicationList = getMedicationList();

        // Initialize RecyclerView
        recyclerViewMedications.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicationAdapter(medicationList);
        recyclerViewMedications.setAdapter(adapter);

        // Set click listener for the FAB
        fabAddMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open activity to add a new medication
                Intent intent = new Intent(MedicineReminderActivity.this, AddMedicationActivity.class);
                startActivityForResult(intent, ADD_MEDICATION_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEDICATION_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the new medication from the intent
            Medication newMedication = (Medication) data.getSerializableExtra("NEW_MEDICATION");

            if (newMedication != null) {
                // Add to list and update adapter
                medicationList.add(newMedication);
                adapter.notifyItemInserted(medicationList.size() - 1);

                // Save medication to persistent storage (would be implemented in a real app)
                // For now we're just keeping it in memory

                // Show confirmation
                Toast.makeText(this, newMedication.getName() + " added to your medications", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Create a method to get sample medication data
    private List<Medication> getMedicationList() {
        List<Medication> medications = new ArrayList<>();

        // Add some sample data
        medications.add(new Medication(
                1,
                "Paracetamol",
                "500mg",
                "Tablet",
                "Daily",
                "8:00 AM",
                new String[]{"Morning", "Night"},
                true
        ));

        medications.add(new Medication(
                2,
                "Vitamin C",
                "250mg",
                "Tablet",
                "Daily",
                "9:00 AM",
                new String[]{"Morning"},
                true
        ));

        return medications;
    }
}







