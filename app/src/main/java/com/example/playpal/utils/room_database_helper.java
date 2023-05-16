package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.playpal.models.room;

import java.util.ArrayList;
import java.util.List;

public class room_database_helper extends SQLiteOpenHelper {

    public static final String ROOM_DB = "Room.db";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS room");
    }

    public boolean insertDummyRoom(Integer roomId, Integer fieldId, String name, String categories, String location ){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean roomExist = checkRoom(roomId, fieldId, name, categories, location);

        if(roomExist){
            return false;
        }else{
            ContentValues contentValues = inputContent(roomId, fieldId, name, categories, location);
            long results = db.insert("room", null, contentValues);
            return results != -1;
        }

    }

    public boolean insertRoom(Integer fieldId, String name, String categories, String location ){
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if room with the same name and location already exists
        if(isRoomExist(name, location)){
            return false;
        }

        // Generate new room ID
        int roomId = getMaxRoomId(fieldId) + 1;

        ContentValues contentValues = inputContent(roomId, fieldId, name, categories, location);

        long results = db.insert("room", null, contentValues);

        return results != -1;
    }

    // Helper method to get the max room ID for a given field ID
    public int getMaxRoomId(int fieldId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectMax = "SELECT MAX(room_id) FROM room WHERE field_id = ?";
        Cursor cursor = db.rawQuery(selectMax, new String[]{String.valueOf(fieldId)});

        int latestRoomId = 0;
        if(cursor.moveToFirst()){
            latestRoomId = cursor.getInt(0);
        }
        return latestRoomId;
    }


    public boolean checkRoom(Integer roomId, Integer fieldId, String name, String categories, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM room WHERE room_id = ? AND field_id = ? AND room_name = ? AND categories = ? AND location = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(roomId), String.valueOf(fieldId), name, categories, location});

        boolean results = cursor.moveToFirst();
        cursor.close();

        return results;
    }

    public boolean isRoomExist(String name, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM room WHERE room_name = ? AND location = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name, location});

        boolean results = cursor.moveToFirst();
        cursor.close();

        return results;
    }

    public List<room> getAllRooms(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<room> rooms = new ArrayList<>();
        String selectQuery = "SELECT * FROM room";
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
        return rooms;
    }

    public ContentValues inputContent(Integer roomId, Integer fieldId, String name, String categories, String location ){
        ContentValues contentValues = new ContentValues();

        contentValues.put("room_id", roomId);
        contentValues.put("field_id", fieldId);
        contentValues.put("room_name", name);
        contentValues.put("categories", categories);
        contentValues.put("location", location);

        return contentValues;
    }
}
