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

        // Récupération des données envoyées par Intent
        getIntentData();

        // Initialisation du QuestionGenerator
        questionGenerator = new QuestionGenerator();

        // Configuration de la Toolbar
        setupToolbar();

        // Configuration du bouton retour
        setupBackButton();

        // Configuration du RecyclerView
        setupRecyclerView();

        // Génération des niveaux basée sur les questions disponibles
        generateLevels();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        difficulty = intent.getStringExtra("difficulty");

        // Vérification des données reçues
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
     * Génère les niveaux dynamiquement en fonction du nombre de questions disponibles
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
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
