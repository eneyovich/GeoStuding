package com.dzondza.vasya.geostudying;

import android.app.Activity;
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

class QuestionMechanism {
    private int mCountriesIndex;
    private TextView mQuestionTextView;
    private Button[] mButtonAnswersArray;

    private TextView mTrueScoreTextView, mWrongScoreTextView;

    private final Activity mActivity;

    /*
    lists of european capitals & countries
     */
    private List<String> mCapitalsList;
    private List<String> mCountriesList;

    private int mFirstAnswer;
    private int mRightAnswers, mWrongAnswers;


    QuestionMechanism(Activity activity) {
        mActivity = activity;
    }


    private Button createButton(int id) {
        return (Button) mActivity.findViewById(id);
    }


    //creates buttons, textViews, lists of capitals and countries, sets buttons' listeners
    void create(String[] capitalsArray, String[] countriesArray) {
        mQuestionTextView = (TextView) mActivity.findViewById(R.id.text_question);

        mTrueScoreTextView = (TextView) mActivity.findViewById(R.id.text_true_score);
        mWrongScoreTextView = (TextView) mActivity.findViewById(R.id.text_wrong_score);

        mButtonAnswersArray = new Button[]{createButton(R.id.button_answer0),
                createButton(R.id.button_answer1), createButton(R.id.button_answer2),
                createButton(R.id.button_answer3)};

        Button mNextButton = createButton(R.id.button_next);

        mCapitalsList = Arrays.asList(capitalsArray);
        mCountriesList = Arrays.asList(countriesArray);

        mCountriesIndex = -1;
        openNextQuestion();

        //sets listeners for all buttons with answers
        for (Button answerButton: mButtonAnswersArray) {
            buttonListener(answerButton);
        }

        mNextButton.setOnClickListener(view -> openNextQuestion());
    }


    private void openNextQuestion() {
        //random values for questions and answers
        ArrayList<String> randomArray = new ArrayList<>();
        Set<Integer> randomSet = new HashSet<>();


        mCountriesIndex++;
        if (mCountriesIndex == mCapitalsList.size()) {
            mCountriesIndex = 0;
        }


        //random question title
        String mQuestionCountry = mCountriesList.get(mCountriesIndex);
        mQuestionTextView.setText(new StringBuilder(mActivity.getString(R.string.question_begin))
                .append("\n").append(mQuestionCountry).append(" ?").toString());


        //1 correct answer and 3 random values for 4 buttons
        randomSet.add(mCountriesIndex);
        while (randomSet.size() != 4 ) {
            int random = (int) (Math.random() * mCapitalsList.size());
            randomSet.add(random);
        }
        for (int a : randomSet) {
            randomArray.add(mCapitalsList.get(a));
        }

        //buttons' titles and colors, enable buttons
        for (int i = 0; i < mButtonAnswersArray.length; i++) {
            mButtonAnswersArray[i].setText(randomArray.get(i));
            mButtonAnswersArray[i].setBackgroundResource(R.color.colorButtonUnpressed);
            mButtonAnswersArray[i].setEnabled(true);
        }


        randomSet.clear();
        randomArray.clear();

        mFirstAnswer = 0;
    }


    /* creates listener for button with answer
       mFirstAnswer = 0 - no answer yet; 1 - first answer is correct; 2 - first answer is wrong
    */
    private void buttonListener(final Button button) {
        button.setOnClickListener(v -> {
            int buttonIndex = mCapitalsList.indexOf(button.getText().toString());

            if (buttonIndex == mCountriesIndex) {
                button.setTag(mFirstAnswer +=1);
                button.setBackgroundResource(R.color.colorButtonTrueGreen);
            } else {
                button.setBackgroundResource(R.color.colorButtonFalseRed);
                button.setTag(mFirstAnswer +=2);
            }
            button.setEnabled(false);

            if (button.getTag().equals(1)) {

                mRightAnswers += 1;
                mTrueScoreTextView.setText(new StringBuilder(mActivity.getResources()
                        .getString(R.string.true_answers)).append(mRightAnswers).toString());
            }
            else if (button.getTag().equals(2)) {

                mWrongAnswers += 1;
                mWrongScoreTextView.setText(new StringBuilder(mActivity.getResources()
                        .getString(R.string.false_answers)).append(mWrongAnswers).toString());
            }
        });
    }
}