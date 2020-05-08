package com.example.demo;

public class Player {
    private int userID;
    public int getUserID() {
        return this.userID;
    }
    private void setUserID(int userID) {
        this.userID = userID;
    }

    private String name;
    public String getName() {
        return this.name;
    }
    private void setName(String name) {
        this.name = name;
    }

    public Player(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }
}
