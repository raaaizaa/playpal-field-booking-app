package com.example.playpal;

import java.util.List;

public class room {
    Integer roomId;
    Integer fieldId;
    String roomName;
    String categories;
    String location;

    public room(Integer roomId, Integer fieldId, String roomName, String categories, String location) {
        this.roomId = roomId;
        this.fieldId = fieldId;
        this.roomName = roomName;
        this.categories = categories;
        this.location = location;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

