package com.example.converter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumber;
    private Spinner fromBase, toBase;
    private Button convertButton;
    private TextView resultView, titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        fromBase = findViewById(R.id.fromBase);
        toBase = findViewById(R.id.toBase);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);
        titleView = findViewById(R.id.titleView);

        // Appliquer les couleurs demandées
        titleView.setTextColor(Color.BLACK);
        inputNumber.setTextColor(Color.BLACK);
        fromBase.setBackgroundColor(Color.BLACK);
        toBase.setBackgroundColor(Color.BLACK);
        convertButton.setBackgroundColor(Color.BLACK);
        convertButton.setTextColor(Color.WHITE);
        resultView.setTextColor(Color.BLACK);

        // Ajouter une bordure au TextView du résultat
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.WHITE); // Fond blanc
        border.setStroke(3, Color.BLACK); // Bordure noire de 3px
        resultView.setBackground(border);
        resultView.setPadding(10, 10, 10, 10);

        // Liste des bases disponibles
        String[] bases = {"Binaire", "Octal", "Décimal", "Hexadécimal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bases);
        fromBase.setAdapter(adapter);
        toBase.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertNumber());
    }

    private void convertNumber() {
        try {
            String input = inputNumber.getText().toString();
            int from = getBase(fromBase.getSelectedItem().toString());
            int to = getBase(toBase.getSelectedItem().toString());

            int decimalValue = Integer.parseInt(input, from);
            String result = Integer.toString(decimalValue, to).toUpperCase();

            resultView.setText("Résultat : " + result);
        } catch (Exception e) {
            resultView.setText("Erreur de conversion !");
        }
    }

    private int getBase(String base) {
        switch (base) {
            case "Binaire": return 2;
            case "Octal": return 8;
            case "Décimal": return 10;
            case "Hexadécimal": return 16;
            default: return 10;
        }
    }
}
