package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

    public boolean insertDummyRoom(Integer roomId, Integer fieldId, String name, String categories, String location ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(checkRoom(roomId, fieldId, name, categories, location)){
            return false;
        }else{

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

    }

    public boolean insertRoom(Integer fieldId, String name, String categories, String location ){
        Log.i("masuk ga sih", "masuk");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        Cursor cursorCheck = db.rawQuery("SELECT * FROM room WHERE field_id = ?", new String[]{String.valueOf(fieldId)});

        if(cursorCheck.moveToFirst()){
            do{
                int roomId = cursorCheck.getInt(0);
                Log.i("ini si roomId: ", String.valueOf(roomId));
            }while (cursorCheck.moveToNext());
        }
        cursorCheck.close();

        Cursor cursor = db.rawQuery("SELECT MAX(room_id) FROM room WHERE field_id = ?", new String[]{String.valueOf(fieldId)});

        int latestRoomId = 0;

        if(cursor.moveToFirst()){
            latestRoomId = cursor.getInt(0);
        }

        int roomId = latestRoomId + 1;
        Log.i("ini roomId yang digenerate: ", String.valueOf(roomId));

        if(isRoomExist(name, location)){
            Log.i("lah ko ada", "laaahh??");
            db.close();
            return false;
        }else{
            contentValues.put("room_id", roomId);
            contentValues.put("field_id", fieldId);
            contentValues.put("room_name", name);
            contentValues.put("categories", categories);
            contentValues.put("location", location);

            long results = db.insert("room", null, contentValues);
            Log.i("teeessss masuk kagak", roomId + " " + fieldId + " " + name + " " + categories + " " + location);
            if(results == -1){
                Log.i("kesini", "ke results == -1");
                db.close();
                return false;
            }else{
                Log.i("kesini", "ke results == 0");
                db.close();
                return true;
            }
        }

    }

    public boolean checkRoom(Integer roomId, Integer fieldId, String name, String categories, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM room WHERE room_id = ? AND field_id = ? OR room_name = ? OR categories = ? OR location = ?", new String[]{String.valueOf(roomId), String.valueOf(fieldId), name, categories, location});

        if(cursor.moveToFirst()){
            //Record exist
            cursor.close();
            return true;
        }else{
            //Record available
            cursor.close();
            return false;
        }
    }

    public boolean isRoomExist(String name, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM room WHERE room_name = ? AND location = ?", new String[]{name, location});

        if(cursor.moveToFirst()){
            //Record exist
            cursor.close();
            return true;
        }else{
            //Record available
            cursor.close();
            return false;
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
