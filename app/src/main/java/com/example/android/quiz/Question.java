package com.example.android.quiz;

/**
 * Created by KevinGreen on 2/8/18.
 * Credit to Tiberius for help and code https://github.com/Causaelity
 */
import java.util.HashMap;
import java.util.Map;

// Define the question Types
enum typeOfQuestion {
    BUTTON, RADIO, CHECKBOX, TEXTENTRY
}

public class Question {
    public Map questionSet = new HashMap<>();

    public Question(String questionText,  String answer1, String answer2, String answer3, String answer4, String correctAnswer, typeOfQuestion type){
        questionSet.put("question", questionText);
        questionSet.put("a", answer1);
        questionSet.put("b", answer2);
        questionSet.put("c", answer3);
        questionSet.put("d", answer4);
        questionSet.put("answer", correctAnswer);
        questionSet.put("format", type);



    }

}
