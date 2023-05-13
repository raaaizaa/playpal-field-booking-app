package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

        String fieldId = getIntent().getStringExtra("fieldId");

        initialize();
        setAdapter(fieldId);
//        setAdapter2();
    }

    public void initialize(){
        back = findViewById(R.id.backButton);
        setBackButton();
        roomList = findViewById(R.id.field_roomlist);
    }

    public void setAdapter(String fieldId){
        Integer fieldIdInt = Integer.parseInt(fieldId);
        roomList.setLayoutManager(new LinearLayoutManager(this));
        room_adapter adapter = new room_adapter(roomdb.getRoomById(fieldIdInt));
        roomList.setAdapter(adapter);
    }

    public void setBackButton(){

        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }

    public void setAdapter2(){
        roomList.setLayoutManager(new LinearLayoutManager(this));
//        room_adapter adapter = new room_adapter(roomdb.getAllRooms());
//        roomList.setAdapter(adapter);
    }
}