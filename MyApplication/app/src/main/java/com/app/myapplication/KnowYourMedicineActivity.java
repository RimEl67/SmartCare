package com.app.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.adapters.MedicineAdapter;
import com.app.myapplication.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class KnowYourMedicineActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView recyclerViewMedicines;
    private MedicineAdapter medicineAdapter;
    private List<Medicine> medicineList;
    private List<Medicine> filteredMedicineList;
    private TextView tvNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_know_your_medicine);


            // Setup toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    toolbar.setNavigationOnClickListener(v -> finish());
                }
            }

            // Initialize views
            searchEditText = findViewById(R.id.editTextSearch);
            recyclerViewMedicines = findViewById(R.id.recyclerViewMedicines);
            tvNoResults = findViewById(R.id.tvNoResults);

            // Setup RecyclerView
            recyclerViewMedicines.setLayoutManager(new LinearLayoutManager(this));

            // Initialize medicine data
            medicineList = new ArrayList<>();
            filteredMedicineList = new ArrayList<>();

            // Ajouter juste un médicament test pour vérifier que tout fonctionne
            addTestMedicine();

            // Create and set adapter
            medicineAdapter = new MedicineAdapter(filteredMedicineList);
            recyclerViewMedicines.setAdapter(medicineAdapter);

            // Setup search functionality
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Not needed
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterMedicines(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Not needed
                }
            });

            // Confirmation que tout est bien initialisé
            Toast.makeText(this, "Know Your Medicine chargé avec succès", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // En cas d'erreur, afficher un message explicite
            Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Méthode simplifiée pour tester l'ajout d'un médicament
    private void addTestMedicine() {
        // Utiliser une icône système pour éviter les problèmes de ressources
        int iconResource = android.R.drawable.ic_dialog_info;

        Medicine testMed = new Medicine(
                "Paracetamol (Test)",
                "Acetaminophen",
                "Pain reliever and fever reducer",
                "Used to treat mild to moderate pain and reduce fever.",
                "Common side effects include nausea, stomach pain.",
                "Adults: 500-1000mg every 4-6 hours as needed.",
                iconResource
        );

        medicineList.add(testMed);
        filteredMedicineList.add(testMed);
    }

    // Réintégrer la méthode initMedicineData plus tard
    private void initMedicineData() {
        // Cette méthode sera réactivée plus tard
    }

    private void filterMedicines(String query) {
        filteredMedicineList.clear();

        if (query.isEmpty()) {
            filteredMedicineList.addAll(medicineList);
            tvNoResults.setVisibility(View.GONE);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Medicine medicine : medicineList) {
                if (medicine.getName().toLowerCase().contains(lowerCaseQuery) ||
                        medicine.getGenericName().toLowerCase().contains(lowerCaseQuery)) {
                    filteredMedicineList.add(medicine);
                }
            }

            if (filteredMedicineList.isEmpty()) {
                tvNoResults.setVisibility(View.VISIBLE);
            } else {
                tvNoResults.setVisibility(View.GONE);
            }
        }

        medicineAdapter.notifyDataSetChanged();
    }
}