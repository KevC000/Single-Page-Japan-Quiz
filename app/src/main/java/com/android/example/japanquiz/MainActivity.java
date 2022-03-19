package com.android.example.japanquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.example.japanquiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Onclick for the submit button
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswers(view);
            }
        });

        //Onclick for the reset button
        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAnswers(view);
            }
        });
    }

    //Variables for the points
    int numberOfQuestions = 5;
    int pointPerQuestion = 4;
    int maxPoints = numberOfQuestions * pointPerQuestion;
    int points = 0;

    //Method that submits the quiz
    public void submitAnswers(View view) {
        //Calculate points for each question
        points = 0;
        points += calculateQ1Points() +
                calculateQ2Points() +
                calculateQ3Points() +
                calculateQ4Points() +
                calculateQ5Points();
        //Show answers
        binding.textQ1Answer.setText(R.string.q1_correct_message);
        binding.textQ2Answer.setText(R.string.q2_correct_message);
        binding.textQ3Answer.setText(R.string.q3_correct_message);
        binding.textQ4Answer.setText(R.string.q4_correct_message);
        binding.textQ5Answer.setText(R.string.q5_correct_message);

        //Toast for the points
        Toast.makeText(this, "You scored " + points + "/" + maxPoints + "!", Toast.LENGTH_LONG).show();
    }

    //Reset points to 0 and hide answers
    public void resetAnswers(View view) {
        points = 0;
        binding.textQ1Answer.setText("");
        binding.textQ2Answer.setText("");
        binding.textQ3Answer.setText("");
        binding.textQ4Answer.setText("");
        binding.textQ5Answer.setText("");
        Toast.makeText(this, R.string.reset_button_message, Toast.LENGTH_LONG).show();
    }

    //Calculate points for question 1. Either 4 or 0 points.
    private int calculateQ1Points() {
        if (binding.radioQ1Correct.isChecked()) {
            return 4;
        } else {
            return 0;
        }
    }

    //Calculate points for question 2.
    // 1 point for each correct checked except for the wrong answer
    //Scoring system could be better xD
    private int calculateQ2Points() {
        int score = 0;
        if (binding.checkQ2A1.isChecked()) {
            score++;
        }
        if (binding.checkQ2A3.isChecked()) {
            score++;
        }
        if (binding.checkQ2A4.isChecked()) {
            score++;
        }
        if (!binding.checkQ2A2.isChecked()) {
            score++;
        }
        return score;
    }

    //Calculate points for question 3. Either 4 or 0 points.
    private int calculateQ3Points() {
        if (binding.radioQ3Correct.isChecked()) {
            return 4;
        } else {
            return 0;
        }
    }

    //Calculate points for question 4. Either 4 or 0 points.
    private int calculateQ4Points() {
        if (binding.radioQ4Correct.isChecked()) {
            return 4;
        } else {
            return 0;
        }
    }

    //Calculate points for question 1. Either 4 or 0 points.
    //Not case sensitive and trim() in case there are extra spaces
    private int calculateQ5Points() {
        if (binding.editTextQ5.getText().toString().trim().equalsIgnoreCase("nihon") || binding.editTextQ5.getText().toString().trim().equalsIgnoreCase("nippon")) {
            return 4;
        } else {
            return 0;
        }
    }

}