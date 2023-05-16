package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.playpal.R;
import com.example.playpal.utils.field_database_helper;
import com.example.playpal.utils.player_database_helper;
import com.example.playpal.utils.room_database_helper;

import java.util.Arrays;
import java.util.List;

public class room_add_new extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private field_database_helper fielddb;
    private room_database_helper roomdb;
    private Spinner locationSpinner, sportSpinner;
    private ImageButton backButton, accountButton;
    private Button addNewRoomButton;
    private EditText roomNameEditText;
    private List<String> fields;
    private final List<String> sports = Arrays.asList("Sepak Bola", "Futsal", "Basket", "Badminton");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add_new);

        initialize();
    }

    public void initialize(){
        fielddb = new field_database_helper(this);
        roomdb = new room_database_helper(this);

        locationSpinner = findViewById(R.id.location_spinner);
        fields = fielddb.getFieldNames();
        ArrayAdapter locationList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fields);
        locationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationList);

        sportSpinner = findViewById(R.id.sport_spinner);
        sportSpinner.setOnItemSelectedListener(this);
        ArrayAdapter sportList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sports);
        sportList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(sportList);

        String username = getIntent().getStringExtra("username");
        backButton = findViewById(R.id.backButton);
        addNewRoomButton = findViewById(R.id.add_new_room_button);
        roomNameEditText = findViewById(R.id.add_room_name);

        setListener(username);
    }

    public void setListener(String username){
        backButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            intent.putExtra("username", username);
            finish();
        });

        addNewRoomButton.setOnClickListener(e -> {
            String insertedRoomName = roomNameEditText.getText().toString();
            String insertedFieldName = locationSpinner.getSelectedItem().toString();
            String insertedSport = sportSpinner.getSelectedItem().toString();

            if(!insertedSport.equals("Futsal")){
                Toast.makeText(this, "Sorry! currently unavailable", Toast.LENGTH_SHORT).show();
            }else{
                switch (insertedFieldName) {
                    case "Champion Futsal":
                        if (roomdb.insertRoom(1, insertedRoomName, insertedSport, "Champion Futsal")) {
                            showToast("Success adding new room!");
                            Intent intent = new Intent(this, home.class);
                            intent.putExtra("username", username);
                            finish();
                        } else {
                            showToast("Failed!");
                        }
                        break;
                    case "Terminal Futsal":
                        if (roomdb.insertRoom(2, insertedRoomName, insertedSport, "Terminal Futsal")) {
                            showToast("Success adding new room!");
                            Intent intent = new Intent(this, home.class);
                            intent.putExtra("username", username);
                            finish();
                        } else {
                            showToast("Failed!");
                        }
                        break;
                    case "Elang Futsal":
                        if (roomdb.insertRoom(3, insertedRoomName, insertedSport, "Elang Futsal")) {
                            showToast("Success adding new room!");
                            Intent intent = new Intent(this, home.class);
                            intent.putExtra("username", username);
                            finish();
                        } else {
                            showToast("Failed!");
                        }
                        break;
                }
            }
            fielddb.close();
            roomdb.close();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Generated
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Generated
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}