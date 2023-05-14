package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.playpal.models.room;

import java.util.ArrayList;
import java.util.List;

public class room_database_helper extends SQLiteOpenHelper {

    public static final String ROOM_DB = "Room.db";
    List<room> rooms;

    public room_database_helper(Context context){
        super(context, ROOM_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE room(" +
                "room_id INTEGER PRIMARY KEY, " +
                "field_id INTEGER, " +
                "room_name TEXT, " +
                "categories TEXT, " +
                "location TEXT, " +
                "FOREIGN KEY (field_id) REFERENCES field(field_id))"
        );

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS room");
    }

    public void dropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS room");
        db.close();
    }

    public boolean insertRoom(Integer roomId, Integer fieldId, String name, String categories, String location ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("room_id", roomId);
        contentValues.put("field_id", fieldId);
        contentValues.put("room_name", name);
        contentValues.put("categories", categories);
        contentValues.put("location", location);

        long results = db.insert("room", null, contentValues);

        if(results == -1){
            return false;
        }else{
            return true;
        }
    }


    public List<room> getAllRooms(){
        List<room> rooms = new ArrayList<>();

        String selectQuery = "SELECT * FROM room";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                room room = new room(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                rooms.add(room);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return rooms;
    }

    public List<room> getRoomById(Integer fieldId){
        List<room> rooms = new ArrayList<>();
        String selectQuery = "SELECT * FROM room WHERE field_id = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(fieldId)});
        if(cursor.moveToFirst()){
            do{
                room room = new room(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                rooms.add(room);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rooms;
    }
}
