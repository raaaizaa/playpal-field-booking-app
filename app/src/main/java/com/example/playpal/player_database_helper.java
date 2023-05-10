package com.example.playpal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class player_database_helper extends SQLiteOpenHelper {

    public static final String PLAYER_DB = "Player.db";

    public player_database_helper(Context context){
        super(context, PLAYER_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE player(" +
                "player_id INTEGER PRIMARY KEY, " +
                "room_id INTEGER, " +
                "player_name TEXT," +
                "FOREIGN KEY (room_id) REFERENCES room(room_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS player");
    }

    public boolean checkPlayer(String name, Integer roomId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM player WHERE player_name = ? AND room_id = ?", new String[]{name, String.valueOf(roomId)});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
