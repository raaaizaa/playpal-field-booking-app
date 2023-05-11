package com.example.playpal;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class room_fragment extends Fragment {

    RecyclerView roomRV;
    View view;
    room_database_helper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_room, container, false);
        roomRV = view.findViewById(R.id.roomRV);
        roomRV.setHasFixedSize(true);

        insertData();
        setAdapter();
        return view;
    }

    public void insertData(){
        Context context = getContext();
        db = new room_database_helper(context);

        db.insertRoom(101, 001, "join aja", "Futsal", "Champion Futsal");
        db.insertRoom(102, 001, "SPARRING BRIGEZ VS XTC", "Futsal", "Champion Futsal");
        db.insertRoom(103, 001, "FUTSAL PEMUDA BINUS REVOLUSIONER", "Futsal", "Champion Futsal");

        db.insertRoom(201, 002, "kuy", "Futsal", "Terminal Futsal");
        db.insertRoom(202, 002, "latihan kesebelasan NANKATSU FC", "Futsal", "Terminal Futsal");

        db.insertRoom(301, 003, "ngajedog", "Futsal", "Elang Futsal");
        db.insertRoom(302, 003, "duh apa ya", "Futsal", "Elang Futsal");
        db.insertRoom(303, 003, "JANGAN JOIN!!!", "Futsal", "Elang Futsal");
    }

    public void setAdapter(){
        roomRV.setLayoutManager(new LinearLayoutManager(getContext()));
        room_adapter adapter = new room_adapter(db.getAllRooms());
        roomRV.setAdapter(adapter);
    }
}