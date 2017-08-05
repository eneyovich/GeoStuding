package com.dzondza.vasya.geostudying;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  shows how each quiz works
 */

public class QuestionMechanism {
    private int mCountriesIndex;
    private TextView mQuestionTextView;
    private Button mButtonAnswer0;
    private Button mButtonAnswer1;
    private Button mButtonAnswer2;
    private Button mButtonAnswer3;
    private Button[] mButtonAnswersArray;

    private TextView mTrueScoreTextView;
    private TextView mWrongScoreTextView;

    private Button mNextButton;
    private Activity mActivity;

    /*
    lists of european capitals & countries
     */
    private List<String> mCapitalsList;
    private List<String> mCountriesList;

    private String mQuestionCountry;

    private int countRightAnswers = 0;
    private int countWrongAnswers = 0;

    private int firstAnswer = 0;

    //random values for questions and answers
    private int random;
    private ArrayList<String> randomArray = new ArrayList<>();
    private Set<Integer> randomSet = new HashSet<>();


    QuestionMechanism(Activity activity) {
        mActivity = activity;
    }


    //creates buttons, textViews, lists of capitals and countries, sets buttons' listeners
    void create(String[] capitalsArray, String[] countriesArray) {
        mQuestionTextView = (TextView) mActivity.findViewById(R.id.question_textview);

        mTrueScoreTextView = (TextView) mActivity.findViewById(R.id.true_score_textview);
        mWrongScoreTextView = (TextView) mActivity.findViewById(R.id.wrong_score_textview);

        mButtonAnswer0 = (Button) mActivity.findViewById(R.id.button_answer0);
        mButtonAnswer1 = (Button) mActivity.findViewById(R.id.button_answer1);
        mButtonAnswer2 = (Button) mActivity.findViewById(R.id.button_answer2);
        mButtonAnswer3 = (Button) mActivity.findViewById(R.id.button_answer3);

        mButtonAnswersArray = new Button[]{mButtonAnswer0, mButtonAnswer1,
                mButtonAnswer2, mButtonAnswer3};

        mNextButton = (Button) mActivity.findViewById(R.id.next_button);

        mCapitalsList = Arrays.asList(capitalsArray);
        mCountriesList = Arrays.asList(countriesArray);

        mCountriesIndex = -1;
        openNextQuestion();

        //sets listeners for all buttons with answers
        for (Button answerButton: mButtonAnswersArray) {
            buttonListener(answerButton);
        }

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextQuestion();
            }
        });
    }

    private void openNextQuestion() {
        mCountriesIndex++;
        if (mCountriesIndex == mCapitalsList.size()) {
            mCountriesIndex = 0;
        }

        /*
        random question title
         */
        mQuestionCountry = mCountriesList.get(mCountriesIndex);
        mQuestionTextView.setText(new StringBuilder(mActivity.getString(R.string.question_begin))
                .append("\n").append(mQuestionCountry).append(" ?").toString());

        /*
        1 correct answer and 3 random values for 4 buttons
         */
        randomSet.add(mCountriesIndex);
        while (randomSet.size() != 4 ) {
            random = (int) (Math.random() * mCapitalsList.size());
            randomSet.add(random);
        }
        for (int a : randomSet) {
            randomArray.add(mCapitalsList.get(a));
        }

        //buttons' title
        for (int i = 0; i < mButtonAnswersArray.length; i++) {
            mButtonAnswersArray[i].setText(randomArray.get(i));
        }

        //buttons' colors
        for (Button answerButton: mButtonAnswersArray){
            answerButton.setBackgroundResource(R.color.colorButtonStandart);
        }

        //enable buttons
        for (Button answerButton: mButtonAnswersArray){
            answerButton.setEnabled(true);
        }


        randomSet.clear();
        randomArray.clear();

        firstAnswer = 0;
    }

    /*
    Creates listener for button with answer
    firstAnswer = 0 - no answer yet; 1 - first answer is correct; 2 - first answer is wrong
     */
    private void buttonListener(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonIndex = mCapitalsList.indexOf(button.getText().toString());

                if (buttonIndex == mCountriesIndex) {
                    button.setTag(firstAnswer +=1);
                    button.setBackgroundResource(R.color.colorTrueButtonGreen);
                } else {
                    button.setBackgroundResource(R.color.colorFalseButtonRed);
                    button.setTag(firstAnswer +=2);
                }
                button.setEnabled(false);

                if (button.getTag().equals(1)) {

                    countRightAnswers += 1;
                    mTrueScoreTextView.setText(new StringBuilder(mActivity.getResources()
                            .getString(R.string.true_answers)).append(countRightAnswers).toString());
                }
                else if (button.getTag().equals(2)) {

                    countWrongAnswers += 1;
                    mWrongScoreTextView.setText(new StringBuilder(mActivity.getResources()
                            .getString(R.string.false_answers)).append(countWrongAnswers).toString());
                }
            }
        });
    }
}