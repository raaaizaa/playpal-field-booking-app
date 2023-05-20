package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playpal.R;
import com.example.playpal.fragment.field_fragment;
import com.example.playpal.fragment.room_fragment;
import com.example.playpal.utils.dummy_data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class home extends AppCompatActivity {

    private ImageButton fieldButton, roomButton, accountButton;
    private TextView greetingTextView;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();
    }

    @SuppressLint("SetTextI18n")
    public void initialize(){
        dummy_data dummy_data = new dummy_data(this);
        dummy_data.insertData();
        String username = getIntent().getStringExtra("username");
        fieldButton = findViewById(R.id.field_button);
        roomButton = findViewById(R.id.room_button);
        addButton = findViewById(R.id.add_button);

        greetingTextView = findViewById(R.id.greeting);
        greetingTextView.setText("Hi, " + username);

        replaceFragment(new field_fragment(), username);
        setListener(username);
    }

    public void setListener(String username){
        fieldButton.setOnClickListener(e -> {
            field_fragment field_fragment = new field_fragment();
            replaceFragment(field_fragment, username);
        });

        roomButton.setOnClickListener(e -> {
            room_fragment room_fragment = new room_fragment();
            replaceFragment(room_fragment, username);
        });

        addButton.bringToFront();
        addButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, room_add_new.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }

    private void replaceFragment(Fragment fragment, String username) {
        Bundle args = new Bundle();
        args.putString("username", username);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}