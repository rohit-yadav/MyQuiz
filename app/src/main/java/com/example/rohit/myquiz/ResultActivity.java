package com.example.rohit.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String finalScore = intent.getStringExtra(DisplayMessageActivity.EXTRA_MESSAGE);
        String finalUserName = intent.getStringExtra(DisplayMessageActivity.EXTRA_MESSAGE2);

        /**
         * Create an object for TextView.
         */
        TextView displayName = (TextView) findViewById(R.id.showUserName);
        TextView displayCorrectAnswers = (TextView) findViewById(R.id.showCorrectAnswers);
        TextView displayWrongAnswers = (TextView) findViewById(R.id.showWorngAnswers);
        TextView displayUserScore = (TextView) findViewById(R.id.showScore);

        // Total no of questions in the quiz.
        int totalNumberOfQuestions = 6;

        // Set the text on the TextView.
        // Detailed display of the result.
        displayName.setText(finalUserName);
        displayCorrectAnswers.setText(finalScore);
        displayWrongAnswers.setText(String.valueOf(totalNumberOfQuestions- Integer.valueOf(finalScore)));
        displayUserScore.setText(finalScore);
    }

    /**
     * This will take the user at the start of the quiz
     * so that the user can take the quiz again.
     */
    public void retakeQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}