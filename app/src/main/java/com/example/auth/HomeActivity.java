package com.example.auth;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Assuming you passed the username from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText(String.format("Welcome, %s!", username));
    }
}
