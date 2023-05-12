package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

public class field_detail extends AppCompatActivity {

    ImageButton back, account;
    RecyclerView roomList;
    room_database_helper roomdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_detail);
        roomdb = new room_database_helper(this);

        initialize();

        String fieldId = getIntent().getStringExtra("fieldId");
        setAdapter(fieldId);
    }

    public void initialize(){
        back = findViewById(R.id.backButton);
        setBackButton();

        roomList = findViewById(R.id.field_roomlist);
    }

    public void setBackButton(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    public void setAdapter(String fieldId){
        roomList.setLayoutManager(new LinearLayoutManager(this));
        Log.i("beres setlayout manager", "beres");

        room_adapter adapter = new room_adapter(roomdb.getRoomById(fieldId));
        Log.i("beres getroombyid", "beres");
        roomList.setAdapter(adapter);
    }
}