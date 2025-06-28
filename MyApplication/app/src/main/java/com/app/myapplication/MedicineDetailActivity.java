package com.app.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MedicineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_medicine_detail);

            // Setup toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());

            // Get data from intent with safety checks
            String name = getIntent().getStringExtra("MEDICINE_NAME");
            String genericName = getIntent().getStringExtra("MEDICINE_GENERIC_NAME");
            String category = getIntent().getStringExtra("MEDICINE_CATEGORY");
            String description = getIntent().getStringExtra("MEDICINE_DESCRIPTION");
            String sideEffects = getIntent().getStringExtra("MEDICINE_SIDE_EFFECTS");
            String dosage = getIntent().getStringExtra("MEDICINE_DOSAGE");
            int imageResId = getIntent().getIntExtra("MEDICINE_IMAGE_RES_ID", android.R.drawable.ic_dialog_info);

            // Set views
            ImageView ivMedicineImage = findViewById(R.id.ivMedicineImage);
            TextView tvMedicineName = findViewById(R.id.tvMedicineName);
            TextView tvGenericName = findViewById(R.id.tvGenericName);
            TextView tvCategory = findViewById(R.id.tvCategory);
            TextView tvDescription = findViewById(R.id.tvDescription);
            TextView tvSideEffects = findViewById(R.id.tvSideEffects);
            TextView tvDosage = findViewById(R.id.tvDosage);

            // Set text values safely
            if (tvMedicineName != null) tvMedicineName.setText(name != null ? name : "Unknown");
            if (tvGenericName != null) tvGenericName.setText(genericName != null ? genericName : "Unknown");
            if (tvCategory != null) tvCategory.setText(category != null ? category : "Unknown");
            if (tvDescription != null) tvDescription.setText(description != null ? description : "No description available");
            if (tvSideEffects != null) tvSideEffects.setText(sideEffects != null ? sideEffects : "No side effects information available");
            if (tvDosage != null) tvDosage.setText(dosage != null ? dosage : "No dosage information available");

            // Set image resource safely
            if (ivMedicineImage != null) {
                try {
                    ivMedicineImage.setImageResource(imageResId);
                } catch (Exception e) {
                    // If custom resource fails, use system icon
                    try {
                        ivMedicineImage.setImageResource(android.R.drawable.ic_dialog_info);
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading medicine details: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}