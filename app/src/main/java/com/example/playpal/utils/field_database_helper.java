package com.example.playpal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.playpal.models.field;

import java.util.ArrayList;
import java.util.List;

public class field_database_helper extends SQLiteOpenHelper {

    public static final String FIELD_DB = "Field.db";
    List<field> fields;

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

    public List<field> getAllFields() {
        List<field> fields = new ArrayList<>();

        String selectQuery = "SELECT * FROM field";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do{
                field field = new field(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3)
                );
                fields.add(field);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return fields;
    }
}
