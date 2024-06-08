package com.example.bca_intenship;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseLongArray;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.getString(ConstantSp.ID,"").equals("")){
                    new CommonMethod(SplashActivity.this,MainActivity.class);
                }
                else {
                    new CommonMethod(SplashActivity.this,ActivityToFragment.class);
                }
                finish();

            }
        },5000);


    }
}