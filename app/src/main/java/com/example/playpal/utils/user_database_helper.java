package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class user_database_helper extends SQLiteOpenHelper {

    public static final String LOGIN_DB = "Login.db";

    public user_database_helper(Context context){
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
        ContentValues contentValues = inputContent(username, email, password);

        long results = db.insert("user", null, contentValues);

        return results != -1;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM user WHERE username = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {username});

        return cursor.getCount() > 0;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});

        return cursor.getCount() > 0;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{username, password});

        return cursor.getCount() > 0;
    }

    public ContentValues inputContent(String username, String email, String password){
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        return contentValues;
    }
}
