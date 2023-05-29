package com.example.playpal.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playpal.R;
import com.example.playpal.adapters.field_adapter;
import com.example.playpal.utils.field_database_helper;

import java.io.ByteArrayOutputStream;

public class field_fragment extends Fragment {

    field_database_helper db;
    RecyclerView fieldRV;
    View view;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_field, container, false);
        fieldRV = view.findViewById(R.id.fieldRV);
        fieldRV.setHasFixedSize(true);
        Bundle args = getArguments();

        if(args != null){
            username = args.getString("username");
        }

        setAdapter(username);
        return view;
    }

    public void setAdapter(String username){
        db = new field_database_helper(getContext());
        fieldRV.setLayoutManager(new LinearLayoutManager((getContext()), LinearLayoutManager.HORIZONTAL, false));
        field_adapter adapter = new field_adapter(db.getAllFields(), getContext(), username);
        fieldRV.setAdapter(adapter);
    }
}