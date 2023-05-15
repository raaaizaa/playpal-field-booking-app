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

    RecyclerView roomRV;
    View view;
    room_database_helper roomdb;
    player_database_helper playerdb;
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

        insertDummyData();
        setAdapter();
        return view;
    }

    public void insertDummyData(){
        Context context = getContext();
        roomdb = new room_database_helper(context);

        roomdb.insertDummyRoom(101, 1, "join aja", "Futsal", "Champion Futsal");
        roomdb.insertDummyRoom(102, 1, "SPARRING BRIGEZ VS XTC", "Futsal", "Champion Futsal");
        roomdb.insertDummyRoom(103, 1, "FUTSAL PEMUDA BINUS REVOLUSIONER", "Futsal", "Champion Futsal");

        roomdb.insertDummyRoom(201, 2, "kuy", "Futsal", "Terminal Futsal");
        roomdb.insertDummyRoom(202, 2, "latihan kesebelasan NANKATSU SC", "Futsal", "Terminal Futsal");

        roomdb.insertDummyRoom(301, 3, "ngajedog", "Futsal", "Elang Futsal");

        playerdb = new player_database_helper(context);

        playerdb.insertDummyPlayer(10101, 101, "fakhri");
        playerdb.insertDummyPlayer(10102, 101, "arvito");
        playerdb.insertDummyPlayer(10103, 101, "rasyid");
        playerdb.insertDummyPlayer(10104, 101, "kiting");

        playerdb.insertDummyPlayer(10201, 102, "ASEP BRIGEZ");
        playerdb.insertDummyPlayer(10202, 102, "SYARIF BRIGEZ");
        playerdb.insertDummyPlayer(10203, 102, "EKO BRIGEZ");
        playerdb.insertDummyPlayer(10204, 102, "SYARIFUDDIN XTC");
        playerdb.insertDummyPlayer(10205, 102, "TARMIJI XTC");
        playerdb.insertDummyPlayer(10206, 102, "JHON XTC");
        playerdb.insertDummyPlayer(10207, 102, "UDIN XTC");
        playerdb.insertDummyPlayer(10208, 102, "dadang");

        playerdb.insertDummyPlayer(10301, 103, "bian");
        playerdb.insertDummyPlayer(10302, 103, "ken");
        playerdb.insertDummyPlayer(10303, 103, "dyl");

        playerdb.insertDummyPlayer(20101, 201, "maman");
        playerdb.insertDummyPlayer(20102, 201, "arif");
        playerdb.insertDummyPlayer(20103, 201, "aqil");
        playerdb.insertDummyPlayer(20104, 201, "heru");

        playerdb.insertDummyPlayer(20201, 202, "GENZO WAKABAYASHI");
        playerdb.insertDummyPlayer(20202, 202, "TAKESHI KISHIDA");
        playerdb.insertDummyPlayer(20203, 202, "KOJI NISHIO");
        playerdb.insertDummyPlayer(20204, 202, "MASAO NAKAYAMA");
        playerdb.insertDummyPlayer(20205, 202, "HANJI URABE");
        playerdb.insertDummyPlayer(20206, 202, "SHINGO TAKASUGI");
        playerdb.insertDummyPlayer(20207, 202, "HAJIME TAKI");
        playerdb.insertDummyPlayer(20208, 202, "MAMORU IZAWA");
        playerdb.insertDummyPlayer(20209, 202, "TEPPEI KISUGI");
        playerdb.insertDummyPlayer(20210, 202, "TSUBASA OZORA");
        playerdb.insertDummyPlayer(20211, 202, "TARO MISAKI");

        playerdb.insertDummyPlayer(30101, 301, "James");
    }


    public void setAdapter(){
        roomRV.setLayoutManager(new LinearLayoutManager(getContext()));
        room_adapter adapter = new room_adapter(roomdb.getAllRooms(),  getContext(), username);
        roomRV.setAdapter(adapter);
    }
}