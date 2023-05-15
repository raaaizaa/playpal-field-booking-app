package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.playpal.R;
import com.example.playpal.models.field;
import com.example.playpal.utils.field_database_helper;

import java.util.Arrays;
import java.util.List;

public class room_add_new extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    field_database_helper db;
    Spinner location_spinner, sport_spinner;
    ImageButton back, account;
    List<String> fields;
    List<String> sports = Arrays.asList("Sepak Bola", "Futsal", "Basket", "Badminton");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add_new);

        initialize();
    }

    public void initialize(){
        location_spinner = (Spinner) findViewById(R.id.location_spinner);
        db = new field_database_helper(this);
        fields = db.getFieldNames();

        ArrayAdapter locationList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fields);
        locationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(locationList);

        sport_spinner = (Spinner) findViewById(R.id.sport_spinner);
        sport_spinner.setOnItemSelectedListener(this);
        ArrayAdapter sportList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sports);
        sportList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sport_spinner.setAdapter(sportList);

        back = findViewById(R.id.backButton);
        setListener();
    }

    public void setListener(){
        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}