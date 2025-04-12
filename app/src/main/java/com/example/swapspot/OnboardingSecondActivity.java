package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_second);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set up the Get Started button to navigate to LoginActivity
        Button getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent intent = new Intent(OnboardingSecondActivity.this, LoginActivity.class);
                startActivity(intent);
                
                // Close all previous activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });
    }
} 