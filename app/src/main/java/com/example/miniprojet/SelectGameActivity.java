package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.miniprojet.Mathematiques.TableSelectionActivity;

public class SelectGameActivity extends AppCompatActivity {

    private CardView mathGame1, cultureGame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);

        mathGame1 = findViewById(R.id.mathGame1);
        cultureGame3 = findViewById(R.id.cultureGame3);

        mathGame1.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGameActivity.this, TableSelectionActivity.class);
            startActivity(intent);
        });

        cultureGame3.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGameActivity.this, DifficultyActivity.class);
            intent.putExtra("game", "Science");
            startActivity(intent);
        });
    }
}

