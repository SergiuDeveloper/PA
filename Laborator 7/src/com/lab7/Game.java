package com.lab7;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Board getBoard() {
        return this.board;
    }
    private void setBoard(Board board) {
        this.board = board;
    }

    private Player player1;
    private Player getPlayer1() {
        return this.player1;
    }
    private void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    private Player player2;
    private Player getPlayer2() {
        return this.player2;
    }
    private void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;

        this.player1.setBoard(this.board);
        this.player2.setBoard(this.board);
    }

    public void runGame() {
        this.player1.activateTurn();
        boolean player1Turn = this.player1.isTurnActive();

        Thread player1Thread = new Thread(this.player1);
        player1Thread.start();
        Thread player2Thread = new Thread(this.player2);
        player2Thread.start();

        Player winner;
        while (this.getWinner() == null && this.board.getAvailableTokensCount() > 0) {
            if (this.getWinner() != null) {
                winner = this.getWinner();

                if (winner == this.player1) {
                    System.out.println(this.player1.getName() + " won");
                    player1.deactivatePlayer();
                    player2.deactivatePlayer();
                    return;
                } else if (winner == this.player2) {
                    System.out.println(this.player2.getName() + " won");
                    player1.deactivatePlayer();
                    player2.deactivatePlayer();
                    return;
                }
            }

            if (player1Turn)
                this.player2.activateTurn();
            else
                this.player1.activateTurn();

            player1Turn = !player1Turn;
        }

        System.out.println("Both players lost");
        player1.deactivatePlayer();
        player2.deactivatePlayer();
    }

    private Player getWinner() {
        List<Integer> player1Tokens = new ArrayList<>();
        List<Integer> player2Tokens = new ArrayList<>();

        for (var token: this.board.getTokens())
            if (token != null) {
                if (token.getOwner() != null && token.getOwner() == this.player1)
                    player1Tokens.add(token.getValue());
                else if (token.getOwner() != null && token.getOwner() == this.player2)
                    player2Tokens.add(token.getValue());
            }

        boolean playerWon;
        if (player1Tokens.size() > 2) {
            playerWon = true;
            for (int i = 2; i < player1Tokens.size(); ++i)
                if (player1Tokens.get(i) - player1Tokens.get(i - 1) != player1Tokens.get(i - 1) - player1Tokens.get(i - 2)) {
                    playerWon = false;
                    break;
                }

            if (playerWon)
                return this.player1;
        }

        if (player2Tokens.size() > 2) {
            playerWon = true;
            for (int i = 2; i < player2Tokens.size(); ++i)
                if (player2Tokens.get(i) - player2Tokens.get(i - 1) != player2Tokens.get(i - 1) - player2Tokens.get(i - 2)) {
                    playerWon = false;
                    break;
                }

            if (playerWon)
                return this.player2;
        }

        return null;
    }
}
