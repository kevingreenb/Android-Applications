package com.example.android.quiz;

/**
 * Created by KevinGreen on 2/8/18.
 */

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Question> list = new ArrayList<>();

    public QuestionBank() {
        list.add(new Question("Which of the following is an example of valid jQuery code?\n",
                "a) $('main-nav').show();\n",
                "b) document.querySelector('#main-nav');\n",
                "c) $('#main-nav').hide();\n",
                "d) document.getElementById('main-nav').style.display = 'inline-block';",
                "c",
                "RADIO"));

        list.add(new Question( "What will the following code do? \n"+
                "$('#price-list li').html('<strong>$4.99</strong>');\n",
                "a) Select the element with the ID of price list and set its HTML content to $4.99\n",
                "b) Select the <strong> tag and set its HTML content to $4.99\n",
                "c) Select the list item nested within #price-list and set its HTML content to $4.99\n",
                "d) To make pizza more delicious",
                "c",
                "BUTTON"));

        list.add(new Question( "Which of the following is a primary reason the jQuery library was developed for JavaScript?\n",
                "a) To address inconsistencies in the way JavaScript was implemented in different browsers.\n",
                "b) To provide clear guidelines for web application architecture.\n",
                "c) To make developer's life easier.\n",
                "d) To compete against Java.",
                "ac",
                "CHECK"));

        list.add(new Question( "Fill in the text below with the method that would hide the element #my-element.",
                "",
                "",
                "",
                "",
                "hide()",
                "TEXT"));
    }



}