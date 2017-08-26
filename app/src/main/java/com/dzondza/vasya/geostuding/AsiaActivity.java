package com.dzondza.vasya.geostuding;

import android.os.Bundle;

/**
 * Asia's Quiz
 */

public class AsiaActivity extends BaseCountryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        mQuestionMechanism.create(this.getResources().getStringArray(R.array.asia_capitals),
                this.getResources().getStringArray(R.array.asia_countries));
    }
}