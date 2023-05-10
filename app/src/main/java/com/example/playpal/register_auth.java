package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class register_auth extends AppCompatActivity {

    private ImageButton back;
    private Button register;
    private EditText email, username, password, confirm;
    private TextView loginHere;

    private database_helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        initialize();
    }

    public void initialize(){
        email = findViewById(R.id.emailRegisterField);
        username = findViewById(R.id.usernameRegisterField);
        password = findViewById(R.id.passwordRegisterField);
        confirm = findViewById(R.id.confirmRegisterField);
        db = new database_helper(this);

        register = findViewById(R.id.registerButton);
        setRegister();

        back = findViewById(R.id.backButton);
        setBackButton();

        loginHere = findViewById(R.id.loginHereText);
        setLoginHere();
    }

    public void setBackButton(){
        back.setOnClickListener(e -> {
            openLoginPage();
        });
    }

    public void setLoginHere(){
        loginHere.setOnClickListener(e -> {
            openLoginPage();
        });
    }

    public void setRegister(){
        register.setOnClickListener(e -> {
            String inputtedEmail = email.getText().toString();
            String inputtedUsername = username.getText().toString();
            String inputtedPass = password.getText().toString();
            String inputtedConfirm = confirm.getText().toString();

            Boolean isUsernameNotAvailable = db.checkUsername(inputtedUsername);
            Boolean isEmailNotAvailable = db.checkEmail(inputtedEmail);
            Boolean insert = db.insertUser(inputtedUsername, inputtedEmail, inputtedPass);

            if (inputIsEmpty(inputtedEmail, inputtedUsername, inputtedPass, inputtedConfirm)) {
                showToast("All fields must be filled");
            }else if(!isEmailValid(inputtedEmail)) {
                showToast("Invalid email address!");
            }else if(!isPasswordValid(inputtedPass)){
                showToast("Password must be alphanumeric!");
            }else if(!inputtedPass.equals(inputtedConfirm)){
                showToast("Password doesn't match!");
            }else{
                if(isEmailNotAvailable == true){
                    showToast("This email previously has been used!");
                }else{
                    if(isUsernameNotAvailable == true){
                        showToast("User already exist!");
                    }else{
                        if(insert == true){
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