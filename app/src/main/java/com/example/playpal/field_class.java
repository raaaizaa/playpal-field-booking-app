package com.example.playpal;

import java.util.ArrayList;

public class field_class {

    String fieldName;
    String fieldLocation;
    Integer fieldPicture;

    ArrayList<room_class> rooms;

    public field_class(String fieldName, String fieldLocation, Integer fieldPicture) {
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.fieldPicture = fieldPicture;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public void setFieldLocation(String fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public Integer getFieldPicture() {
        return fieldPicture;
    }

    public void setFieldPicture(Integer fieldPicture) {
        this.fieldPicture = fieldPicture;
    }

    public void addRoom(room_class room_class){
        rooms.add(room_class);
    }
}
