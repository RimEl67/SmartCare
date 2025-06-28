package com.app.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private CircleImageView ivProfile;
    private Button btnConnect;
    private TextView tvProfileHint;
    private CircleImageView[] avatars = new CircleImageView[6];
    private int selectedAvatarId = R.drawable.default_profile;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        etName = findViewById(R.id.etName);
        ivProfile = findViewById(R.id.ivProfile);
        btnConnect = findViewById(R.id.btnConnect);
        tvProfileHint = findViewById(R.id.tvProfileHint);

        // Initialize avatar selection
        avatars[0] = findViewById(R.id.avatar1);
        avatars[1] = findViewById(R.id.avatar2);
        avatars[2] = findViewById(R.id.avatar3);
        avatars[3] = findViewById(R.id.avatar4);
        avatars[4] = findViewById(R.id.avatar5);
        avatars[5] = findViewById(R.id.avatar6);

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("CareForUPrefs", MODE_PRIVATE);

        // Set click listeners for avatars
        for (int i = 0; i < avatars.length; i++) {
            final int index = i;
            avatars[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Set selected avatar
                    selectedAvatarId = getResources().getIdentifier(
                            "avatar" + (index + 1),
                            "drawable",
                            getPackageName());

                    // Update profile image
                    ivProfile.setImageResource(selectedAvatarId);

                    // Highlight selected avatar
                    for (int j = 0; j < avatars.length; j++) {
                        avatars[j].setBorderWidth(j == index ? 4 : 0);
                    }
                }
            });
        }

        // Connect button click listener
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();

                if (name.isEmpty()) {
                    etName.setError("Please enter your name");
                    return;
                }

                // Save user data
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", name);
                editor.putInt("userAvatar", selectedAvatarId);
                editor.apply();

                // Navigate to home screen
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });

        // Check if user already exists
        if (sharedPreferences.contains("userName")) {
            // Auto-login
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }
}