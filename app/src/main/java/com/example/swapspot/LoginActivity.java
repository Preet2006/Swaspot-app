package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox rememberMeCheckbox;
    private ImageButton passwordVisibilityToggle;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize UI elements
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        passwordVisibilityToggle = findViewById(R.id.passwordVisibilityToggle);
        
        Button signUpButton = findViewById(R.id.signUpButton);
        Button googleSignInButton = findViewById(R.id.googleSignInButton);
        Button facebookSignInButton = findViewById(R.id.facebookSignInButton);
        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        TextView registerLink = findViewById(R.id.registerLink);
        ImageButton backButton = findViewById(R.id.backButton);

        // Set up back button to go back to the previous screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will navigate to the previous screen (OnboardingSecondActivity)
            }
        });

        // Password visibility toggle
        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        // Sign Up Button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // Google Sign In
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the Google Account selection activity
                Intent intent = new Intent(LoginActivity.this, GoogleAccountActivity.class);
                startActivity(intent);
            }
        });

        // Facebook Sign In
        facebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement Facebook Sign In Logic
                Toast.makeText(LoginActivity.this, "Facebook Sign In clicked", Toast.LENGTH_SHORT).show();
                // Navigate to MainActivity on successful sign in
                goToMainActivity();
            }
        });

        // Forgot Password Link
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement Forgot Password Logic
                Toast.makeText(LoginActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Register Link
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement Register Logic
                Toast.makeText(LoginActivity.this, "Register clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility);
        } else {
            // Show password
            passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility);
        }
        isPasswordVisible = !isPasswordVisible;
        
        // Move cursor to the end of the text
        passwordInput.setSelection(passwordInput.getText().length());
    }

    private void attemptLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        boolean rememberMe = rememberMeCheckbox.isChecked();

        // Basic validation
        if (email.isEmpty()) {
            emailInput.setError("Email cannot be empty");
            return;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Password cannot be empty");
            return;
        }

        // For demo purposes, allowing any login
        // In a real app, you would validate credentials against a server
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
        
        // Save remember me preference if needed
        if (rememberMe) {
            // Save login credentials using SharedPreferences
            // This is just a placeholder - in a real app you'd use secure storage
        }

        // Navigate to MainActivity
        goToMainActivity();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }
} 