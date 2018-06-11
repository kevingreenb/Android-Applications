package com.example.android.quiz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    EditText textEntry;
    TextView questionLabel, finalLabel, answersText;
    LinearLayout buttonGroup, checkGroup;
    RadioButton radioA, radioB, radioC, radioD;
    Button buttonA, buttonB, buttonC, buttonD, nextQ, tryAgain;
    CheckBox checkA, checkB, checkC, checkD;
    QuestionBank allQuestions = new QuestionBank();
    String pickedAnswer = "", correctAnswer = "", questionFormat = "", testText = "";
    final int numberOfQuestions = allQuestions.list.size();
    final Handler handler = new Handler();
    int questionNumber = 0, score = 0;
    boolean noSelection = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textEntry = findViewById(R.id.textEntry);
        checkA = findViewById(R.id.checkA);
        checkB = findViewById(R.id.checkB);
        checkC = findViewById(R.id.checkC);
        checkD = findViewById(R.id.checkD);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        nextQ = findViewById(R.id.submit_answer);
        tryAgain = findViewById(R.id.tryAgain);
        buttonGroup = findViewById(R.id.buttonGroup);
        radioGroup = findViewById(R.id.radioGroup);
        checkGroup = findViewById(R.id.checkGroup);
        radioA = findViewById(R.id.radioA);
        radioB = findViewById(R.id.radioB);
        radioC = findViewById(R.id.radioC);
        radioD = findViewById(R.id.radioD);
        questionLabel = findViewById(R.id.questionText);
        finalLabel = findViewById(R.id.finalLabel);
        answersText = findViewById(R.id.answersText);
        hideAll();
        nextQuestion();
    }

    public void nextQuestion() {
        hideAll();


        if (questionNumber <= numberOfQuestions - 1) {

            String fullQuestion = allQuestions.list.get(questionNumber).questionSet.get("question").toString();
            String fullAnswers = allQuestions.list.get(questionNumber).questionSet.get("a").toString();
            fullAnswers += allQuestions.list.get(questionNumber).questionSet.get("b");
            fullAnswers += allQuestions.list.get(questionNumber).questionSet.get("c");
            fullAnswers += allQuestions.list.get(questionNumber).questionSet.get("d");
            correctAnswer = allQuestions.list.get(questionNumber).questionSet.get("answer").toString();
            questionLabel.setVisibility(View.VISIBLE);
            answersText.setVisibility(View.VISIBLE);

            answersText.setText(fullAnswers);
            questionLabel.setText(fullQuestion);

            if (allQuestions.list.get(questionNumber).questionSet.get("format").toString().equals("RADIO")) {
                questionFormat = "RADIO";
                displayRadio();
                nextQ.setVisibility(View.VISIBLE);
            } else if (allQuestions.list.get(questionNumber).questionSet.get("format").toString().equals("BUTTON")) {
                questionFormat = "BUTTON";
                displayButton();

            } else if (allQuestions.list.get(questionNumber).questionSet.get("format").toString().equals("CHECK")) {
                questionFormat = "CHECK";
                displayCheck();
                nextQ.setVisibility(View.VISIBLE);
            } else if (allQuestions.list.get(questionNumber).questionSet.get("format").toString().equals("TEXT")) {
                questionFormat = "TEXT";
                displayText();
                textEntry.setVisibility(View.VISIBLE);
                textEntry.setText("");
                nextQ.setVisibility(View.VISIBLE);
            }
            questionNumber++;
        } else

        {
            questionLabel.setVisibility(View.INVISIBLE);
            String finalText = "You scored " + score + "%";
            Toast toast = Toast.makeText(getApplicationContext(), finalText, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finalLabel.setText(finalText);
            displayFinalLabel();
            displayTryAgain();
            score = 0;
        }

    }

    //Action for buttons

    public void pressedA(View view) {
        pickedAnswer = "a";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }

    public void pressedB(View view) {
        pickedAnswer = "b";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }

    public void pressedC(View view) {
        pickedAnswer = "c";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }

    public void pressedD(View view) {
        pickedAnswer = "d";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }

    public void getSelectedAnswer(View view) {

        if (questionFormat.equals("RADIO")) {
            if (radioA.isChecked()) {
                pickedAnswer = "a";
            } else if (radioB.isChecked()) {
                pickedAnswer = "b";
            } else if (radioC.isChecked()) {
                pickedAnswer = "c";
            } else if (radioD.isChecked()) {
                pickedAnswer = "d";
            }
            radioGroup.clearCheck();

        } else if (questionFormat.equals("CHECK")) {
            if (checkA.isChecked()) {
                pickedAnswer = "a";
                checkA.toggle();
            }
            if (checkB.isChecked()) {
                pickedAnswer += "b";
                checkB.toggle();

            }
            if (checkC.isChecked()) {
                pickedAnswer += "c";
                checkC.toggle();

            }
            if (checkD.isChecked()) {
                pickedAnswer += "d";
                checkD.toggle();

            }
        } else if (questionFormat.equals("TEXT")) {
            testText = textEntry.getText().toString();
            pickedAnswer = textEntry.getText().toString();
            Log.d("picked answer", testText);

        }
        if (pickedAnswer.equals("")) {
            noSelection = true;
        }


    }

    //Action that runs with Next Question is clicked
    public void submitAnswer(View view) {

        getSelectedAnswer(view);
        if (noSelection) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Please input your answer");
            a_builder.show();
            noSelection = false;
        } else {
            hideAll();
            checkAnswer();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextQuestion();
                }
            }, 1000);
        }

    }

    public void checkAnswer() {
        if (correctAnswer.equals(pickedAnswer)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Right Answer!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            score += 25;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        pickedAnswer = "";
        correctAnswer = "";

    }

    // Hide all the Input Views
    private void hideAll() {
        answersText.setVisibility(View.INVISIBLE);
        buttonGroup.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        checkGroup.setVisibility(View.INVISIBLE);
        nextQ.setVisibility(View.INVISIBLE);
        textEntry.setVisibility(View.INVISIBLE);
        tryAgain.setVisibility(View.INVISIBLE);
        finalLabel.setVisibility(View.INVISIBLE);
    }

    private void displayRadio() {
        radioGroup.setVisibility(View.VISIBLE);
    }

    private void displayButton() {
        buttonGroup.setVisibility(View.VISIBLE);
    }

    private void displayCheck() {
        checkGroup.setVisibility(View.VISIBLE);
    }

    private void displayText() {
        textEntry.setVisibility(View.VISIBLE);
    }

    private void displayTryAgain() {
        tryAgain.setVisibility(View.VISIBLE);
    }

    private void displayFinalLabel() {
        finalLabel.setVisibility(View.VISIBLE);
    }

    public void restart(View view) {
        questionNumber = 0;
        hideAll();
        nextQuestion();

    }


}