package com.example.playpal.models;

public class player {

    private int playerId;
    private int roomId;
    private String name;

    public player(int playerId, int roomId, String playerName) {
        this.playerId = playerId;
        this.roomId = roomId;
        this.name = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
