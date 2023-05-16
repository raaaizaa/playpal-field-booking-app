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
        boolean fieldExist = checkField(id, name, location);

        if(fieldExist){
            return false;
        }else{
            inputContent(id, name, location, picture);
            long results = db.insert("field", null, inputContent(id, name, location, picture));
            db.close();
            return results != -1;
        }


    }

    public boolean checkField(Integer id, String name, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM field WHERE field_id = ? AND field_name = ? OR field_location = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id), name, location});

        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }

    }

    public List<String> getFieldNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT field_name FROM field";
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<String> fieldNames = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                String fieldName = cursor.getString(cursor.getColumnIndex("field_name"));
                fieldNames.add(fieldName);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return fieldNames;
    }

    public List<field> getAllFields() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM field";
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<field> fields = new ArrayList<>();

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

    public ContentValues inputContent(Integer id, String name, String location, byte[] picture){
        ContentValues contentValues = new ContentValues();

        contentValues.put("field_id", id);
        contentValues.put("field_name", name);
        contentValues.put("field_location", location);
        contentValues.put("field_picture", picture);

        return contentValues;
    }
}
