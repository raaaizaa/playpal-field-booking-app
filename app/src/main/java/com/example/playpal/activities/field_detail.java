package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playpal.R;
import com.example.playpal.adapters.room_adapter;
import com.example.playpal.utils.room_database_helper;

public class field_detail extends AppCompatActivity {

    private room_database_helper roomdb;
    private TextView fieldNameTextView, fieldLocationTextView;
    private ImageButton backButton, accountButton;
    private RecyclerView roomListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_detail);

        initialize();
    }

    public void initialize() {
        roomdb = new room_database_helper(this);
        String fieldId = getIntent().getStringExtra("fieldId");
        String fieldName = getIntent().getStringExtra("fieldName");
        String fieldLocation = getIntent().getStringExtra("fieldLocation");
        String username = getIntent().getStringExtra("username");

        fieldNameTextView = findViewById(R.id.field_detail_name);
        fieldNameTextView.setText(fieldName);

        fieldLocationTextView = findViewById(R.id.field_detail_location);
        fieldLocationTextView.setText(fieldLocation);

        backButton = findViewById(R.id.backButton);
        accountButton = findViewById(R.id.accountButton);
        roomListRecyclerView = findViewById(R.id.field_roomlist);
        setListener(username);
        setAdapter(fieldId);
    }

    public void setAdapter(String fieldId) {
        Integer fieldIdInt = Integer.parseInt(fieldId);
        roomListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String username = getIntent().getStringExtra("username");
        room_adapter adapter = new room_adapter(roomdb.getRoomById(fieldIdInt), this, username);
        roomListRecyclerView.setAdapter(adapter);
        roomdb.close();
    }

    public void setListener(String username) {
        backButton.setOnClickListener(e -> {
            backToHomepage(username);
        });
    }

    public void backToHomepage(String username){
        Intent intent = new Intent(this, home.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

}