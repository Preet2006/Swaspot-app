package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set up the Next button to navigate to the second onboarding screen
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the second onboarding screen
                Intent intent = new Intent(OnboardingActivity.this, OnboardingSecondActivity.class);
                startActivity(intent);
                finish(); // Close this activity so the user can't navigate back using back button
            }
        });
    }
} 