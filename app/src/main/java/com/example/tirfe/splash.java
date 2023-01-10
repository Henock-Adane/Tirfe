package com.example.tirfe;

import static com.example.tirfe.MainActivity.splashShown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * splash activity class
 */
public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //if splash is shown already proceed to the main activity
        if(splashShown){
            Intent intent = new Intent(splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //hiding the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //using handler to proceed to the main activity with 2 and half sec delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);

    }
}