package com.example.playpal;

public class room_class {
    String roomName;
    String categories;
    String location;
    Integer player;

    public room_class(String roomName, String categories, String location, Integer player) {
        this.roomName = roomName;
        this.categories = categories;
        this.location = location;
        this.player = player;
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

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }
}
