package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.adapters.player_adapter;
import com.example.playpal.utils.player_database_helper;

public class room_detail extends AppCompatActivity {

    private ImageButton backButton, accountButton;
    private TextView roomNameTextView, fieldNameTextView, playerCountTextView;
    private RecyclerView playerListRecyclerView;
    private player_database_helper playerdb;
    private Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        initialize();

    }

    public void initialize(){
        playerdb = new player_database_helper(this);
        String roomName = getIntent().getStringExtra("roomName");
        String roomLocation = getIntent().getStringExtra("roomLocation");
        String roomId = getIntent().getStringExtra("roomId");
        String roomPlayerCount = getIntent().getStringExtra("roomPlayerCount");

        roomNameTextView = findViewById(R.id.room_detail_room_name);
        roomNameTextView.setText(roomName);
        fieldNameTextView = findViewById(R.id.room_detail_field_name);
        fieldNameTextView.setText(roomLocation);
        playerCountTextView = findViewById(R.id.room_detail_player_count);
        playerCountTextView.setText(roomPlayerCount);
        backButton = findViewById(R.id.backButton);

        accountButton = findViewById(R.id.accountButton);
        playerListRecyclerView = findViewById(R.id.playerList);

        joinButton = findViewById(R.id.room_detail_join_button);
        setListener();
        setAdapter(roomId);
    }

    public void setListener(){

        backButton.setOnClickListener(e -> {
            finish();
        });

        joinButton.setOnClickListener(e -> {
            String roomId = getIntent().getStringExtra("roomId");
            String playerName = getIntent().getStringExtra("playerName");

            try{
                int roomIdInt = Integer.parseInt(roomId);
                boolean isPlayerExist = playerdb.isPlayerExist(playerName, roomIdInt);

                if(isPlayerExist){
                    showToast("You already joined this room!");
                } else {
                    String message = "Are you sure you want join this room?";

                    new AlertDialog.Builder(this).
                            setMessage(message).
                            setPositiveButton("Yes",
                                    ((dialog, which) -> {playerdb.insertPlayer(
                                            Integer.parseInt(roomId), playerName);
                    })).setNegativeButton("No", null).show();

                }
            }catch (NumberFormatException v) {
                showToast("Invalid room ID");
            }
        });
    }

    public void setAdapter(String roomId){
        int roomIdInt = Integer.parseInt(roomId);
        playerListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        player_adapter adapter = new player_adapter(playerdb.getPlayerById(roomIdInt));
        playerListRecyclerView.setAdapter(adapter);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}