package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class DifficultyActivity extends AppCompatActivity {

    TextView gameTitleTextView;
    CardView easyModeCard, mediumModeCard, hardModeCard;
    Button startGameButton;
    String selectedDifficulty = "easy"; // Par défaut à "easy"
    int defaultCardColor, selectedCardColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty);

        gameTitleTextView = findViewById(R.id.gameTitle);
        easyModeCard = findViewById(R.id.easyModeCard);
        mediumModeCard = findViewById(R.id.mediumModeCard);
        hardModeCard = findViewById(R.id.hardModeCard);
        startGameButton = findViewById(R.id.startGameButton);

        String gameName = getIntent().getStringExtra("game");
        gameTitleTextView.setText(gameName);

        // Définir les couleurs
        defaultCardColor = ContextCompat.getColor(this, R.color.white);
        selectedCardColor = ContextCompat.getColor(this, R.color.primary_light);

        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectedCard((CardView) v);
            }
        };

        easyModeCard.setOnClickListener(cardClickListener);
        mediumModeCard.setOnClickListener(cardClickListener);
        hardModeCard.setOnClickListener(cardClickListener);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, ChooseLevel.class);
                intent.putExtra("game", gameName);
                intent.putExtra("difficulty", selectedDifficulty);
                startActivity(intent);
            }
        });

        // Sélectionner "Facile" par défaut
        updateSelectedCard(easyModeCard);
    }

    private void updateSelectedCard(CardView selectedCard) {
        // Réinitialiser toutes les cartes
        easyModeCard.setCardBackgroundColor(defaultCardColor);
        mediumModeCard.setCardBackgroundColor(defaultCardColor);
        hardModeCard.setCardBackgroundColor(defaultCardColor);

        // Mettre à jour la carte sélectionnée
        selectedCard.setCardBackgroundColor(selectedCardColor);

        // Mettre à jour la difficulté sélectionnée
        if (selectedCard == easyModeCard) {
            selectedDifficulty = "FACILE";
        } else if (selectedCard == mediumModeCard) {
            selectedDifficulty = "MOYEN";
        } else if (selectedCard == hardModeCard) {
            selectedDifficulty = "DIFFICILE";
        }
    }
}
