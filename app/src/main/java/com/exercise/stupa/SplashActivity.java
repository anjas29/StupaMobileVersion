package com.exercise.stupa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                    SharedPreferences sharedPreferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);

                    if(sharedPreferences.getBoolean("login", false)){
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
