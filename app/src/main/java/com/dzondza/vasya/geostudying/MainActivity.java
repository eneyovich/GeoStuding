package com.dzondza.vasya.geostudying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * main screen in application
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button euroButton = (Button) findViewById(R.id.button_europe);
        Button asiaButton = (Button) findViewById(R.id.button_asia);
        Button namericaButton = (Button) findViewById(R.id.button_namerica);
        Button samericaButton = (Button) findViewById(R.id.button_samerica);
        Button australiaButton = (Button) findViewById(R.id.button_australia);
        Button africaButton = (Button) findViewById(R.id.button_africa);


        createIntent(euroButton, EuropeanActivity.class);
        createIntent(asiaButton, AsiaActivity.class);
        createIntent(namericaButton, NAmericaActivity.class);
        createIntent(samericaButton, SAmericaActivity.class);
        createIntent(australiaButton, AustraliaActivity.class);
        createIntent(africaButton, AfricaActivity.class);
    }


    private void createIntent(final Button button, final Class clazz) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, clazz);
                startActivity(intent);
            }
        });
    }
}