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

    private user_database_helper db;
    private ImageView google, facebook, apple;
    private TextView registerTextView, forgotPassTextView;
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initialize();
    }

    public void initialize(){
        db = new user_database_helper(this);
        usernameEditText = findViewById(R.id.usernameLoginField);
        passwordEditText = findViewById(R.id.passwordLoginField);
        loginButton = findViewById(R.id.loginButton);
        forgotPassTextView = findViewById(R.id.forgotPass);
        registerTextView = findViewById(R.id.registerHereText);
        google = findViewById(R.id.loginUsingGoogle);
        facebook = findViewById(R.id.loginUsingFacebook);
        apple = findViewById(R.id.loginusingAppleID);

        setListener();
    }

    public void setListener(){
        loginButton.setOnClickListener(e -> {
            String inputtedUsername = usernameEditText.getText().toString();
            String inputtedPassword = passwordEditText.getText().toString();

            boolean accountIsValid = db.checkUser(inputtedUsername, inputtedPassword);

            if(inputIsEmpty(inputtedUsername, inputtedPassword)){
                showToast("All fields must be filled!");
            }else{
                if(accountIsValid){
                    showToast("Login Success!");
                    openHomepage(inputtedUsername);
                }else{
                    showToast("Login Failed!");
                }
            }
            db.close();
        });

        forgotPassTextView.setOnClickListener(e -> {
            showToast("Sorry, this service currently not available!");
        });

        registerTextView.setOnClickListener(e -> {
            openRegisterPage();
        });

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
        finish();
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