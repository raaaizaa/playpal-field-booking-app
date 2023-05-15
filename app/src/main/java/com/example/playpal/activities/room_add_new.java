package com.example.playpal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    field_database_helper fieldDb;
    room_database_helper roomDb;
    player_database_helper playerDb;
    Spinner location_spinner, sport_spinner;
    ImageButton back, account;
    Button addNewRoom;
    EditText addRoomName;
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
        fieldDb = new field_database_helper(this);
        fields = fieldDb.getFieldNames();

        ArrayAdapter locationList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fields);
        locationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(locationList);

        sport_spinner = (Spinner) findViewById(R.id.sport_spinner);
        sport_spinner.setOnItemSelectedListener(this);
        ArrayAdapter sportList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sports);
        sportList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sport_spinner.setAdapter(sportList);

        back = findViewById(R.id.backButton);
        addNewRoom = findViewById(R.id.add_new_room_button);
        addRoomName = findViewById(R.id.add_room_name);
        roomDb = new room_database_helper(this);
        setListener();
    }

    public void setListener(){
        back.setOnClickListener(e -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

        addNewRoom.setOnClickListener(e -> {
            String insertedRoomName = addRoomName.getText().toString();
            String insertedFieldName = location_spinner.getSelectedItem().toString();
            String insertedSport = sport_spinner.getSelectedItem().toString();

            if(!insertedSport.equals("Futsal")){
                Toast.makeText(this, "Sorry! currently unavailable", Toast.LENGTH_SHORT).show();
            }else{
                if(insertedFieldName.equals("Champion Futsal")){
                    if(roomDb.insertRoom(1, insertedRoomName, insertedSport, "Champion Futsal") == true){
                        Toast.makeText(this, "Success adding new room!", Toast.LENGTH_SHORT).show();
                        Log.i("done! horreee", insertedRoomName + " " + insertedSport);

                        Intent intent = new Intent(this, home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Adding new room failed", Toast.LENGTH_SHORT).show();
                    }
                }else if(insertedFieldName.equals("Terminal Futsal")){
                    if(roomDb.insertRoom(2, insertedRoomName, insertedSport, "Terminal Futsal") == true){
                        Toast.makeText(this, "Success adding new room!", Toast.LENGTH_SHORT).show();
                        Log.i("done! horreee", insertedRoomName + " " + insertedSport);

                        Intent intent = new Intent(this, home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Adding new room failed", Toast.LENGTH_SHORT).show();
                    }


                }else if(insertedFieldName.equals("Elang Futsal")){
                    if(roomDb.insertRoom(3, insertedRoomName, insertedSport, "Elang Futsal") == true){
                        Toast.makeText(this, "Success adding new room!", Toast.LENGTH_SHORT).show();
                        Log.i("done! horreee", insertedRoomName + " " + insertedSport);

                        Intent intent = new Intent(this, home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Adding new room failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            Log.i("tes dapet gak ni datanya", insertedRoomName + " " + insertedFieldName + " " + insertedSport);
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