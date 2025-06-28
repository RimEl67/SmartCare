package com.app.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class NearbyHospitalsActivity extends AppCompatActivity {

    private TextView tvCityName;
    private TextView selectedHospitalBadge;
    private ImageView mapMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_nearby_hospitals);

            // Setup toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(v -> finish());

            // Initialize views
            tvCityName = findViewById(R.id.tvCityName);
            tvCityName.setText("Marrakech, Morocco");

            // Get map elements
            selectedHospitalBadge = findViewById(R.id.selectedHospitalBadge);
            mapMarker = findViewById(R.id.mapMarker);

            // Setup static hospital buttons
            setupHospitalClickListeners();

            // Setup floating action button
            FloatingActionButton fabMyLocation = findViewById(R.id.fabMyLocation);
            fabMyLocation.setOnClickListener(v -> {
                // Reset map to center on Marrakech
                selectedHospitalBadge.setVisibility(View.GONE);
                Toast.makeText(this, "Map centered on Marrakech", Toast.LENGTH_SHORT).show();
            });

        } catch (Exception e) {
            // Handle any exception to prevent app crash
            Toast.makeText(this, "Error loading hospitals: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void setupHospitalClickListeners() {
        // Find and set up direction button clicks for each hospital
        setupDirectionButton(R.id.btnDirections1, "CHU Mohammed VI", "Route de Safi, Marrakech", 31.6541, -8.0175);
        setupDirectionButton(R.id.btnDirections2, "Hôpital Ibn Tofail", "Rue Abdelouahab El Marini, Marrakech", 31.6374, -8.0217);
        setupDirectionButton(R.id.btnDirections3, "Clinique Internationale de Marrakech", "Avenue Hassan II, Marrakech", 31.6383, -8.0027);
        setupDirectionButton(R.id.btnDirections4, "Clinique Privee", "Boulevard Allal Al Fassi, Marrakech", 31.6487, -8.0091);
    }

    private void setupDirectionButton(int buttonId, String name, String address, double lat, double lng) {
        try {
            ImageButton button = findViewById(buttonId);
            if (button != null) {
                button.setOnClickListener(v -> openDirections(name, address, lat, lng));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHospitalOnMap(String name, String distance) {
        try {
            // Show hospital badge
            selectedHospitalBadge.setText(name);
            selectedHospitalBadge.setVisibility(View.VISIBLE);

            // Update city text to show selected hospital
            tvCityName.setText(name + " - " + distance);

            // Indicate selection
            Toast.makeText(this, "Selected: " + name, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error showing hospital: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void openDirections(String name, String address, double latitude, double longitude) {
        try {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%s",
                    latitude, longitude,
                    Uri.encode(name + ", " + address));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // Fallback to browser if maps app is not available
                Uri browserUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" +
                        latitude + "," + longitude);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                startActivity(browserIntent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Maps application not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}