package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playpal.R;
import com.example.playpal.fragment.field_fragment;
import com.example.playpal.fragment.room_fragment;

public class home extends AppCompatActivity {

    ImageButton fieldButton, roomButton;
    TextView greetingText;
    com.example.playpal.models.room room;
    com.example.playpal.models.field field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
    }

    @SuppressLint("SetTextI18n")
    public void initialize(){
        fieldButton = findViewById(R.id.field_button);
        roomButton = findViewById(R.id.room_button);

//        String username = getIntent().getStringExtra("username");
        String username = "pongpet";
        Log.i("tes", username);
        greetingText = findViewById(R.id.greeting);
        greetingText.setText("Hi, " + username);

        replaceFragment(new field_fragment());
        setClickListener(username);
    }

    public void setClickListener(String username){
        fieldButton.setOnClickListener(e -> {
            field_fragment field_fragment = new field_fragment();
            Bundle args = new Bundle();
            args.putString("username", username);
            field_fragment.setArguments(args);
            replaceFragment(field_fragment);
        });

        roomButton.setOnClickListener(e -> {
            room_fragment room_fragment = new room_fragment();

            Bundle args = new Bundle();
            args.putString("username", username);
            room_fragment.setArguments(args);
            replaceFragment(room_fragment);
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}