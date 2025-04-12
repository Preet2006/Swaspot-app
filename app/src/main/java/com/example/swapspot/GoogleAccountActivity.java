package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GoogleAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_account);

        // Set up back button to return to LoginActivity
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will return to the LoginActivity
            }
        });

        // Set up click listeners for all accounts
        setupAccountClickListener(R.id.account1, "Sarang Dev", "devsarang2004@gmail.com");
        setupAccountClickListener(R.id.account2, "Sarang Dev", "sarang.dev.23ce@btmu.edu.in");
        setupAccountClickListener(R.id.account3, "Sarang Maurya", "mauryasarang32@gmail.com");
        setupAccountClickListener(R.id.account4, "Sarang Maurya", "mauryasarang27@gmail.com");
        
        // Set up click listener for "Use another account"
        ConstraintLayout useAnotherAccount = findViewById(R.id.useAnotherAccount);
        useAnotherAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoogleAccountActivity.this, 
                        "Add another account functionality would go here", 
                        Toast.LENGTH_SHORT).show();
                // In a real app, this would launch a dialog or activity to add a new Google account
            }
        });
    }
    
    private void setupAccountClickListener(int accountId, final String name, final String email) {
        ConstraintLayout accountLayout = findViewById(accountId);
        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // In a real app, this would authenticate with the selected Google account
                Toast.makeText(GoogleAccountActivity.this, 
                        "Selected account: " + email, 
                        Toast.LENGTH_SHORT).show();
                
                // Navigate to MainActivity on successful login
                Intent intent = new Intent(GoogleAccountActivity.this, MainActivity.class);
                intent.putExtra("USER_NAME", name);
                intent.putExtra("USER_EMAIL", email);
                intent.putExtra("LOGIN_METHOD", "GOOGLE");
                
                // Clear back stack so user can't go back to login screens
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
} 