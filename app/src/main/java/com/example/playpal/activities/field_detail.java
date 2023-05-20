package com.example.playpal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playpal.R;
import com.example.playpal.adapters.room_adapter;
import com.example.playpal.utils.room_database_helper;

public class field_detail extends AppCompatActivity{

    private room_database_helper roomdb;
    private TextView fieldNameTextView, fieldLocationTextView;
    private ImageButton backButton, accountButton, mapButton;
    private RecyclerView roomListRecyclerView;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_detail);

        initialize();
    }

    public void initialize() {
        roomdb = new room_database_helper(this);
        String fieldId = getIntent().getStringExtra("fieldId");
        String fieldName = getIntent().getStringExtra("fieldName");
        String fieldLocation = getIntent().getStringExtra("fieldLocation");
        String username = getIntent().getStringExtra("username");
        double latitude = getIntent().getDoubleExtra("fieldLatitude", 0.0);
        double longitude = getIntent().getDoubleExtra("fieldLongitude", 0.0);
        Log.i("tes lat long", "lat: " + String.valueOf(latitude) + " long: " + String.valueOf(longitude));

        fieldNameTextView = findViewById(R.id.field_detail_name);
        fieldNameTextView.setText(fieldName);

        fieldLocationTextView = findViewById(R.id.field_detail_location);
        fieldLocationTextView.setText(fieldLocation);

        mapButton = findViewById(R.id.field_detail_map);

        backButton = findViewById(R.id.backButton);
        accountButton = findViewById(R.id.accountButton);
        roomListRecyclerView = findViewById(R.id.field_roomlist);
        setListener(username, latitude, longitude);
        setAdapter(fieldId);
    }

    public void setAdapter(String fieldId) {
        Integer fieldIdInt = Integer.parseInt(fieldId);
        roomListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String username = getIntent().getStringExtra("username");
        room_adapter adapter = new room_adapter(roomdb.getRoomById(fieldIdInt), this, username);
        roomListRecyclerView.setAdapter(adapter);
        roomdb.close();
    }

    public void setListener(String username, double latitude, double longitude) {
        backButton.setOnClickListener(e -> {
            backToHomepage(username);
        });

        mapButton.setOnClickListener(e -> {
            openMap(username, latitude, longitude);
        });
    }

    public void backToHomepage(String username){
        Intent intent = new Intent(this, home.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    public void openMap(String username, double latitude, double longitude){
        Intent intent = new Intent(this, maps.class);
        intent.putExtra("username", username);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}