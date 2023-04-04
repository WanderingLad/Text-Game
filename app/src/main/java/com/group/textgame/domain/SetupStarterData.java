package com.group.textgame.domain;

import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Level;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.LevelRepository;
import com.group.textgame.repo.RoomsRepository;

public class SetupStarterData {

    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;
    private final LevelRepository levelRepo;

    public SetupStarterData(CharacterRepository characterRepo, RoomsRepository roomsRepo, LevelRepository levelRepo){
        this.characterRepo = characterRepo;
        this.roomsRepo = roomsRepo;
        this.levelRepo = levelRepo;

        setupGrid();
    }

    private void setupGrid() {
        Player player = new Player("player");

        player.setHealth(100);

        player.setDamage(10);

        characterRepo.addPlayer(player);

        characterRepo.setActivePlayer(player);

        //Room 1
        Rooms room = new Rooms("Room 1");

        roomsRepo.addRoom(room);
        roomsRepo.setActiveRoom(room);

        Enemy enemy = new Enemy("Enemy 1");

        enemy.setHealth(100);

        enemy.setDamage(10);

        characterRepo.addEnemy(enemy);

        room.setEnemy(enemy.getID());
        room.setNorthRoom(2);
        room.setEastRoom(4);

        Level level = new Level("Level 1");

        levelRepo.addLevel(level);

        room.setLevel(level.getID());

        roomsRepo.updateRoom(room);

        //Room 2
        Rooms room2 = new Rooms("Room 2");

        roomsRepo.addRoom(room2);

        Enemy enemy1 = new Enemy("Enemy 2");

        enemy1.setHealth(90);

        enemy1.setDamage(10);

        characterRepo.addEnemy(enemy1);

        room2.setEnemy(enemy1.getID());
        room2.setSouthRoom(1);
        room2.setEastRoom(3);

        room2.setLevel(level.getID());

        roomsRepo.updateRoom(room2);

        //Room 3
        Rooms room3 = new Rooms("Room 3");

        roomsRepo.addRoom(room3);

        Enemy enemy2 = new Enemy("Enemy 3");

        enemy2.setHealth(80);

        enemy2.setDamage(10);

        characterRepo.addEnemy(enemy2);

        room3.setEnemy(enemy2.getID());
        room3.setWestRoom(2);
        room3.setSouthRoom(4);

        room3.setLevel(level.getID());

        roomsRepo.updateRoom( room3);

        //Room 4
        Rooms room4 = new Rooms("Room 4");

        roomsRepo.addRoom(room4);

        Enemy enemy3 = new Enemy("Enemy 4");

        enemy3.setHealth(70);

        enemy3.setDamage(10);

        characterRepo.addEnemy(enemy3);

        room4.setEnemy(enemy3.getID());
        room4.setNorthRoom(3);
        room4.setWestRoom(1);

        room4.setLevel(level.getID());

        roomsRepo.updateRoom(room4);
    }
}
