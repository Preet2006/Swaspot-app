package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Delayed navigation to OnboardingActivity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Start the onboarding activity
            Intent mainIntent = new Intent(SplashActivity.this, OnboardingActivity.class);
            startActivity(mainIntent);
            
            // Close this activity
            finish();
        }, SPLASH_DISPLAY_TIME);
    }
} 