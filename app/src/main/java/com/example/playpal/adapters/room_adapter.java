package com.example.playpal.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playpal.R;
import com.example.playpal.models.player;
import com.example.playpal.models.room;
import com.example.playpal.activities.room_detail;
import com.example.playpal.utils.player_database_helper;

import java.util.List;

public class room_adapter extends RecyclerView.Adapter<room_adapter.ViewHolder>{

    private List<room> rooms;
    private List<player> players;
    private Context context;
    player_database_helper playerdb;
    private String username;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, location, count;
        Button roomJoinButton;

        public ViewHolder(@NonNull View view) {
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

    @NonNull
    @Override
    public room_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull room_adapter.ViewHolder holder, int position) {
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
            context.startActivity(intent);
        });

        holder.roomJoinButton.setOnClickListener(e -> {
            String roomId = room.getRoomId().toString();
            Log.i("nih", "tes");
//            playerdb.checkPlayer(, roomId);
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public int getPlayerCount(Integer roomId){
        return playerdb.countPlayersInRoom(roomId);
    }

}
