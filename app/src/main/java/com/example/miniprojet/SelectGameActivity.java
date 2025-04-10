package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.miniprojet.Mathematiques.TableSelectionActivity;

public class SelectGameActivity extends AppCompatActivity {

    CardView mathGame1, mathGame2, mathGame3, mathGame4, cultureGame1,cultureGame2, cultureGame3, cultureGame4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);


        mathGame1 = findViewById(R.id.mathGame1);
        cultureGame3 = findViewById(R.id.cultureGame3);


        cultureGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectGameActivity.this, DifficultyActivity.class);
                intent.putExtra("game", "Science");
                startActivity(intent);
            }
        });

        mathGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectGameActivity.this, TableSelectionActivity.class);
                startActivity(intent);
            }
        });

    }
}