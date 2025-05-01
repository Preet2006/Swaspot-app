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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox rememberMeCheckbox;
    private ImageButton passwordVisibilityToggle;
    private boolean isPasswordVisible = false;
    private FirebaseAuth mAuth;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize UI elements
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        passwordVisibilityToggle = findViewById(R.id.passwordVisibilityToggle);
        
        signUpButton = findViewById(R.id.signUpButton);
        Button googleSignInButton = findViewById(R.id.googleSignInButton);
        Button facebookSignInButton = findViewById(R.id.facebookSignInButton);
        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        TextView registerLink = findViewById(R.id.registerLink);
        ImageButton backButton = findViewById(R.id.backButton);

        // Set up back button to go back to the previous screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Password visibility toggle
        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        // Sign In Button
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
            }
        });

        // Forgot Password Link
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                if (email.isEmpty()) {
                    emailInput.setError("Please enter your email");
                    return;
                }
                
                // Show loading state
                forgotPasswordLink.setEnabled(false);
                
                mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            forgotPasswordLink.setEnabled(true);
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, 
                                    "Password reset email sent. Please check your inbox.",
                                    Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                    "Failed to send reset email: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });

        // Register Link
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
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

        // Show loading state
        signUpButton.setEnabled(false);
        signUpButton.setText("Signing in...");

        // Sign in with Firebase
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // Reset button state
                    signUpButton.setEnabled(true);
                    signUpButton.setText("Sign In");

                    if (task.isSuccessful()) {
                        // Sign in success
                        FirebaseUser user = mAuth.getCurrentUser();
                        
                        // Save remember me preference if needed
                        if (rememberMe) {
                            // Save login credentials using SharedPreferences
                            // This is just a placeholder - in a real app you'd use secure storage
                        }

                        Toast.makeText(LoginActivity.this, "Login successful!", 
                            Toast.LENGTH_SHORT).show();
                        
                        // Navigate to MainActivity
                        goToMainActivity();
                    } else {
                        // If sign in fails, display a message to the user.
                        String errorMessage = task.getException() != null ? 
                            task.getException().getMessage() : 
                            "Authentication failed.";
                        
                        if (errorMessage.contains("no user record")) {
                            errorMessage = "No account found with this email";
                        } else if (errorMessage.contains("password is invalid")) {
                            errorMessage = "Incorrect password";
                        }
                        
                        Toast.makeText(LoginActivity.this, errorMessage,
                            Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
} 