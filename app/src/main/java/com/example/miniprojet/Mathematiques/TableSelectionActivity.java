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

        numberPicker.setFormatter(value -> String.format("%d", value));
    }

    private void setupStartButton() {
        startButton.setOnClickListener(v -> {
            int selectedTable = numberPicker.getValue();
            launchTableQuizActivity(selectedTable);
        });
    }

    private void launchTableQuizActivity(int table) {
        if (table < 1 || table > 12) {
            Toast.makeText(this, "Veuillez sélectionner une table valide (1-12)", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("table", table);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
