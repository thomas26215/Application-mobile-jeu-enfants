package com.example.miniprojet.Mathematiques;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.R;

public class TableSelectionActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_selection);

        initializeViews();
        setupNumberPicker();
        setupStartButton();
    }

    private void initializeViews() {
        numberPicker = findViewById(R.id.tablePicker);
        startButton = findViewById(R.id.startButton);
    }

    private void setupNumberPicker() {
        // Configuration du NumberPicker
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(12);
        numberPicker.setWrapSelectorWheel(false);
    }

    private void setupStartButton() {
        startButton.setOnClickListener(v -> {
            int selectedTable = numberPicker.getValue(); // Récupère la valeur sélectionnée
            launchTableQuizActivity(selectedTable); // Lance l'activité du quiz
        });
    }

    private void launchTableQuizActivity(int table) {
        // Lance l'activité QuizActivity avec la table sélectionnée
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("table", table); // Passe la valeur de la table
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Retourner à l'écran précédent
    }
}

