package com.dzondza.vasya.geostuding;

import android.os.Bundle;

/*
Africa Quiz
 */
public class AfricaActivity extends BaseCountryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        mQuestionMechanism.create(this.getResources().getStringArray(R.array.africa_capitals),
                this.getResources().getStringArray(R.array.africa_countries));
    }
}