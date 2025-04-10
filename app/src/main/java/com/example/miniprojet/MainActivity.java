package com.example.miniprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vérification de l'état de connexion
        checkLoginStatus();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Re-vérifie le login quand l'activité revient au premier plan
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("is_logged_in", false);

        Intent intent;
        if(isLoggedIn) {
            intent = new Intent(this, SelectGameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
