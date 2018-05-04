package com.example.rohit.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    /**
     * Key for the messages that are being passed while throwing intent
     */
    public static final String EXTRA_MESSAGE = "displayScore";
    public static final String EXTRA_MESSAGE2 = "displayUserName";

    /**
     * ArrayList for holding the user answers
     */
    ArrayList<String> allUserAnswers = new ArrayList<String>();

    /**
     * Global variables for holding user name
     */
    String providedUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        /**
         * Get the Intent that started this activity and extract the string.
         */
        Intent intent = getIntent();
        providedUserName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        /**
         * Capture the layout's TextView and set the string as its text
         */
        TextView textView = findViewById(R.id.textView);

        /**
         * Check if user name is provided or not.
         * Then display accordingly.
         */
        if (TextUtils.isEmpty(providedUserName)) {
            textView.setText(getString(R.string.noUserName));
        } else {
            String gotName = getString(R.string.withUserName) + providedUserName ;
            textView.setText(gotName);
//            textView.setText("Name: " + providedUserName);
        }
    }

    /**
     * Get the button's status
     */
    public void showResults(View view) {
        // Initialize a variable of ArrayList to hold user answers.
        ArrayList finalAnswerList = userAnswers();

        // Function call; to get the result of the quiz.
        int userScore = userResult(finalAnswerList);

        // Variable to hold the toast message.
        String displayToast = getString(R.string.toastMessagePart1) + userScore + getString(R.string.toastMessagePart2);
        // Display the result with a toast message.
        Toast.makeText(this, displayToast, Toast.LENGTH_SHORT).show();

        // Throw intent to start another activity so as to display more details of the results
        Intent intentResult = new Intent(this, ResultActivity.class);
        intentResult.putExtra(EXTRA_MESSAGE, String.valueOf(userScore));
        intentResult.putExtra(EXTRA_MESSAGE2, providedUserName);

        // Start Result Activity.
        startActivity(intentResult);

    }

    /**
     * This function adds the user answer choices to a ArrayList.
     * @return an array list with all the user answers in it.
     */

    public ArrayList userAnswers () {

        /**
         * Get the first question's answer choice of user.
         */
        RadioGroup firstRadioGroupObject = (RadioGroup) findViewById(R.id.firstRadioGroup);
        int firstUserAnswerId = firstRadioGroupObject.getCheckedRadioButtonId();
        RadioButton firstRadioButtonObject = (RadioButton) firstRadioGroupObject.findViewById(firstUserAnswerId);
        String firstSelectedButton = (String) firstRadioButtonObject.getText();
        allUserAnswers.add(firstSelectedButton);

        /**
         * Get the second question's answer choice of user.
         */
        RadioGroup secondRadioGroupObject = (RadioGroup) findViewById(R.id.secondRadioGroup);
        int secondUserAnswerId = secondRadioGroupObject.getCheckedRadioButtonId();
        RadioButton secondRadioButtonObject = (RadioButton) secondRadioGroupObject.findViewById(secondUserAnswerId);
        String secondSelectedButton = (String) secondRadioButtonObject.getText();
        allUserAnswers.add(secondSelectedButton);

        /**
         * Get the Third question's answer choice of user.
         */
        RadioGroup thirdRadioGroupObject = (RadioGroup) findViewById(R.id.thirdRadioGroup);
        int thirdUserAnswerId = thirdRadioGroupObject.getCheckedRadioButtonId();
        RadioButton thirdRadioButtonObject = (RadioButton) thirdRadioGroupObject.findViewById(thirdUserAnswerId);
        String thirdSelectedButton = (String) thirdRadioButtonObject.getText();
        allUserAnswers.add(thirdSelectedButton);

        /**
         * Get the fourth question's answer choice of user.
         */
        RadioGroup fourthRadioGroupObject = (RadioGroup) findViewById(R.id.fourthRadioGroup);
        int fourthUserAnswerId = fourthRadioGroupObject.getCheckedRadioButtonId();
        RadioButton fourthRadioButtonObject = (RadioButton) fourthRadioGroupObject.findViewById(fourthUserAnswerId);
        String fourthSelectedButton = (String) fourthRadioButtonObject.getText();
        allUserAnswers.add(fourthSelectedButton);

        /**
         * Get the fifth question's answer choice of user.
         */
        EditText editTextObject = (EditText) findViewById(R.id.tita);
        String fifthAnswer = editTextObject.getText().toString();
        allUserAnswers.add(fifthAnswer);

        /**
         * Get the sixth question's answer choice of user.
         */
        CheckBox checkBoxFirstObject = (CheckBox) findViewById(R.id.sixthQnsOptionOne);
        boolean hasCheckedOdisha = checkBoxFirstObject.isChecked();
        allUserAnswers.add(String.valueOf(hasCheckedOdisha));

        CheckBox checkBoxSecondObject = (CheckBox) findViewById(R.id.sixthQnsOptionTwo);
        boolean hasCheckedAssam = checkBoxSecondObject.isChecked();
        allUserAnswers.add(String.valueOf(hasCheckedAssam));

        CheckBox checkBoxThirdObject = (CheckBox) findViewById(R.id.sixthQnsOptionThree);
        boolean hasCheckedTripura = checkBoxThirdObject.isChecked();
        allUserAnswers.add(String.valueOf(hasCheckedTripura));

        CheckBox checkBoxFourthObject = (CheckBox) findViewById(R.id.sixthQnsOptionFour);
        boolean hasCheckedTamilNadu = checkBoxFourthObject.isChecked();
        allUserAnswers.add(String.valueOf(hasCheckedTamilNadu));

        return allUserAnswers;
    }

    /**
     * This function compares the user answers with the correct answers.
     * @param answerList takes user answer list
     * @return the score. i.e the no of correct answers.
     */
    public int userResult(ArrayList answerList) {

        // Declare he correct answers in a list.
        String[] correctAnswersArray = {"Narendra Modi", "Naveen Patnaik", "Arun Jaitley", "Nirmala Sitharaman", "Tiger",
                "true", "true", "true", "true"};

        int score = 0;
        // Compares the answers (from 1 till 5). i.e only for 5 questions.
        for (int i = 0; i< 5; i++) {
            if (answerList.get(i).equals(correctAnswersArray[i])) {
                score += 1;
            } else {
                score += 0;
            }
        }

        int count = 0;
        // Checks the sixth answer choice.
        for (int i = 5; i < 9; i++) {
            if (answerList.get(i).equals(correctAnswersArray[i])) {
                count += 1;
                // if all the four choices are true then increase the score by 1.
                // i.e the answer is only true if all the 4 check box is checked.
                if (count == 4) {
                    score += 1;
                } else {
                    score += 0;
                }
            } else {
                score += 0;
            }

        }
        return score;
    }

}