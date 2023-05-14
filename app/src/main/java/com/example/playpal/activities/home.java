package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
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

    public void initialize(){
        fieldButton = findViewById(R.id.field_button);
        roomButton = findViewById(R.id.room_button);

        String username = getIntent().getStringExtra("username");
        greetingText = findViewById(R.id.greeting);
        greetingText.setText("Hi, " + username);

        replaceFragment(new field_fragment());
        setFragmentButton();
    }

    public void setFragmentButton(){
        fieldButton.setOnClickListener(e -> {
            replaceFragment(new field_fragment());
        });

        roomButton.setOnClickListener(e -> {
            replaceFragment(new room_fragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}