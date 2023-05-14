package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.adapters.room_adapter;
import com.example.playpal.utils.room_database_helper;

public class field_detail extends AppCompatActivity {

    TextView fieldDetailName;
    ImageButton back, account;
    RecyclerView roomList;
    room_database_helper roomdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_detail);
        Log.i("teeeesssssssssssssss", "=========================================");
        roomdb = new room_database_helper(this);

            String fieldId = getIntent().getStringExtra("fieldId");
            String fieldName = getIntent().getStringExtra("fieldName");


            fieldDetailName = findViewById(R.id.field_detail_name);
            fieldDetailName.setText(fieldName);


        initialize();
        setAdapter(fieldId);
    }

    public void initialize() {
        back = findViewById(R.id.backButton);
        setBackButton();
        roomList = findViewById(R.id.field_roomlist);
    }

    public void setAdapter(String fieldId) {
        Integer fieldIdInt = Integer.parseInt(fieldId);
        roomList.setLayoutManager(new LinearLayoutManager(this));
        String username = getIntent().getStringExtra("username");
        room_adapter adapter = new room_adapter(roomdb.getRoomById(fieldIdInt), this, username);
        if (username != null) {
            Log.i("tes", username);
        } else {
            Log.i("tes", "Username not found");
        }
        roomList.setAdapter(adapter);
    }

    public void setBackButton() {

        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

    }
}