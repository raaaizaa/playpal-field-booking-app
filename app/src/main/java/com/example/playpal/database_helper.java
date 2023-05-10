package com.example.playpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

public class database_helper extends SQLiteOpenHelper {

    public static final String LOGIN_DB = "Login.db";
    public static final String FIELD_DB = "Field.db";
    public static final String ROOM_DB = "Room.db";

    public database_helper(Context context){
        super(context, LOGIN_DB, null, 1);
        super(context, FIELD_DB, null, 1);
    }

    public field_database_helper(Context context){
        super(context, FIELD_DB, null, 1);

    }

    public room_database_helper(Context context){
        super(context, ROOM_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" +
                "username TEXT PRIMARY KEY, " +
                "email TEXT, " +
                "password TEXT)"
        );

        db.execSQL("CREATE TABLE field(" +
                "field_id INTEGER PRIMARY KEY, " +
                "field_name TEXT, " +
                "field_location TEXT, " +
                "field_picture BLOB)"
        );

        db.execSQL("CREATE TABLE room(" +
                "room_id INTEGER PRIMARY KEY, " +
                "field_id INTEGER, " +
                "room_name TEXT, " +
                "categories TEXT, " +
                "location TEXT, " +
                "FOREIGN KEY (field_id) REFERENCES field(field_id))"
        );

        db.execSQL("CREATE TABLE player(" +
                "player_id INTEGER PRIMARY KEY, " +
                "room_id INTEGER, " +
                "player_name TEXT," +
                "FOREIGN KEY (room_id) REFERENCES room(room_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS field");
        db.execSQL("DROP TABLE IF EXISTS room");
        db.execSQL("DROP TABLE IF EXISTS player");
    }

    public boolean insertUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long results = db.insert("user", null, contentValues);

        if(results == - 1){
            return false;
        }else{
            return  true;
        }
    }

    public boolean insertField(Integer id, String name, String location, byte[] picture){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("field_id", id);
        contentValues.put("field_name", name);
        contentValues.put("field_location", location);
        contentValues.put("field_picture", picture);

        long results = db.insert("field", null, contentValues);

        if(results == -1){
            return false;
        }else{
            return true;
        }
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

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[] {username});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email = ?", new String[] {email});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
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
