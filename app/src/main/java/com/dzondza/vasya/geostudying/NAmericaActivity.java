package com.dzondza.vasya.geostudying;

import android.os.Bundle;

/*
North America Quiz
 */
public class NAmericaActivity extends BaseCountryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        mQuestionMechanism.create(this.getResources().getStringArray(R.array.north_america_capitals),
                this.getResources().getStringArray(R.array.north_america_countries));
    }
}