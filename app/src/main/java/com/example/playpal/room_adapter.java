package com.example.playpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class room_adapter extends RecyclerView.Adapter<room_adapter.ViewHolder> {

    private List<room> rooms;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, location;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.room_name);
            location = view.findViewById(R.id.room_location);
        }
    }

    public room_adapter(List<room> rooms){
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public room_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull room_adapter.ViewHolder holder, int position) {
        room room = rooms.get(position);
        holder.name.setText(room.getRoomName());
        holder.location.setText(room.getLocation());
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}
