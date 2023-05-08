package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    ImageButton fieldButton, roomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
    }

    public void initialize(){
        fieldButton = findViewById(R.id.field_button);
        roomButton = findViewById(R.id.room_button);

        setFragmentButton();
    }

    public void setFragmentButton(){
        fieldButton.setOnClickListener(e -> {
            replaceFragment(new fieldFragment());
        });

        roomButton.setOnClickListener(e -> {
            replaceFragment(new roomFragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}