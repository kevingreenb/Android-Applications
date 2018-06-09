package com.example.android.quiz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Handler;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    EditText text_Entry;
    TextView questionLabel, finalLabel, answers;
    LinearLayout buttonGroup, checkGroup;
    RadioButton radio_1, radio_2, radio_3, radio_4;
    Button btn_a, btn_b, btn_c, btn_d, nextQ, tryAgain;
    CheckBox ck_a, ck_b, ck_c, ck_d;
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
        text_Entry = findViewById(R.id.text_entry);
        ck_a = findViewById(R.id.check_a);
        ck_b = findViewById(R.id.check_b);
        ck_c = findViewById(R.id.check_c);
        ck_d = findViewById(R.id.check_d);
        btn_a = findViewById(R.id.button_a);
        btn_b = findViewById(R.id.button_b);
        btn_c = findViewById(R.id.button_c);
        btn_d = findViewById(R.id.button_d);
        nextQ = findViewById(R.id.submit_answer);
        tryAgain = findViewById(R.id.try_again);
        buttonGroup = findViewById(R.id.button_group);
        radioGroup = findViewById(R.id.radio_group);
        checkGroup = findViewById(R.id.check_group);
        radio_1 = findViewById(R.id.option1_button);
        radio_2 = findViewById(R.id.option2_button);
        radio_3 = findViewById(R.id.option3_button);
        radio_4 = findViewById(R.id.option4_button);
        questionLabel = (TextView) findViewById(R.id.question_text_view);
        finalLabel = (TextView) findViewById(R.id.final_text);
        answers = (TextView) findViewById(R.id.answers_text);
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
            answers.setVisibility(View.VISIBLE);

            answers.setText(fullAnswers);
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
                text_Entry.setVisibility(View.VISIBLE);
                text_Entry.setText("");
                nextQ.setVisibility(View.VISIBLE);
            }
            questionNumber++;
        } else

        {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("You finished the quiz!");
            a_builder.show();
            displayFinalLabel();
            questionLabel.setVisibility(View.INVISIBLE);
            finalLabel.setText("You scored " + score + "%");
            displayTryAgain();
            score = 0;
        }

    }

    //Action for buttons

    public void buttonA(View view) {
        pickedAnswer = "a";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 1000);
    }

    public void buttonB(View view) {
        pickedAnswer = "b";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 1000);
    }

    public void buttonC(View view) {
        pickedAnswer = "c";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 1000);
    }

    public void buttonD(View view) {
        pickedAnswer = "d";
        checkAnswer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 1000);
    }

    public void getSelectedAnswer(View view) {

        if (questionFormat.equals("RADIO")) {
            if (radio_1.isChecked()) {
                pickedAnswer = "a";
                //radio_1.setChecked(false);
            } else if (radio_2.isChecked()) {
                pickedAnswer = "b";
                //radio_2.setChecked(false);
            } else if (radio_3.isChecked()) {
                pickedAnswer = "c";
                //radio_3.setChecked(false);
            } else if (radio_4.isChecked()) {
                pickedAnswer = "d";
                //radio_4.setChecked(false);
            }
            radioGroup.clearCheck();

        } else if (questionFormat.equals("CHECK")) {
            if (ck_a.isChecked()) {
                pickedAnswer = "a";
                ck_a.toggle();
            }
            if (ck_b.isChecked()) {
                pickedAnswer += "b";
                ck_b.toggle();

            }
            if (ck_c.isChecked()) {
                pickedAnswer += "c";
                ck_c.toggle();

            }
            if (ck_d.isChecked()) {
                pickedAnswer += "d";
                ck_d.toggle();

            }
        } else if (questionFormat.equals("TEXT")) {
            testText = text_Entry.getText().toString();
            pickedAnswer = text_Entry.getText().toString();
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
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Right Answer!");
            a_builder.show();
            score += 25;
        } else {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Wrong Answer!");
            a_builder.show();
        }
        pickedAnswer = "";
        correctAnswer = "";

    }

    // Hide all the Input Views
    private void hideAll() {
        answers.setVisibility(View.INVISIBLE);
        buttonGroup.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        checkGroup.setVisibility(View.INVISIBLE);
        nextQ.setVisibility(View.INVISIBLE);
        text_Entry.setVisibility(View.INVISIBLE);
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
        text_Entry.setVisibility(View.VISIBLE);
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