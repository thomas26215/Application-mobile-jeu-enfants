package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojet.Entités.Question.QuestionGenerator;

import java.util.ArrayList;
import java.util.List;

public class ChooseLevel extends AppCompatActivity implements LevelAdapter.OnLevelClickListener {

    private RecyclerView recyclerView;
    private List<String> levels = new ArrayList<>();
    private LevelAdapter adapter;
    private String game;
    private String difficulty;
    private QuestionGenerator questionGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level);

        // Restauration du contexte de jeu (game et difficulty) via Intent
        getIntentData();

        // Initialisation du générateur de questions
        questionGenerator = new QuestionGenerator();

        // Configuration de l’UI
        setupToolbar();
        setupBackButton();
        setupRecyclerView();

        // Génération dynamique des niveaux en fonction de la difficulté
        generateLevels();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        difficulty = intent.getStringExtra("difficulty");

        // En cas de données manquantes, fin prématurée de l’activité
        if (game == null || difficulty == null) {
            Toast.makeText(this, "Données de jeu ou difficulté invalides", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setupBackButton() {
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            // Retour à l’activité précédente sans créer de nouvelle instance
            finish();
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LevelAdapter(levels, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Génère la liste des niveaux disponibles selon le nombre de questions
     */
    private void generateLevels() {
        int questionCount = getQuestionCountForDifficulty();

        if (questionCount <= 0) {
            Toast.makeText(this, "Aucune question disponible pour cette difficulté", Toast.LENGTH_SHORT).show();
            return;
        }

        levels.clear();
        for (int i = 1; i <= questionCount; i++) {
            levels.add("Niveau " + i);
        }
        adapter.notifyDataSetChanged();
    }

    private int getQuestionCountForDifficulty() {
        switch (difficulty.toUpperCase()) {
            case "FACILE":
                return questionGenerator.getEasyQuestionsCount();
            case "MOYEN":
                return questionGenerator.getMediumQuestionsCount();
            case "DIFFICILE":
                return questionGenerator.getHardQuestionsCount();
            default:
                Toast.makeText(this, "Difficulté inconnue, utilisation des questions faciles",
                        Toast.LENGTH_SHORT).show();
                return questionGenerator.getEasyQuestionsCount();
        }
    }

    @Override
    public void onLevelClick(String level) {
        try {
            int levelNumber = Integer.parseInt(level.replace("Niveau ", "").trim());
            launchQuestionActivity(levelNumber);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Format de niveau invalide", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchQuestionActivity(int levelNumber) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("game", game);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("level", levelNumber);

        // Pas besoin de terminer cette activité : retour possible avec le bouton back
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Utilise le comportement par défaut (retour à l'activité précédente dans la pile)
        super.onBackPressed();
    }
}

