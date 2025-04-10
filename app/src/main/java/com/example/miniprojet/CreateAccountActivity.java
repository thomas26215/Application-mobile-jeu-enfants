package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.Entités.User.User;
import com.example.miniprojet.Entités.User.UserRepository;
import com.google.android.material.textfield.TextInputLayout;

public class CreateAccountActivity extends AppCompatActivity {

    TextView loginLink;
    Button createAccountButton;
    TextInputLayout usernameInputLayout, emailInputLayout, passwordInputLayout, confirmPasswordInputLayout;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        // Initialisation du dépôt utilisateur
        userRepository = new UserRepository(this);

        // Récupération des vues
        loginLink = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        confirmPasswordInputLayout = findViewById(R.id.confirmPasswordInputLayout);

        // Action de redirection vers la page de connexion
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
            // Utilisation de finish pour garantir que cette activité n’est pas gardée dans la pile
            finish();
        });

        // Action pour créer un compte utilisateur
        createAccountButton.setOnClickListener(v -> createNewAccount());
    }

    private void createNewAccount() {
        // Récupération des données saisies par l'utilisateur
        String username = usernameInputLayout.getEditText().getText().toString().trim();
        String email = emailInputLayout.getEditText().getText().toString().trim();
        String password = passwordInputLayout.getEditText().getText().toString();
        String confirmPassword = confirmPasswordInputLayout.getEditText().getText().toString();

        // Validation des champs
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Création du nouvel utilisateur
        final User newUser = new User(username, email, password);
        userRepository.insert(newUser); // Insertion dans la base de données

        // Message de confirmation
        Toast.makeText(this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();

        // Transition vers l'activité de connexion avec suppression de l'activité actuelle de la pile
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Garantie que l'utilisateur ne pourra pas revenir à cette activité après la création du compte
    }
}

