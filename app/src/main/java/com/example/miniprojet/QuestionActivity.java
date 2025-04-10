package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.Entités.Question.QuestionGenerator;

public class QuestionActivity extends AppCompatActivity {

    TextView questionNumber;
    TextView questionText;
    EditText answerEditText;
    Button submitButton;
    TextView explanationText;
    Button nextButton;
    ImageButton backButton;

    private QuestionGenerator questionGenerator;
    private String game;
    private String difficulty;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        initializeViews();
        getIntentData();
        questionGenerator = new QuestionGenerator();

        loadQuestion();
        setupListeners();
    }

    private void initializeViews() {
        questionNumber = findViewById(R.id.questionNumber);
        questionText = findViewById(R.id.questionText);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);
        explanationText = findViewById(R.id.explanationText);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        difficulty = intent.getStringExtra("difficulty");
        currentQuestionIndex = intent.getIntExtra("level", 1)-1;
    }

    private void setupListeners() {
        submitButton.setOnClickListener(v -> onSubmitButtonClick());
        nextButton.setOnClickListener(v -> loadNextQuestion());
        backButton.setOnClickListener(v -> onBackButtonClick());
    }

    private void onSubmitButtonClick() {
        QuestionGenerator.Question currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            explanationText.setText(currentQuestion.expectedAnswer + " : " + currentQuestion.explanation);
            explanationText.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);
            nextButton.setVisibility(View.VISIBLE);
            answerEditText.setEnabled(false);
        }
    }

    private void loadQuestion() {
        QuestionGenerator.Question question = getCurrentQuestion();

        if (question != null) {
            updateUIWithQuestion(question);
        } else {
            handleQuestionLoadError();
        }
    }

    private QuestionGenerator.Question getCurrentQuestion() {
        switch (difficulty.toUpperCase()) {
            case "FACILE":
                return questionGenerator.getEasyQuestion(currentQuestionIndex);
            case "MOYEN":
                return questionGenerator.getMediumQuestion(currentQuestionIndex);
            case "DIFFICILE":
                return questionGenerator.getHardQuestion(currentQuestionIndex);
            default:
                Toast.makeText(this, "Difficulté inconnue, chargement des questions faciles.", Toast.LENGTH_SHORT).show();
                return questionGenerator.getEasyQuestion(currentQuestionIndex);
        }
    }

    private void handleQuestionLoadError() {
        questionNumber.setText("Fin des questions");
        questionText.setText("Toutes les questions ont été répondues.");
        nextButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }

    private void updateUIWithQuestion(QuestionGenerator.Question question) {
        questionNumber.setText("Question " + (currentQuestionIndex + 1) + " - " + difficulty);
        questionText.setText(question.questionText);
        explanationText.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);
        answerEditText.setEnabled(true);
        answerEditText.setText("");
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        loadQuestion();
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentQuestionIndex", currentQuestionIndex);
        outState.putString("game", game);
        outState.putString("difficulty", difficulty);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex");
        game = savedInstanceState.getString("game");
        difficulty = savedInstanceState.getString("difficulty");
        loadQuestion();
    }

    private void onBackButtonClick() {
        // Retour propre à l'activité précédente
        Intent intent = new Intent(this, ChooseLevel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
