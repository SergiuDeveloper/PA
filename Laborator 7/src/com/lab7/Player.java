package com.lab7;

import java.util.Random;

public class Player implements Runnable {
    private String name;
    public String getName() {
        return this.name;
    }
    private void setName(String name) {
        this.name = name;
    }

    private Board board;
    private Board getBoard() {
        return this.board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    private boolean activePlayer;
    private boolean isActivePlayer() {
        return this.activePlayer;
    }
    private void setActivePlayer(boolean activePlayer) {
        this.activePlayer = activePlayer;
    }

    private boolean turnActive;
    public boolean isTurnActive() {
        return this.turnActive;
    }
    private void setTurnActive(boolean turnActive) {
        this.turnActive = turnActive;
    }

    private int n;
    private int getN() {
        return this.n;
    }
    private void setN(int n) {
        this.n = n;
    }

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.activePlayer = false;
        this.turnActive = false;
    }

    public boolean activatePlayer(int n) {
        if (this.activePlayer)
            return false;
        this.n = n;
        this.activePlayer = true;
        return true;
    }

    public boolean deactivatePlayer() {
        if (!this.activePlayer)
            return false;
        this.activePlayer = false;
        return true;
    }

    public boolean activateTurn() {
        if (this.turnActive)
            return false;
        this.turnActive = true;
        return true;
    }

    private boolean deactivateTurn() {
        if (!this.turnActive)
            return false;
        this.turnActive = false;
        return true;
    }

    private void awaitTurn() {
        while (!this.turnActive);
    }

    private Token getRandomToken() {
        Random random = new Random();
        int tokenIndex = random.nextInt(this.board.getAvailableTokensCount()) + 1;
        Token[] tokens = this.board.getTokens();
        Token chosenToken = null;
        for (int i = 1; i < tokens.length; ++i)
            if (tokens[i].getOwner() != null) {
                --tokenIndex;
                if (tokenIndex <= 0) {
                    chosenToken = tokens[i];
                    this.board.assignTokenOwner(this, i);
                    break;
                }
            }
        return chosenToken;
    }

    @Override
    public void run() {
        Token randomToken;
        while (this.activePlayer) {
            this.awaitTurn();

            randomToken = this.getRandomToken();

            this.deactivateTurn();
        }
    }
}
