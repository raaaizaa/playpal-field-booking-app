package com.example.playpal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playpal.R;
import com.example.playpal.models.player;
import com.example.playpal.utils.player_database_helper;

import java.util.List;

public class player_adapter extends RecyclerView.Adapter<player_adapter.ViewHolder> {

    private List<player> players;
    player_database_helper playerdb;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;


        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.player_name);
        }
    }

    public player_adapter(List<player> players){
        this.players = players;
    }

    @NonNull
    @Override
    public player_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull player_adapter.ViewHolder holder, int position) {
        playerdb = new player_database_helper(holder.itemView.getContext());
        player player = players.get(position);
        holder.name.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}