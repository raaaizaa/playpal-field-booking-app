package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.playpal.models.player;

import java.util.ArrayList;
import java.util.List;

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
                "FOREIGN KEY (room_id) REFERENCES room(room_id))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS player");
    }

    public boolean insertDummyPlayer(Integer playerId, Integer roomId, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean playerExist = checkPlayer(playerId, name, roomId);

        if(playerExist){
            return false;
        }else{
            inputContent(playerId, roomId, name);
            long results = db.insert("player", null, inputContent(playerId, roomId, name));

            db.close();
            return results != -1;
        }
    }

    public boolean insertPlayer(Integer roomId, String name){
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if player has joined room before
        if (isPlayerExist(name, roomId)) {
            return false;
        }

        int playerId = getMaxPlayerId(roomId) + 1;

        ContentValues contentValues = inputContent(playerId, roomId, name);
        long results = db.insert("player", null, contentValues);

        db.close();
        return results != -1;
    }

    public int getMaxPlayerId(int roomId){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectMax = "SELECT MAX(player_id) FROM player WHERE room_id = ?";
        Cursor cursor = db.rawQuery(selectMax, new String[]{String.valueOf(roomId)});

        int latestPlayerId = 0;
        if (cursor.moveToFirst()) {
            latestPlayerId = cursor.getInt(0);
        }
        cursor.close();
        return latestPlayerId;
    }

    public boolean checkPlayer(Integer id, String name, Integer roomId){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM player WHERE player_id = ? AND player_name = ? AND room_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id), name, String.valueOf(roomId)});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

    public boolean isPlayerExist(String name, Integer roomId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM player WHERE player_name = ? AND room_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name, String.valueOf(roomId)});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

    public List<player> getPlayerById(int roomId){
        List<player> players = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM player WHERE room_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(roomId)});

        if (cursor.moveToFirst()) {
            do{
                player player = new player(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2)
                );
                players.add(player);
            }while(cursor.moveToNext());
        }

        cursor.close();

        return players;
    }

    public int countPlayersInRoom(Integer roomId){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT COUNT(*) FROM player WHERE room_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(roomId)});

        int count = 0;

        if(cursor.moveToFirst()){
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return count;
    }

    public ContentValues inputContent(Integer playerId, Integer roomId, String name){
        ContentValues contentValues = new ContentValues();

        contentValues.put("player_id", playerId);
        contentValues.put("room_id", roomId);
        contentValues.put("player_name", name);

        return contentValues;
    }
}
