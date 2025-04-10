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

        // Récupération des vues
        gameTitleTextView = findViewById(R.id.gameTitle);
        easyModeCard = findViewById(R.id.easyModeCard);
        mediumModeCard = findViewById(R.id.mediumModeCard);
        hardModeCard = findViewById(R.id.hardModeCard);
        startGameButton = findViewById(R.id.startGameButton);

        // Récupération du nom du jeu via l'intent
        String gameName = getIntent().getStringExtra("game");
        gameTitleTextView.setText(gameName);

        // Définir les couleurs par défaut et sélectionnées des cartes
        defaultCardColor = ContextCompat.getColor(this, R.color.white);
        selectedCardColor = ContextCompat.getColor(this, R.color.primary_light);

        // Définition du listener pour la sélection des cartes
        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectedCard((CardView) v);
            }
        };

        // Assignation du listener aux cartes
        easyModeCard.setOnClickListener(cardClickListener);
        mediumModeCard.setOnClickListener(cardClickListener);
        hardModeCard.setOnClickListener(cardClickListener);

        // Lancement du jeu avec la difficulté sélectionnée
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Envoie des données sur le jeu et la difficulté à l'activité suivante
                Intent intent = new Intent(DifficultyActivity.this, ChooseLevel.class);
                intent.putExtra("game", gameName);
                intent.putExtra("difficulty", selectedDifficulty);
                startActivity(intent);

                // Finish pour empêcher le retour à cette activité depuis l'écran de niveau
                finish();
            }
        });

        // Sélectionner "Facile" par défaut au lancement
        updateSelectedCard(easyModeCard);
    }

    /**
     * Met à jour la carte sélectionnée et la difficulté correspondante.
     */
    private void updateSelectedCard(CardView selectedCard) {
        // Réinitialisation de toutes les cartes (retour à la couleur par défaut)
        easyModeCard.setCardBackgroundColor(defaultCardColor);
        mediumModeCard.setCardBackgroundColor(defaultCardColor);
        hardModeCard.setCardBackgroundColor(defaultCardColor);

        // Mise à jour de la carte sélectionnée avec la couleur sélectionnée
        selectedCard.setCardBackgroundColor(selectedCardColor);

        // Mise à jour de la difficulté en fonction de la carte sélectionnée
        if (selectedCard == easyModeCard) {
            selectedDifficulty = "FACILE";
        } else if (selectedCard == mediumModeCard) {
            selectedDifficulty = "MOYEN";
        } else if (selectedCard == hardModeCard) {
            selectedDifficulty = "DIFFICILE";
        }
    }

    @Override
    public void onBackPressed() {
        // On empêche l'utilisateur de revenir à l'écran précédent
        super.onBackPressed();
        finish();  // Appel de finish() pour garantir qu'il ne reviendra pas à l'activité actuelle
    }
}

