package com.example.miniprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.miniprojet.Mathematiques.TableSelectionActivity;

public class SelectGameActivity extends AppCompatActivity {

    private CardView mathGame1, cultureGame3;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game);

        mathGame1 = findViewById(R.id.mathGame1);
        cultureGame3 = findViewById(R.id.cultureGame3);
        logoutButton = findViewById(R.id.logoutButton);

        // Gérer les événements de clic
        mathGame1.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGameActivity.this, TableSelectionActivity.class);
            startActivity(intent);
        });

        cultureGame3.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGameActivity.this, DifficultyActivity.class);
            intent.putExtra("game", "Science");
            startActivity(intent);
        });

        // Ajouter la fonctionnalité de déconnexion
        logoutButton.setOnClickListener(v -> {
            // Enlever l'information de l'utilisateur connecté
            SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
            preferences.edit().putBoolean("is_logged_in", false).apply();

            // Retour à l'écran de connexion
            Intent loginIntent = new Intent(SelectGameActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish(); // Fermer l'activité actuelle
        });

        // Désactiver certains jeux (griser)
        disableGame(cultureGame3); // Exemple : désactiver ce jeu spécifique
    }

    // Fonction pour désactiver un jeu
    private void disableGame(CardView gameCard) {
        gameCard.setCardBackgroundColor(getResources().getColor(R.color.disabled_game)); // Gris
        gameCard.setClickable(false); // Désactiver le clic
        gameCard.setAlpha(0.5f); // Ajouter un effet visuel (transparence)
    }
}

