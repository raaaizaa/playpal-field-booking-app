package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.utils.user_database_helper;

public class login_auth extends AppCompatActivity {

    private ImageView google, facebook, apple;
    private TextView registerHere, forgotPass;
    private EditText username, password;
    private Button login;

    private user_database_helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initialize();
    }

    public void initialize(){
        username = findViewById(R.id.usernameLoginField);
        password = findViewById(R.id.passwordLoginField);
        db = new user_database_helper(this);

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
            String inputtedUsername = username.getText().toString();
            String inputtedPassword = password.getText().toString();
            Boolean isAccountValid = db.checkUser(inputtedUsername, inputtedPassword);

            if(inputIsEmpty(inputtedUsername, inputtedPassword)){
                showToast("All fields must be filled!");
            }else{
                if(isAccountValid == true){
                    showToast("Login Success!");
                    openHomepage(inputtedUsername);
                }else{
                    showToast("Login Failed!");
                }
            }
        });
    }

    public void setForgotPass(){
        forgotPass.setOnClickListener(e -> {
            showToast("Sorry, this service currently not available!");
        });
    }

    public void setRegisterHere(){
        registerHere.setOnClickListener(e -> {
            openRegisterPage();
        });
    }

    public void setLoginUsing(){
        google.setOnClickListener(e -> {
            showToast("Sorry, this service currently not available!");
        });

        facebook.setOnClickListener(e -> {
            showToast("Sorry, this service currently not available!");
        });

        apple.setOnClickListener(e -> {
            showToast("Sorry, this service currently not available!");
        });
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, register_auth.class);
        startActivity(intent);
    }

    public void openHomepage(String inputtedUsername){
        Intent intent = new Intent(this, home.class);
        intent.putExtra("username", inputtedUsername);
        startActivity(intent);
        finish();
    }

    private boolean inputIsEmpty(String... inputs){
        for(String input : inputs){
            if(input.isEmpty()){
                return true;
            }
        }
        return false;
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}