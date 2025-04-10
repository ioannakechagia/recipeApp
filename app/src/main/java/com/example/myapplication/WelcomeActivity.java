package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    String receivedValue = "Username";
    EditText username, password, fullName, phone;
    Button loginButton, signUpButton;
    CheckBox acceptTerms;

    String currentLayout = "welcome";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentLayout = savedInstanceState.getString("CURRENT_LAYOUT", "welcome");
        }

        loadCurrentLayout();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent receiverIntent = getIntent();
        String name = receiverIntent.getStringExtra("NAME");

        if (name != null) {
            receivedValue = name;
        }

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CURRENT_LAYOUT", currentLayout);
    }

    public void loginLayout(View view){
        currentLayout = "login";
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        loginButton = findViewById(R.id.LoginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("Ioanna") && password.getText().toString().equals("1234")) {
                    Toast.makeText(WelcomeActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WelcomeActivity.this, MenuActivity.class);
                    intent.putExtra("NAME", username.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(WelcomeActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void signUpLayout(View view){
        currentLayout = "signup";
        setContentView(R.layout.signup_layout);

        fullName = findViewById(R.id.FullName);
        phone = findViewById(R.id.Phone);
        acceptTerms = findViewById(R.id.AcceptTerms);
        signUpButton = findViewById(R.id.SignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fullName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "Please enter your name!", Toast.LENGTH_SHORT).show();
                }
                else if (phone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "Please enter your phone!", Toast.LENGTH_SHORT).show();
                }
                else if (!acceptTerms.isChecked()) {
                    Toast.makeText(WelcomeActivity.this, "Please accept the terms and conditions!", Toast.LENGTH_SHORT).show();
                }
                else {
                    setContentView(R.layout.activity_welcome);
                }
            }
        });


    }

    public void welcomeLayout(View view) {
        currentLayout = "welcome";
        setContentView(R.layout.activity_welcome);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadCurrentLayout() {
        switch (currentLayout) {
            case "login":
                loginLayout(null);
                break;
            case "signup":
                signUpLayout(null);
                break;
            default:
                welcomeLayout(null);
                break;
        }
    }

}