package com.example.playpal.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playpal.R;
import com.example.playpal.models.room;
import com.example.playpal.activities.room_detail;
import com.example.playpal.utils.player_database_helper;

import java.util.List;

public class room_adapter extends RecyclerView.Adapter<room_adapter.ViewHolder>{

    player_database_helper playerdb;
    private final List<room> rooms;
    private final Context context;
    private final String username;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, location, count;
        Button roomJoinButton;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.room_name);
            location = view.findViewById(R.id.room_location);
            count = view.findViewById(R.id.player_count);
            roomJoinButton = view.findViewById(R.id.room_join_button);
        }
    }

    public room_adapter(List<room> rooms, Context context, String username){
        this.rooms = rooms;
        this.context = context;
        this.username = username;
    }

    @Override
    public room_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(room_adapter.ViewHolder holder, int position) {
        playerdb = new player_database_helper(holder.itemView.getContext());
        room room = rooms.get(position);
        holder.name.setText(room.getRoomName());
        holder.location.setText(room.getLocation());
        holder.count.setText(String.valueOf(getPlayerCount(room.getRoomId())));

        holder.itemView.setOnClickListener(e -> {
            String roomId = room.getRoomId().toString();
            String roomName = holder.name.getText().toString();
            String roomLocation = holder.location.getText().toString();
            String roomPlayerCount = holder.count.getText().toString();

            Intent intent = new Intent(context, room_detail.class);
            intent.putExtra("roomName", roomName);
            intent.putExtra("roomLocation", roomLocation);
            intent.putExtra("roomId", roomId);
            intent.putExtra("roomPlayerCount", roomPlayerCount);
            intent.putExtra("playerName", username);
            context.startActivity(intent);
            playerdb.close();
        });

        holder.roomJoinButton.setOnClickListener(e -> {
            String roomId = room.getRoomId().toString();

            try {
                int roomIdInt = Integer.parseInt(roomId);
                boolean isPlayerExist = playerdb.isPlayerExist(username, roomIdInt);
                if(isPlayerExist){
                    showToast("You already joined this room!");
                } else {
                    String message = "Are you sure you want join this room?";

                    new AlertDialog.Builder(context).setMessage(message).
                            setPositiveButton("Yes", ((dialog, which) -> playerdb.insertPlayer(Integer.parseInt(roomId), username))).setNegativeButton("No", null).show();
                }
            } catch (NumberFormatException v) {
                showToast("Invalid room Id");
            }
            playerdb.close();

        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public int getPlayerCount(Integer roomId){
        return playerdb.countPlayersInRoom(roomId);
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
