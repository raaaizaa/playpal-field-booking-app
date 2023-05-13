package com.example.playpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class player_database_helper extends SQLiteOpenHelper {

    public static final String PLAYER_DB = "Player.db";
    List<player> players;

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

        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS player");
    }

    public boolean insertPlayer(Integer playerId, Integer roomId, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id", playerId);
        contentValues.put("room_id", roomId);
        contentValues.put("player_name", name);

        long results = db.insert("player", null, contentValues);

        if(results == -1){
            return false;
        }else{
            return true;
        }
    }

    public void dropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS player");
        db.close();
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

    public player getPlayer(String name, int roomId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM player WHERE player_name = ? AND room_id = ?", new String[]{name, String.valueOf(roomId)});

        if (cursor.moveToFirst()) {
            int playerIdIndex = cursor.getColumnIndex("player_id");
            int playerNameIndex = cursor.getColumnIndex("player_name");

            if (playerIdIndex >= 0 && playerNameIndex >= 0) {
                int playerId = cursor.getInt(playerIdIndex);
                String playerName = cursor.getString(playerNameIndex);

                return new player(playerId, roomId, playerName);
            }
        }

        return null;
    }

    public List<player> getPlayerById(int roomId){
        List<player> players = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM player WHERE room_id = ?", new String[]{String.valueOf(roomId)});

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
        db.close();
        return players;
    }

    public int countPlayersInRoom(Integer roomId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM player WHERE room_id = ?", new String[]{String.valueOf(roomId)});
        int count = 0;

        if(cursor.moveToFirst()){
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }

}
