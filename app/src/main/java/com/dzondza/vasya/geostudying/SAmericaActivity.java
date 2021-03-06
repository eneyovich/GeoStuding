package com.dzondza.vasya.geostudying;

import android.os.Bundle;

/**
 * South America's Quiz
 */

public class SAmericaActivity extends BaseCountryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        mQuestionMechanism.create(this.getResources().getStringArray(R.array.south_america_capitals),
                this.getResources().getStringArray(R.array.south_america_countries));
    }
}