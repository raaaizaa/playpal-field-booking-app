package com.example.playpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class room_detail extends AppCompatActivity {

    ImageButton back, account;
    TextView roomDetailName, roomDetailFieldName;
    RecyclerView playerList;
    player_database_helper playerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        playerdb = new player_database_helper(this);

        String roomName = getIntent().getStringExtra("roomName");
        String roomLocation = getIntent().getStringExtra("roomLocation");
        String roomId = getIntent().getStringExtra("roomId");

        roomDetailName = findViewById(R.id.room_detail_room_name);
        roomDetailName.setText(roomName);
        roomDetailFieldName = findViewById(R.id.room_detail_field_name);
        roomDetailFieldName.setText(roomLocation);

        initialize();
        setAdapter(roomId);
    }

    public void initialize(){
        back = findViewById(R.id.backButton);
        setBackButton();

        account = findViewById(R.id.accountButton);
        playerList = findViewById(R.id.playerList);
    }

    public void setBackButton(){

        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }

    public void setAdapter(String roomId){
        Integer roomIdInt = Integer.parseInt(roomId);
        playerList.setLayoutManager(new LinearLayoutManager(this));
        player_adapter adapter = new player_adapter(playerdb.getPlayerById(roomIdInt));
        playerList.setAdapter(adapter);
    }
}