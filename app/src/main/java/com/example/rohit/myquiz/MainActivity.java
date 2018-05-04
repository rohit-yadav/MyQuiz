package com.example.rohit.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Key for the message that is being sent while throwing an Intent.
    public static final String EXTRA_MESSAGE = "keyUserInput";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This function Starts the quiz.
     */
    public void start(View view) {
       Intent intent = new Intent(this, DisplayMessageActivity.class);
       EditText editText = (EditText) findViewById(R.id.editText);
       String message = editText.getText().toString();
        // Checking if user have has provided the name.
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, getString(R.string.toastMessageForNoName), Toast.LENGTH_SHORT).show();
        } else {
            message = message;
        }
        // Starting new activity with a the user name.
       intent.putExtra(EXTRA_MESSAGE, message);
       startActivity(intent);
    }
}
