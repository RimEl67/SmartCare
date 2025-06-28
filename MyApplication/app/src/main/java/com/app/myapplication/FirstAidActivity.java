package com.app.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstAidActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFirstAid;
    private Button btnEmergencyCall;
    private TextView tvPolice, tvAmbulance, tvFire, tvCivilProtection;
    private List<FirstAidTip> firstAidTips;
    private FirstAidAdapter adapter;

    // Emergency numbers for Morocco
    private static final String POLICE_NUMBER = "19";
    private static final String AMBULANCE_NUMBER = "15";
    private static final String FIRE_NUMBER = "15";
    private static final String CIVIL_PROTECTION_NUMBER = "177";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_first_aid);

            // Initialize the list
            firstAidTips = new ArrayList<>();

            // Setup toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("First Aid Tips");
                }
                toolbar.setNavigationOnClickListener(v -> finish());
            }

            // Initialize emergency contact views
            tvPolice = findViewById(R.id.tvPolice);
            tvAmbulance = findViewById(R.id.tvAmbulance);
            tvFire = findViewById(R.id.tvFire);
            tvCivilProtection = findViewById(R.id.tvCivilProtection);

            // Set emergency numbers
            if (tvPolice != null) tvPolice.setText(POLICE_NUMBER);
            if (tvAmbulance != null) tvAmbulance.setText(AMBULANCE_NUMBER);
            if (tvFire != null) tvFire.setText(FIRE_NUMBER);
            if (tvCivilProtection != null) tvCivilProtection.setText(CIVIL_PROTECTION_NUMBER);

            // Setup click listeners for emergency numbers
            if (tvPolice != null) {
                tvPolice.setOnClickListener(v -> dialEmergencyNumber(POLICE_NUMBER));
            }
            if (tvAmbulance != null) {
                tvAmbulance.setOnClickListener(v -> dialEmergencyNumber(AMBULANCE_NUMBER));
            }
            if (tvFire != null) {
                tvFire.setOnClickListener(v -> dialEmergencyNumber(FIRE_NUMBER));
            }
            if (tvCivilProtection != null) {
                tvCivilProtection.setOnClickListener(v -> dialEmergencyNumber(CIVIL_PROTECTION_NUMBER));
            }

            // Setup emergency call button
            btnEmergencyCall = findViewById(R.id.btnEmergencyCall);
            if (btnEmergencyCall != null) {
                btnEmergencyCall.setOnClickListener(v -> showEmergencyDialog());
            }

            // Setup RecyclerView
            recyclerViewFirstAid = findViewById(R.id.recyclerViewFirstAid);
            if (recyclerViewFirstAid != null) {
                recyclerViewFirstAid.setLayoutManager(new LinearLayoutManager(this));
                loadAllFirstAidTips();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error initializing First Aid screen", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadAllFirstAidTips() {
        try {
            // Clear any existing tips
            firstAidTips.clear();

            // Add sample first aid tips
            firstAidTips.add(new FirstAidTip(1, "Cuts and Scrapes", "Wounds",
                    "1. Wash your hands with soap and water\n" +
                            "2. Stop the bleeding by applying pressure with a clean cloth\n" +
                            "3. Clean the wound with water\n" +
                            "4. Apply an antibiotic cream\n" +
                            "5. Cover with a sterile bandage"));

            firstAidTips.add(new FirstAidTip(2, "Minor Burns", "Burns",
                    "1. Cool the burn with cool running water for 10-15 minutes\n" +
                            "2. Remove rings or other tight items\n" +
                            "3. Don't break blisters\n" +
                            "4. Apply lotion with aloe vera\n" +
                            "5. Bandage the burn loosely with a sterile gauze"));

            firstAidTips.add(new FirstAidTip(3, "Sprains", "Fractures",
                    "1. Rest the injured limb\n" +
                            "2. Ice the area for 20 minutes, several times a day\n" +
                            "3. Compress the area with an elastic bandage\n" +
                            "4. Elevate the injured limb\n" +
                            "5. Take over-the-counter pain medication if needed"));

            firstAidTips.add(new FirstAidTip(4, "Choking", "CPR",
                    "1. Encourage coughing to clear the blockage\n" +
                            "2. If the person can't cough, speak, or breathe, stand behind them\n" +
                            "3. Place a fist slightly above their navel\n" +
                            "4. Grasp your fist with your other hand\n" +
                            "5. Pull inward and upward with quick thrusts"));

            // Load saved state for tips
            loadSavedStates();

            // Create and set adapter safely
            try {
                if (recyclerViewFirstAid != null) {
                    adapter = new FirstAidAdapter(firstAidTips);
                    recyclerViewFirstAid.setAdapter(adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading first aid tips", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSavedStates() {
        try {
            SharedPreferences prefs = getSharedPreferences("FirstAidTips", MODE_PRIVATE);
            for (FirstAidTip tip : firstAidTips) {
                boolean isSaved = prefs.getBoolean("tip_" + tip.getId(), false);
                tip.setSaved(isSaved);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toggleSaveFirstAidTip(FirstAidTip tip) {
        try {
            // Toggle the saved state
            tip.setSaved(!tip.isSaved());

            // Save to preferences
            SharedPreferences prefs = getSharedPreferences("FirstAidTips", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("tip_" + tip.getId(), tip.isSaved());
            editor.apply();

            // Show confirmation
            Toast.makeText(this,
                    tip.isSaved() ? "Saved to favorites" : "Removed from favorites",
                    Toast.LENGTH_SHORT).show();

            // Notify adapter if it exists
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterFirstAidTips(String category) {
        try {
            // Filter the tips by category
            List<FirstAidTip> filteredTips = new ArrayList<>();
            for (FirstAidTip tip : firstAidTips) {
                if (tip.getCategory().equals(category)) {
                    filteredTips.add(tip);
                }
            }

            // Update the adapter with filtered tips
            if (recyclerViewFirstAid != null) {
                adapter = new FirstAidAdapter(filteredTips);
                recyclerViewFirstAid.setAdapter(adapter);
            }

            Toast.makeText(this, "Showing " + category + " tips", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSavedFirstAidTips() {
        try {
            // Get only saved tips
            List<FirstAidTip> savedTips = new ArrayList<>();
            for (FirstAidTip tip : firstAidTips) {
                if (tip.isSaved()) {
                    savedTips.add(tip);
                }
            }

            // Update the adapter with saved tips
            if (recyclerViewFirstAid != null) {
                adapter = new FirstAidAdapter(savedTips);
                recyclerViewFirstAid.setAdapter(adapter);
            }

            Toast.makeText(this, "Showing saved tips", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEmergencyDialog() {
        try {
            String[] emergencyServices = {"Police (19)", "Ambulance (15)", "Fire Department (15)", "Civil Protection (177)"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Call Emergency Service")
                    .setItems(emergencyServices, (dialog, which) -> {
                        // Call selected emergency service
                        switch (which) {
                            case 0: // Police
                                dialEmergencyNumber(POLICE_NUMBER);
                                break;
                            case 1: // Ambulance
                                dialEmergencyNumber(AMBULANCE_NUMBER);
                                break;
                            case 2: // Fire
                                dialEmergencyNumber(FIRE_NUMBER);
                                break;
                            case 3: // Civil Protection
                                dialEmergencyNumber(CIVIL_PROTECTION_NUMBER);
                                break;
                        }
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

            builder.create().show();
        } catch (Exception e) {
            // Fallback if dialog fails
            e.printStackTrace();
            dialEmergencyNumber(AMBULANCE_NUMBER);
        }
    }

    private void dialEmergencyNumber(String phoneNumber) {
        try {
            // Confirm before dialing
            new AlertDialog.Builder(this)
                    .setTitle("Emergency Call")
                    .setMessage("Are you sure you want to call emergency number " + phoneNumber + "?")
                    .setPositiveButton("Call", (dialog, which) -> {
                        // Make the call
                        try {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + phoneNumber));
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(FirstAidActivity.this,
                                    "Error making call. Dial " + phoneNumber + " manually.",
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } catch (Exception e) {
            // Fallback if dialog fails
            e.printStackTrace();
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Please dial " + phoneNumber + " manually", Toast.LENGTH_LONG).show();
            }
        }
    }

    // First Aid Tip model class
    public static class FirstAidTip {
        private int id;
        private String title;
        private String category;
        private String instructions;
        private boolean isSaved;

        public FirstAidTip(int id, String title, String category, String instructions) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.instructions = instructions;
            this.isSaved = false;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public String getInstructions() {
            return instructions;
        }

        public boolean isSaved() {
            return isSaved;
        }

        public void setSaved(boolean saved) {
            isSaved = saved;
        }
    }

    // Make the adapter an inner class to avoid compilation issues
    class FirstAidAdapter extends RecyclerView.Adapter<FirstAidAdapter.FirstAidViewHolder> {
        private List<FirstAidTip> items;

        FirstAidAdapter(List<FirstAidTip> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public FirstAidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            try {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_first_aid_tip, parent, false);
                return new FirstAidViewHolder(view);
            } catch (Exception e) {
                e.printStackTrace();
                // Create a simple fallback view if the layout fails to inflate
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(30, 30, 30, 30);
                return new FirstAidViewHolder(textView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull FirstAidViewHolder holder, int position) {
            try {
                FirstAidTip tip = items.get(position);

                // Set data to views if they exist
                if (holder.tvTitle != null) {
                    holder.tvTitle.setText(tip.getTitle());
                }

                if (holder.tvCategory != null) {
                    holder.tvCategory.setText(tip.getCategory());
                }

                if (holder.tvInstructions != null) {
                    holder.tvInstructions.setText(tip.getInstructions());
                }

                // Set bookmark icon based on saved status
                if (holder.btnSave != null) {
                    holder.btnSave.setImageResource(tip.isSaved() ?
                            android.R.drawable.btn_star_big_on :
                            android.R.drawable.btn_star_big_off);

                    // Set click listener for save button
                    holder.btnSave.setOnClickListener(v -> {
                        toggleSaveFirstAidTip(tip);
                        // We don't need to update the icon here since toggleSaveFirstAidTip will call notifyDataSetChanged
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

        class FirstAidViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvCategory, tvInstructions;
            ImageButton btnSave;

            FirstAidViewHolder(View itemView) {
                super(itemView);
                try {
                    tvTitle = itemView.findViewById(R.id.tvTipTitle);
                    tvCategory = itemView.findViewById(R.id.tvTipCategory);
                    tvInstructions = itemView.findViewById(R.id.tvTipInstructions);
                    btnSave = itemView.findViewById(R.id.btnSaveTip);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}