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
        String username = getIntent().getStringExtra("username");
        fieldButton = findViewById(R.id.field_button);
        roomButton = findViewById(R.id.room_button);
        addButton = findViewById(R.id.add_button);

        greetingTextView = findViewById(R.id.greeting);
        greetingTextView.setText("Hi, " + username);

        replaceFragment(new field_fragment());
        setListener(username);
    }

    public void setListener(String username){
        fieldButton.setOnClickListener(e -> {
            field_fragment field_fragment = new field_fragment();
            Bundle args = new Bundle();
            args.putString("username", username);
            field_fragment.setArguments(args);
            replaceFragment(field_fragment);
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        });

        roomButton.setOnClickListener(e -> {
            room_fragment room_fragment = new room_fragment();

            Bundle args = new Bundle();
            args.putString("username", username);
            room_fragment.setArguments(args);
            replaceFragment(room_fragment);
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        });

        addButton.bringToFront();
        addButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, room_add_new.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}