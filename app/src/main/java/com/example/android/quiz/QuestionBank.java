package com.example.android.quiz;

/**
 * Created by KevinGreen on 2/8/18.
 */

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Question> list = new ArrayList<>();

    public QuestionBank() {
        list.add(new Question("In what year was Bitcoin first released?", "2008",
                "2009", "2010", "2011", "b"));
        list.add(new Question( "Who is the creator of Bitcoin?", "Batmam", "Trump",
                "Your pizza guy", "Satoshi Nakamoto", "d"));
    }

}
