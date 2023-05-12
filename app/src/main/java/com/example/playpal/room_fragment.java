package com.example.playpal;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class room_fragment extends Fragment {

    RecyclerView roomRV;
    View view;
    room_database_helper roomdb;
    player_database_helper playerdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_room, container, false);
        roomRV = view.findViewById(R.id.roomRV);
        roomRV.setHasFixedSize(true);
        Log.i("tes", "tes");

        insertData();
        setAdapter();
        return view;
    }

    public void insertData(){
        Context context = getContext();
        roomdb = new room_database_helper(context);


        roomdb.insertRoom(101, 1, "join aja", "Futsal", "Champion Futsal");
        roomdb.insertRoom(102, 1, "SPARRING BRIGEZ VS XTC", "Futsal", "Champion Futsal");
        roomdb.insertRoom(103, 1, "FUTSAL PEMUDA BINUS REVOLUSIONER", "Futsal", "Champion Futsal");

        roomdb.insertRoom(201, 2, "kuy", "Futsal", "Terminal Futsal");
        roomdb.insertRoom(202, 2, "latihan kesebelasan NANKATSU SC", "Futsal", "Terminal Futsal");

        roomdb.insertRoom(301, 3, "ngajedog", "Futsal", "Elang Futsal");

        playerdb = new player_database_helper(context);

        playerdb.insertPlayer(10101, 101, "fakhri");
        playerdb.insertPlayer(10102, 101, "arvito");
        playerdb.insertPlayer(10103, 101, "rasyid");
        playerdb.insertPlayer(10104, 101, "kiting");

        playerdb.insertPlayer(10201, 102, "ASEP BRIGEZ");
        playerdb.insertPlayer(10202, 102, "SYARIF BRIGEZ");
        playerdb.insertPlayer(10203, 102, "EKO BRIGEZ");
        playerdb.insertPlayer(10204, 102, "SYARIFUDDIN XTC");
        playerdb.insertPlayer(10205, 102, "TARMIJI XTC");
        playerdb.insertPlayer(10206, 102, "JHON XTC");
        playerdb.insertPlayer(10207, 102, "UDIN XTC");
        playerdb.insertPlayer(10208, 102, "dadang");

        playerdb.insertPlayer(10301, 103, "bian");
        playerdb.insertPlayer(10302, 103, "ken");
        playerdb.insertPlayer(10303, 103, "dyl");

        playerdb.insertPlayer(20101, 201, "maman");
        playerdb.insertPlayer(20102, 201, "arif");
        playerdb.insertPlayer(20103, 201, "aqil");
        playerdb.insertPlayer(20104, 201, "heru");

        playerdb.insertPlayer(20201, 202, "GENZO WAKABAYASHI");
        playerdb.insertPlayer(20202, 202, "TAKESHI KISHIDA");
        playerdb.insertPlayer(20203, 202, "KOJI NISHIO");
        playerdb.insertPlayer(20204, 202, "MASAO NAKAYAMA");
        playerdb.insertPlayer(20205, 202, "HANJI URABE");
        playerdb.insertPlayer(20206, 202, "SHINGO TAKASUGI");
        playerdb.insertPlayer(20207, 202, "HAJIME TAKI");
        playerdb.insertPlayer(20208, 202, "MAMORU IZAWA");
        playerdb.insertPlayer(20209, 202, "TEPPEI KISUGI");
        playerdb.insertPlayer(20210, 202, "TSUBASA OZORA");
        playerdb.insertPlayer(20211, 202, "TARO MISAKI");

        playerdb.insertPlayer(30101, 301, "James");


    }

    public void setAdapter(){
        roomRV.setLayoutManager(new LinearLayoutManager(getContext()));
        room_adapter adapter = new room_adapter(roomdb.getAllRooms());
        roomRV.setAdapter(adapter);
    }
}