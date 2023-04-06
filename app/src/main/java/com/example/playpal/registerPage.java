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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerPage extends AppCompatActivity {

    private ImageButton back;
    private Button register;
    private EditText email, username, password, confirm;
    private TextView loginHere;

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

            if(inputtedEmail.isEmpty() || inputtedUsername.isEmpty() || inputtedPass.isEmpty() || inputtedConfirm.isEmpty()){
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(inputtedEmail).matches()){
                Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            }else if(!validator((inputtedPass))){
                Toast.makeText(this, "Password must be alphanumeric!", Toast.LENGTH_SHORT).show();
            }else if(!inputtedPass.equals(inputtedConfirm)){
                Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
            }else{
                // Disini pokonya nambahin data ke database, gatau caranya ntar ajadeh
                Toast.makeText(this, "Register Success!", Toast.LENGTH_SHORT).show();
                openLoginPage();
            }
        });
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, loginPage.class);
        startActivity(intent);
    }

    public static boolean validator(String string){
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        return m.matches();
    }


}