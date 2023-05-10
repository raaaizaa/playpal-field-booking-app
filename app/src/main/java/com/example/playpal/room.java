package com.example.playpal;

import java.util.List;

public class room {
    Integer roomId;
    String roomName;
    String categories;
    String location;
    List<String> players;

    public room(Integer roomId, String roomName, String categories, String location, List<String> players) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.categories = categories;
        this.location = location;
        this.players = players;
    }

    public Integer getRoomId() {
        return roomId;
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

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
