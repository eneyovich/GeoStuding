package com.dzondza.vasya.geostuding;

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
    private Button mButtonAnswer1;
    private Button mButtonAnswer2;
    private Button mButtonAnswer3;
    private Button mButtonAnswer4;

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

    int countRightAnswers = 0;
    int countWrongAnswers = 0;

    int firstAnswer = 0;

    //random values for questions and answers
    private int random;
    private ArrayList<String> randomArray = new ArrayList<>();
    private Set<Integer> randomSet = new HashSet<>();


    public QuestionMechanism(Activity activity) {
        mActivity = activity;
    }


    protected void create(String[] capitalsArray, String[] countriesArray) {
        mQuestionTextView = (TextView) mActivity.findViewById(R.id.question_textview);

        mTrueScoreTextView = (TextView) mActivity.findViewById(R.id.true_score_textview);
        mWrongScoreTextView = (TextView) mActivity.findViewById(R.id.wrong_score_textview);

        mButtonAnswer1 = (Button) mActivity.findViewById(R.id.button1);
        mButtonAnswer2 = (Button) mActivity.findViewById(R.id.button2);
        mButtonAnswer3 = (Button) mActivity.findViewById(R.id.button3);
        mButtonAnswer4 = (Button) mActivity.findViewById(R.id.button4);

        mNextButton = (Button) mActivity.findViewById(R.id.next_button);

        mCapitalsList = Arrays.asList(capitalsArray);
        mCountriesList = Arrays.asList(countriesArray);

        mCountriesIndex = -1;
        openNextQuestion();

        buttonListener(mButtonAnswer1);
        buttonListener(mButtonAnswer2);
        buttonListener(mButtonAnswer3);
        buttonListener(mButtonAnswer4);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextQuestion();
            }
        });
    }

    public void openNextQuestion() {
        mCountriesIndex++;
        if (mCountriesIndex == mCapitalsList.size()) {
            mCountriesIndex = 0;
        }

        /*
        random question title
         */
        mQuestionCountry = mCountriesList.get(mCountriesIndex);
        mQuestionTextView.setText(mActivity.getString
                (R.string.question_begin) + "\n" + mQuestionCountry + " ?");

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
        mButtonAnswer1.setText(randomArray.get(0));
        mButtonAnswer2.setText(randomArray.get(1));
        mButtonAnswer3.setText(randomArray.get(2));
        mButtonAnswer4.setText(randomArray.get(3));

        //buttons' colors
        mButtonAnswer1.setBackgroundResource(R.color.colorButtonStandart);
        mButtonAnswer2.setBackgroundResource(R.color.colorButtonStandart);
        mButtonAnswer3.setBackgroundResource(R.color.colorButtonStandart);
        mButtonAnswer4.setBackgroundResource(R.color.colorButtonStandart);

        //enable buttons
        mButtonAnswer1.setEnabled(true);
        mButtonAnswer2.setEnabled(true);
        mButtonAnswer3.setEnabled(true);
        mButtonAnswer4.setEnabled(true);


        randomSet.clear();
        randomArray.clear();

        firstAnswer = 0;
    }

    /*
    firstAnswer = 0 - no answer yet
    firstAnswer = 1 - first answer is correct
    firstAnswer = 2 - first answer is wrong
     */
    public void buttonListener(final Button button) {
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
                    mTrueScoreTextView.setText(mActivity.getResources()
                            .getString(R.string.true_answers) + countRightAnswers);
                }
                else if (button.getTag().equals(2)) {
                    countWrongAnswers += 1;
                    mWrongScoreTextView.setText(mActivity.getResources()
                            .getString(R.string.false_answers) + countWrongAnswers);
                }
            }
        });
    }
}