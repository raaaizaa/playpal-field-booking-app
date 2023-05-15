package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.utils.user_database_helper;

public class register_auth extends AppCompatActivity {

    private user_database_helper db;
    private Button registerButton;
    private EditText emailEditText, usernameEditText, passwordEditText, confirmEditText;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        initialize();
    }

    public void initialize(){
        db = new user_database_helper(this);
        emailEditText = findViewById(R.id.emailRegisterField);
        usernameEditText = findViewById(R.id.usernameRegisterField);
        passwordEditText = findViewById(R.id.passwordRegisterField);
        confirmEditText = findViewById(R.id.confirmRegisterField);
        registerButton = findViewById(R.id.registerButton);
        loginTextView = findViewById(R.id.loginHereText);
        setListener();
    }

    public void setListener(){
        loginTextView.setOnClickListener(e -> {
            openLoginPage();
        });

        registerButton.setOnClickListener(e -> {
            String inputtedEmail = emailEditText.getText().toString();
            String inputtedUsername = usernameEditText.getText().toString();
            String inputtedPass = passwordEditText.getText().toString();
            String inputtedConfirm = confirmEditText.getText().toString();

            boolean usernameExist = db.checkUsername(inputtedUsername);
            boolean emailExist = db.checkEmail(inputtedEmail);
            boolean insertToDB = db.insertUser(inputtedUsername, inputtedEmail, inputtedPass);

            if (inputIsEmpty(inputtedEmail, inputtedUsername, inputtedPass, inputtedConfirm)) {
                showToast("All fields must be filled");
            }else if(!isEmailValid(inputtedEmail)) {
                showToast("Invalid email address!");
            }else if(!isPasswordValid(inputtedPass)){
                showToast("Password must be alphanumeric!");
            }else if(!inputtedPass.equals(inputtedConfirm)){
                showToast("Password doesn't match!");
            }else{
                if(emailExist){
                    showToast("This email previously has been used!");
                }else{
                    if(usernameExist){
                        showToast("User already exist!");
                    }else{
                        if(insertToDB){
                            showToast("Registration Success!");
                            openLoginPage();
                            finish();
                        }else{
                            showToast("Registration Failed!");
                        }
                    }
                }
            }
        });
    }

    private void openLoginPage(){
        Intent intent = new Intent(this, login_auth.class);
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

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password){
        return password.matches("[a-zA-Z0-9]+");
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}