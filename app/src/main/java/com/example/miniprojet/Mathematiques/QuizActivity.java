package com.example.miniprojet.Mathematiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojet.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ListView questionsListView;
    private List<MultiplicationQuestion> questions = new ArrayList<>();
    private List<Integer> questionIndices = new ArrayList<>();
    private QuestionsAdapter adapter;
    private int table;
    private ArrayList<String> previousAnswers;
    private boolean isCorrectionMode;
    private boolean[] previousResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_quiz);

        table = getIntent().getIntExtra("table", 1);
        isCorrectionMode = getIntent().hasExtra("previousAnswers");

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView correctionHint = findViewById(R.id.correctionHint);
        Button validateButton = findViewById(R.id.validateButton);

        titleTextView.setText(isCorrectionMode ? "Correction - Table de " + table : "Table de " + table);

        if (isCorrectionMode) {
            correctionHint.setVisibility(View.VISIBLE);
            validateButton.setText("Valider les corrections");
            previousAnswers = getIntent().getStringArrayListExtra("previousAnswers");
            previousResults = getIntent().getBooleanArrayExtra("previousResults");
            questionIndices = getIntent().getIntegerArrayListExtra("questionIndices");
        }

        generateQuestions();
        setupListView();
        setupValidateButton();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        table = intent.getIntExtra("table", 1);
        isCorrectionMode = intent.hasExtra("previousAnswers");

        if (isCorrectionMode) {
            previousAnswers = intent.getStringArrayListExtra("previousAnswers");
            previousResults = intent.getBooleanArrayExtra("previousResults");
            questionIndices = intent.getIntegerArrayListExtra("questionIndices");
        }

        generateQuestions();
        adapter.notifyDataSetChanged();
    }

    private void generateQuestions() {
        questions.clear();
        List<MultiplicationQuestion> orderedQuestions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            orderedQuestions.add(new MultiplicationQuestion(table, i));
        }

        if (isCorrectionMode && questionIndices != null) {
            for (int index : questionIndices) {
                questions.add(orderedQuestions.get(index));
            }
        } else {
            questions.addAll(orderedQuestions);
            Collections.shuffle(questions);
            questionIndices = new ArrayList<>();
            for (MultiplicationQuestion q : questions) {
                questionIndices.add(q.getMultiplicand() - 1);
            }
        }
    }

    private void setupListView() {
        questionsListView = findViewById(R.id.questionsListView);
        adapter = new QuestionsAdapter();
        questionsListView.setAdapter(adapter);
    }

    private void setupValidateButton() {
        Button validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(v -> validateAnswers());
    }

    private void validateAnswers() {
        ArrayList<String> questionsList = new ArrayList<>();
        ArrayList<String> answersList = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();
        boolean[] resultsArray = new boolean[questions.size()];
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            View itemView = questionsListView.getChildAt(i);
            if (itemView != null) {
                TextView questionTV = itemView.findViewById(R.id.questionTextView);
                EditText answerET = itemView.findViewById(R.id.answerEditText);

                String question = questionTV.getText().toString();
                String answer = answerET.getText().toString();
                int correctAnswer = questions.get(i).getCorrectAnswer();

                questionsList.add(question);
                answersList.add(answer);
                correctAnswers.add(String.valueOf(correctAnswer));

                try {
                    boolean isCorrect = Integer.parseInt(answer) == correctAnswer;
                    resultsArray[i] = isCorrect;
                    if (isCorrect) correctCount++;
                } catch (NumberFormatException e) {
                    resultsArray[i] = false;
                }
            }
        }

        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putStringArrayListExtra("questions", questionsList);
        resultIntent.putStringArrayListExtra("answers", answersList);
        resultIntent.putStringArrayListExtra("correctAnswers", correctAnswers);
        resultIntent.putExtra("score", correctCount);
        resultIntent.putExtra("total", questions.size());
        resultIntent.putExtra("table", table);
        resultIntent.putExtra("previousResults", resultsArray);
        resultIntent.putIntegerArrayListExtra("questionIndices", (ArrayList<Integer>) questionIndices);
        startActivity(resultIntent);
        finish();
    }

    private class QuestionsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public Object getItem(int position) {
            return questions.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(QuizActivity.this)
                        .inflate(R.layout.item_question, parent, false);
                holder = new ViewHolder();
                holder.questionTextView = convertView.findViewById(R.id.questionTextView);
                holder.answerEditText = convertView.findViewById(R.id.answerEditText);
                holder.itemLayout = convertView.findViewById(R.id.itemLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            MultiplicationQuestion question = questions.get(position);
            holder.questionTextView.setText(question.getQuestionText());

            if (isCorrectionMode && previousAnswers != null && position < previousAnswers.size()) {
                holder.answerEditText.setText(previousAnswers.get(position));

                if (previousResults != null && position < previousResults.length) {
                    if (previousResults[position]) {
                        holder.itemLayout.setBackgroundResource(R.drawable.correct_item_background);
                    } else {
                        holder.itemLayout.setBackgroundResource(R.drawable.incorrect_item_background);
                    }
                }
            } else {
                holder.answerEditText.setText("");
                holder.itemLayout.setBackgroundResource(R.drawable.item_background);
            }

            return convertView;
        }

        class ViewHolder {
            TextView questionTextView;
            EditText answerEditText;
            LinearLayout itemLayout;
        }
    }

    private static class MultiplicationQuestion {
        private final int multiplier;
        private final int multiplicand;

        public MultiplicationQuestion(int multiplier, int multiplicand) {
            this.multiplier = multiplier;
            this.multiplicand = multiplicand;
        }

        public String getQuestionText() {
            return multiplier + " × " + multiplicand + " = ";
        }

        public int getCorrectAnswer() {
            return multiplier * multiplicand;
        }

        public int getMultiplicand() {
            return multiplicand;
        }
    }
}
