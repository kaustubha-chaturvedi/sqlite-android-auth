package com.example.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auth.database.UserRepository;

public class LoginActivity extends Activity {

    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository(this);

        editTextUsernameLogin = findViewById(R.id.editTextUsernameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);

        Button buttonLoginSubmit = findViewById(R.id.buttonLoginSubmit);
        buttonLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();

                boolean isAuthenticated = userRepository.checkUser(username, password);

                if (isAuthenticated) {
                    String loggedInUsername = userRepository.getUsername(username);
                    showToast("Login successful");

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("USERNAME", loggedInUsername);
                    startActivity(intent);
                    finish();  // Close login activity
                } else {
                    showToast("Login failed. Incorrect username or password.");
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
