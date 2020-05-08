package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    public static List<Player> getPlayers() throws SQLException {
        Connection dbConnection = DatabaseHandler.getDBConnection();

        PreparedStatement getPlayersStatement = dbConnection.prepareStatement("SELECT UserID, Name FROM Players");
        ResultSet playersRows = getPlayersStatement.executeQuery();

        List<Player> playerList = new ArrayList<>();
        while (playersRows.next())
            playerList.add(
                new Player(
                    playersRows.getInt(1),
                    playersRows.getString(2)
                )
            );

        dbConnection.close();

        return playerList;
    }

    public static void addPlayer(Player player) throws SQLException {
        Connection dbConnection = DatabaseHandler.getDBConnection();

        PreparedStatement addPlayerStatement = dbConnection.prepareStatement("INSERT INTO Players(UserID, Name) VALUES(?, ?)");
        addPlayerStatement.setInt(1, player.getUserID());
        addPlayerStatement.setString(2, player.getName());
        addPlayerStatement.execute();

        dbConnection.close();
    }

    public static void modifyPlayer(int userID, String name) throws SQLException {
        Connection dbConnection = DatabaseHandler.getDBConnection();

        PreparedStatement modifyPlayerStatement = dbConnection.prepareStatement("UPDATE Players SET name = ? WHERE userID = ?");
        modifyPlayerStatement.setString(1, name);
        modifyPlayerStatement.setInt(2, userID);
        modifyPlayerStatement.execute();

        dbConnection.close();
    }

    public static void deletePlayer(int userID) throws SQLException {
        Connection dbConnection = DatabaseHandler.getDBConnection();

        PreparedStatement deletePlayerStatement = dbConnection.prepareStatement("DELETE FROM Players WHERE UserID = ?");
        deletePlayerStatement.setInt(1, userID);
        deletePlayerStatement.execute();

        dbConnection.close();
    }

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "STUDENT", "STUDENT");
    }
}
