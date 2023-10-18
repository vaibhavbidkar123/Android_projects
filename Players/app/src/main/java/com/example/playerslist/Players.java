package com.example.playerslist;

public class Players {
    int playerImg;
    String playerName,role;

    public Players(int playerImg, String playerName, String role) {
        this.playerImg = playerImg;
        this.playerName = playerName;
        this.role = role;
    }

    public int getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(int playerImg) {
        this.playerImg = playerImg;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
