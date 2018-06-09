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

import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    EditText text_Entry;
    LinearLayout buttonGroup, checkGroup;
    RadioButton radio_1, radio_2, radio_3, radio_4;
    Button btn_a, btn_b, btn_c, btn_d, nextQ;
    CheckBox ck_a, ck_b, ck_c, ck_d;
    QuestionBank allQuestions = new QuestionBank();
    String pickedAnswer = "", correctAnswer = "", questionFormat = "", testText = "";
    final int numberOfQuestions = allQuestions.list.size();
    final Handler handler = new Handler();
    int questionNumber = 0;
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
        buttonGroup = findViewById(R.id.button_group);
        radioGroup = findViewById(R.id.radio_group);
        checkGroup = findViewById(R.id.check_group);
        radio_1 = findViewById(R.id.option1_button);
        radio_2 = findViewById(R.id.option2_button);
        radio_3 = findViewById(R.id.option3_button);
        radio_4 = findViewById(R.id.option4_button);
        hideAll();
        nextQuestion();
    }

    private void nextQuestion() {
        hideAll();

        if (questionNumber <= numberOfQuestions - 1) {
            TextView questionLabel = (TextView) findViewById(R.id.question_text_view);
            String fullQuestion = allQuestions.list.get(questionNumber).questionSet.get("question").toString();
            fullQuestion += "\n\na) " + allQuestions.list.get(questionNumber).questionSet.get("a");
            fullQuestion += "\nb) " + allQuestions.list.get(questionNumber).questionSet.get("b");
            fullQuestion += "\nc) " + allQuestions.list.get(questionNumber).questionSet.get("c");
            fullQuestion += "\nd) " + allQuestions.list.get(questionNumber).questionSet.get("d");
            correctAnswer = allQuestions.list.get(questionNumber).questionSet.get("answer").toString();

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
            restart();
        }

    }

    //Action for button

    public void buttonA(View view) {
        pickedAnswer = "a";
        checkAnswer();
        nextQuestion();
    }

    public void buttonB(View view) {
        pickedAnswer = "b";
        checkAnswer();
        nextQuestion();
    }

    public void buttonC(View view) {
        pickedAnswer = "c";
        checkAnswer();
        nextQuestion();
    }

    public void buttonD(View view) {
        pickedAnswer = "d";
        checkAnswer();
        nextQuestion();
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
    /*
        else if (questionFormat.equals("BUTTON")){

            switch(view.getId()) {
                case R.id.button_a:
                    pickedAnswer = "a";
                    break;
                case R.id.button_b:
                    pickedAnswer = "b";
                    break;
                case R.id.button_c:
                    pickedAnswer = "c";
                    break;
                case R.id.button_d:
                    pickedAnswer = "d";
                    break;
                default:
                    break;
            }

        }
        */


    public void submitAnswer(View view) {

        getSelectedAnswer(view);
        if (noSelection) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Please select an answer!");
            a_builder.show();
            noSelection = false;
        } else {
            hideAll();
            checkAnswer();
            nextQuestion();
        }

    }

    public void checkAnswer() {
        if (correctAnswer.equals(pickedAnswer)) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Right Answer!");
            a_builder.show();
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
        buttonGroup.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        checkGroup.setVisibility(View.INVISIBLE);
        nextQ.setVisibility(View.INVISIBLE);
        text_Entry.setVisibility(View.INVISIBLE);
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

    public void restart() {
        questionNumber = 0;
        hideAll();
        //Collections.shuffle(allQuestions.list);
        nextQuestion();

    }


}