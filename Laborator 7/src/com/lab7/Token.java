package com.lab7;

public class Token {
    public static final int BLANK_TOKEN = 0;

    private int value;
    public int getValue() {
        return this.value;
    }
    private void setValue(int value) {
        this.value = value;
    }

    private Player owner;
    public Player getOwner() {
        return this.owner;
    }
    private void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean assignOwner(Player owner) {
        if (this.owner != null)
            return false;
        this.owner = owner;
        return true;
    }

    public Token(int value) {
        this.value = value;
        this.owner = null;
    }
}