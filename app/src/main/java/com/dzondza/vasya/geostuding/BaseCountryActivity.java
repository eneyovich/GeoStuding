package com.dzondza.vasya.geostuding;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Base quiz activity
 */

public abstract class BaseCountryActivity extends AppCompatActivity {

    QuestionMechanism mQuestionMechanism;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //screen orientation portrait for all quizzes
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //mechanism for all quizzes
        mQuestionMechanism = new QuestionMechanism(this);
    }
}