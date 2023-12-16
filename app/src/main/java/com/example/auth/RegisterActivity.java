package com.example.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auth.database.UserRepository;

public class RegisterActivity extends Activity {

    private EditText editTextUsernameRegister, editTextPasswordRegister;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRepository = new UserRepository(this);

        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);

        Button buttonRegisterSubmit = findViewById(R.id.buttonRegisterSubmit);
        buttonRegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameRegister.getText().toString();
                String password = editTextPasswordRegister.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    showToast("Please enter both username and password");
                } else {
                    boolean isInserted = userRepository.addUser(username, password);

                    if (isInserted) {
                        String registeredUsername = userRepository.getUsername(username);
                        showToast("Registration successful");
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        intent.putExtra("USERNAME", registeredUsername);
                        startActivity(intent);
                        finish(); // Close registration activity
                    } else {
                        showToast("Registration failed. User already exists.");
                    }
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
