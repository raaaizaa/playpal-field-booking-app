package com.example.playpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
