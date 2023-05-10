package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class home extends AppCompatActivity {

    ImageButton fieldButton, roomButton;
    TextView greetingText;
    room room;
    field field;

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

    // Ngetes doang inimah pokonya konsepnya kira-kira gini, gatau ngodingnya tar ajalah
    public void setFieldAndRoom(){
//        field_manager field_manager = new field_manager();
//        field field_class1 = new field("tes", "bandung", 1);
//
//        field_manager.addField(field_class1);
//
//        room room_class1 = new room("tes", "badminton", "bandung", "2");
//
//        field_manager.addRoomToField(room_class1, "tes");
    }
}