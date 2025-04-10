package com.example.miniprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.Entités.User.User;
import com.example.miniprojet.Entités.User.UserRepository;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, createAccountButton;
    private TextInputLayout emailInputLayout, passwordInputLayout;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userRepository = new UserRepository(this);

        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);

        loginButton.setOnClickListener(v -> loginUser());

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailInputLayout.getEditText().getText().toString().trim();
        String password = passwordInputLayout.getEditText().getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        userRepository.getUserByEmail(email, new UserRepository.DataCallback<User>() {
            @Override
            public void onDataLoaded(User user) {
                runOnUiThread(() -> {
                    if (user != null && user.getPassword().equals(password)) {
                        setUserLoggedIn();

                        Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, SelectGameActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        preferences.edit().putBoolean("is_logged_in", true).apply();
    }
}

