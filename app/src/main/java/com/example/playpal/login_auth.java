package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login_auth extends AppCompatActivity {

    private ImageView google, facebook, apple;
    private TextView registerHere, forgotPass;
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initialize();
    }

    public void initialize(){
        email = findViewById(R.id.emailLoginField);
        password = findViewById(R.id.passwordLoginField);

        login = findViewById(R.id.loginButton);
        setLogin();

        forgotPass = findViewById(R.id.forgotPass);
        setForgotPass();

        registerHere = findViewById(R.id.registerHereText);
        setRegisterHere();

        google = findViewById(R.id.loginUsingGoogle);
        facebook = findViewById(R.id.loginUsingFacebook);
        apple = findViewById(R.id.loginusingAppleID);
        setLoginUsing();
    }

    public void setLogin(){
        login.setOnClickListener(e -> {
            String inputtedEmail = email.getText().toString();
            String inputtedPassword = password.getText().toString();

            // Yang pasti disini validasi, masuk ke database, dll
            openHomepage();
        });
    }

    public void setForgotPass(){
        forgotPass.setOnClickListener(e -> {
            Toast.makeText(this, "Sorry, this service currently not available!", Toast.LENGTH_SHORT).show();
        });
    }

    public void setRegisterHere(){
        registerHere.setOnClickListener(e -> {
            openRegisterPage();
        });
    }

    public void setLoginUsing(){
        google.setOnClickListener(e -> {
            Toast.makeText(this, "Sorry, this service currently not available!", Toast.LENGTH_SHORT).show();
        });

        facebook.setOnClickListener(e -> {
            Toast.makeText(this, "Sorry, this service currently not available!", Toast.LENGTH_SHORT).show();
        });

        apple.setOnClickListener(e -> {
            Toast.makeText(this, "Sorry, this service currently not available!", Toast.LENGTH_SHORT).show();
        });
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, register_auth.class);
        startActivity(intent);
    }

    public void openHomepage(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
}