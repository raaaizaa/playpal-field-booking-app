package com.example.playpal.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playpal.R;
import com.example.playpal.adapters.room_adapter;
import com.example.playpal.utils.player_database_helper;
import com.example.playpal.utils.room_database_helper;

public class room_fragment extends Fragment {

    room_database_helper roomdb;
    player_database_helper playerdb;
    RecyclerView roomRV;
    View view;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_room, container, false);
        roomRV = view.findViewById(R.id.roomRV);
        roomRV.setHasFixedSize(true);

        Bundle args = getArguments();

        if(args != null){
            username = args.getString("username");
        }

        setAdapter();
        return view;
    }


    public void setAdapter(){
        roomdb = new room_database_helper(getContext());
        roomRV.setLayoutManager(new LinearLayoutManager(getContext()));
        room_adapter adapter = new room_adapter(roomdb.getAllRooms(),  getContext(), username);
        roomRV.setAdapter(adapter);
    }
}