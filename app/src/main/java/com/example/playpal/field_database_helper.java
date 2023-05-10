package com.example.playpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class field_database_helper extends SQLiteOpenHelper {

    public static final String FIELD_DB = "Field.db";

    public field_database_helper(Context context){
        super(context, FIELD_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE field(" +
                "field_id INTEGER PRIMARY KEY, " +
                "field_name TEXT, " +
                "field_location TEXT, " +
                "field_picture BLOB)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS field");
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
}
