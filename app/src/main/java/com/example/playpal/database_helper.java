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
    public static final String PLAYER_DB = "Player.db";

    public database_helper(Context context){
        super(context, LOGIN_DB, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" +
                "username TEXT PRIMARY KEY, " +
                "email TEXT, " +
                "password TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
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

}
