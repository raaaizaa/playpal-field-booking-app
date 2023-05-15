package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.adapters.player_adapter;
import com.example.playpal.models.room;
import com.example.playpal.utils.player_database_helper;

import java.util.List;

public class room_detail extends AppCompatActivity {

    ImageButton back, account;
    TextView roomDetailName, roomDetailFieldName, roomDetailCount;
    RecyclerView playerList;
    player_database_helper playerdb;
    Button roomDetailJoinButton;
    private List<room> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        playerdb = new player_database_helper(this);

        String roomName = getIntent().getStringExtra("roomName");
        String roomLocation = getIntent().getStringExtra("roomLocation");
        String roomId = getIntent().getStringExtra("roomId");
        String roomPlayerCount = getIntent().getStringExtra("roomPlayerCount");

        roomDetailName = findViewById(R.id.room_detail_room_name);
        roomDetailName.setText(roomName);
        roomDetailFieldName = findViewById(R.id.room_detail_field_name);
        roomDetailFieldName.setText(roomLocation);
        roomDetailCount = findViewById(R.id.room_detail_player_count);
        roomDetailCount.setText(roomPlayerCount);

        initialize();
        setAdapter(roomId);
    }

    public void initialize(){
        back = findViewById(R.id.backButton);

        account = findViewById(R.id.accountButton);
        playerList = findViewById(R.id.playerList);

        roomDetailJoinButton = findViewById(R.id.room_detail_join_button);
        setListener();
    }

    public void setListener(){

        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

        roomDetailJoinButton.setOnClickListener(e -> {
            String roomName = getIntent().getStringExtra("roomName");
            String roomLocation = getIntent().getStringExtra("roomLocation");
            String roomId = getIntent().getStringExtra("roomId");
            String roomPlayerCount = getIntent().getStringExtra("roomPlayerCount");
            String playerName = getIntent().getStringExtra("playerName");

            try{
                int roomIdInt = Integer.parseInt(roomId);
                Log.i("nih datanya", playerName + " " + roomIdInt);
                Boolean isPlayerExist = playerdb.isPlayerExist(playerName, roomIdInt);

                if(isPlayerExist){
                    Log.i("tes", "sampe sini");
                    Toast.makeText(this, playerName + " exists!", Toast.LENGTH_SHORT).show();
                    // don't insert the username
                } else {
                    Toast.makeText(this, playerName + " does not exist!", Toast.LENGTH_SHORT).show();

                    String message = "Are you sure you want join this room?";

                    new AlertDialog.Builder(this).setMessage(message).setPositiveButton("Yes", ((dialog, which) -> {
                        playerdb.insertPlayer(Integer.parseInt(roomId), playerName);
                    })).setNegativeButton("No", null).show();


                }
            }catch (NumberFormatException v) {
                Toast.makeText(this, "Invalid room ID: " + roomId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAdapter(String roomId){
        Integer roomIdInt = Integer.parseInt(roomId);
        playerList.setLayoutManager(new LinearLayoutManager(this));
        player_adapter adapter = new player_adapter(playerdb.getPlayerById(roomIdInt));
        playerList.setAdapter(adapter);
    }
}