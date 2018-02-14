package com.example.android.quiz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    QuestionBank allQuestions = new QuestionBank();
    String pickedAnswer = "", correctAnswer = "";
    final int numberOfQuestions = allQuestions.list.size();
    int questionNumber = 0;
    boolean noSelection = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextQuestion();
    }

    private void nextQuestion() {
        uncheckAll();
        if (questionNumber <= numberOfQuestions - 1) {
            TextView questionLabel = (TextView) findViewById(R.id.question_text_view);
            String fullQuestion = allQuestions.list.get(questionNumber).questionSet.get("question").toString();
            fullQuestion += "\n\na) " + allQuestions.list.get(questionNumber).questionSet.get("a");
            fullQuestion += "\nb) " + allQuestions.list.get(questionNumber).questionSet.get("b");
            fullQuestion += "\nc) " + allQuestions.list.get(questionNumber).questionSet.get("c");
            fullQuestion += "\nd) " + allQuestions.list.get(questionNumber).questionSet.get("d");
            correctAnswer = allQuestions.list.get(questionNumber).questionSet.get("answer").toString();

            questionLabel.setText(fullQuestion);
            questionNumber++;
        } else {
            restart();
        }
    }

    public void getSelectedAnswer() {
        RadioButton radio_1 = (RadioButton) findViewById(R.id.option1_button);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.option2_button);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.option3_button);
        RadioButton radio_4 = (RadioButton) findViewById(R.id.option4_button);


        if (radio_1.isChecked()) {
            pickedAnswer = "a";
            radio_1.setChecked(false);
        } else if (radio_2.isChecked()) {
            pickedAnswer = "b";
            radio_2.setChecked(false);
        } else if (radio_3.isChecked()) {
            pickedAnswer = "c";
            radio_3.setChecked(false);
        } else if (radio_4.isChecked()) {
            pickedAnswer = "d";
            radio_4.setChecked(false);
        } else {
            noSelection = true;
        }


    }

    public void submitAnswer(View view) {
        getSelectedAnswer();
        if (noSelection) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Please select an answer!");
            a_builder.show();
            noSelection = false;
        } else {
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


    public void restart() {
        questionNumber = 0;
        //Collections.shuffle(allQuestions.list);
        nextQuestion();

    }

    public void uncheckAll(){
        RadioButton radio_1 = (RadioButton) findViewById(R.id.option1_button);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.option2_button);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.option3_button);
        RadioButton radio_4 = (RadioButton) findViewById(R.id.option4_button);
        radio_1.setChecked(false);
        radio_2.setChecked(false);
        radio_3.setChecked(false);
        radio_4.setChecked(false);

    }
}
