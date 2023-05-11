package com.tamim.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameTextField;
    EditText passwordTextField;
    Button loginButton;
    Button signupButton;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTextField = findViewById(R.id.usernameTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        db = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("LoginButton Pressed");
                handleLogin();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Signup Pressed");
                Intent i= new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void handleLogin() {
        String username = usernameTextField.getText().toString();
        if (username.matches("")) {
            showToast("You did not enter username");
            return;
        }
        String password = passwordTextField.getText().toString();
        if (password.matches("")) {
            showToast("You did not enter password");
            return;
        }
        User user = db.getUser(username);
        if (user == null) {
            showToast("User does not exists");
            return;
        }
        if (user.getPassword().equals(password)) {
            showToast("Login successful");
            Intent i= new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
        } else {
            showToast("Password does not match!");
        }

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}