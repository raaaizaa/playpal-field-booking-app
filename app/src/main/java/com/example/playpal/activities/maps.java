package com.example.playpal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.playpal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class maps extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap map;
    private float ZOOM_LEVEL = 15.0f;
    private double LAT;
    private double LONG;
    private ImageButton backButton, accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);

        mapFragment.getMapAsync(this);
        initialize();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LAT = getIntent().getDoubleExtra("latitude", 0.0);
        LONG = getIntent().getDoubleExtra("longitude", 0.0);
        LatLng latlng = new LatLng(LAT, LONG);
        Log.i("tes latlong", "lat: " + LAT + " long: " + LONG);
        map.addMarker(new MarkerOptions().position(latlng).title("BINUS"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, ZOOM_LEVEL));
    }

    public void initialize(){
        String username = getIntent().getStringExtra("username");
        backButton = findViewById(R.id.backButton);
        accountButton = findViewById(R.id.accountButton);
        setListener(username);
    }

    public void setListener(String username){
        backButton.setOnClickListener(e -> {
            Intent intent = new Intent();
            intent.putExtra("username", username);
            finish();
        });
    }


}