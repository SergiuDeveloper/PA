package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {
    @GetMapping("/get")
    public List<Player> getPlayers() throws SQLException {
        try {
            return DatabaseHandler.getPlayers();
        }
        catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    @PostMapping("/add")
    public boolean addPlayer(@RequestParam int userID, @RequestParam String name) {
        try {
            DatabaseHandler.addPlayer(
                new Player(
                    userID,
                    name
                )
            );
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    @PutMapping("/modify/{userID}")
    public boolean modifyPlayer(@PathVariable int userID, @RequestParam String name) throws SQLException {
        try {
            DatabaseHandler.modifyPlayer(userID, name);
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }
    
    @DeleteMapping("/delete/{userID}")
    public boolean deletePlayer(@PathVariable int userID) {
        try {
            DatabaseHandler.deletePlayer(userID);
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }
}
