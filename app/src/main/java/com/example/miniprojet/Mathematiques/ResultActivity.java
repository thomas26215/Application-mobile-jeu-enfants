package com.example.miniprojet.Mathematiques;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojet.MainActivity;
import com.example.miniprojet.R;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private ArrayList<String> correctAnswers;
    private boolean[] results;
    private LinearLayout resultsContainer;
    private int table;
    private ArrayList<Integer> questionIndices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Récupération des données envoyées via Intent
        questions = getIntent().getStringArrayListExtra("questions");
        answers = getIntent().getStringArrayListExtra("answers");
        correctAnswers = getIntent().getStringArrayListExtra("correctAnswers");
        results = getIntent().getBooleanArrayExtra("previousResults");
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 10);
        table = getIntent().getIntExtra("table", 1);
        questionIndices = getIntent().getIntegerArrayListExtra("questionIndices");

        // Initialisation des vues
        resultsContainer = findViewById(R.id.resultsContainer);
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(String.format("Score : %d/%d", score, total));

        // Affichage des résultats sans solutions par défaut
        showResults(false);

        // Configuration des boutons
        Button correctButton = findViewById(R.id.correctButton);
        Button solutionButton = findViewById(R.id.solutionButton);
        Button homeButton = findViewById(R.id.homeButton);

        // Gestion des clics sur les boutons
        correctButton.setOnClickListener(v -> launchCorrectionMode());
        solutionButton.setOnClickListener(v -> showResults(true));
        homeButton.setOnClickListener(v -> navigateToHome());
    }

    private void showResults(boolean showSolutions) {
        resultsContainer.removeAllViews();

        for (int i = 0; i < questions.size(); i++) {
            View resultItem = createResultItem(i, showSolutions);
            resultsContainer.addView(resultItem);
        }
    }

    private View createResultItem(int index, boolean showSolutions) {
        View resultItem = getLayoutInflater().inflate(R.layout.item_result, resultsContainer, false);
        LinearLayout itemLayout = resultItem.findViewById(R.id.itemLayout);

        TextView questionText = resultItem.findViewById(R.id.questionText);
        TextView userAnswerText = resultItem.findViewById(R.id.userAnswerText);
        TextView correctAnswerText = resultItem.findViewById(R.id.correctAnswerText);
        TextView resultIcon = resultItem.findViewById(R.id.resultIcon);

        questionText.setText(questions.get(index));
        userAnswerText.setText("Votre réponse : " + answers.get(index));

        boolean isCorrect = results[index];
        resultIcon.setText(isCorrect ? "✓" : "✗");
        resultIcon.setTextColor(isCorrect ? Color.GREEN : Color.RED);

        itemLayout.setBackgroundResource(isCorrect ? R.drawable.correct_item_background : R.drawable.incorrect_item_background);

        if (showSolutions) {
            correctAnswerText.setVisibility(View.VISIBLE);
            correctAnswerText.setText("Solution : " + correctAnswers.get(index));
        } else {
            correctAnswerText.setVisibility(View.GONE);
        }

        return resultItem;
    }

    private void launchCorrectionMode() {
        Intent correctionIntent = new Intent(this, QuizActivity.class);
        correctionIntent.putExtra("table", table);
        correctionIntent.putStringArrayListExtra("previousAnswers", answers);
        correctionIntent.putExtra("previousResults", results);
        correctionIntent.putIntegerArrayListExtra("questionIndices", questionIndices);
        startActivity(correctionIntent);
        finish();
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}

