package com.dzondza.vasya.geostudying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * main screen in application
 */

public class MainActivity extends AppCompatActivity {
    List<Button> mButtons = new ArrayList<>();
    List<Class> mClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continentalsButtons();
    }


    private void createIntent(final Button button, final Class clazz) {
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, clazz);
            startActivity(intent);
        });
    }


    private void continentalsButtons() {
        mButtons.addAll(Arrays.asList((Button) findViewById(R.id.button_europe),
                (Button) findViewById(R.id.button_asia), (Button) findViewById(R.id.button_namerica),
                (Button) findViewById(R.id.button_samerica), (Button) findViewById(R.id.button_australia),
                (Button) findViewById(R.id.button_africa)));

        mClasses.addAll(Arrays.asList(EuropeanActivity.class, AsiaActivity.class, NAmericaActivity.class,
                SAmericaActivity.class, AustraliaActivity.class, AfricaActivity.class));

        for (int i = 0; i < mButtons.size(); i++) {
            createIntent(mButtons.get(i), mClasses.get(i));
        }
    }
}