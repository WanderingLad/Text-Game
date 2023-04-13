package com.group.textgame.domain;

import com.group.textgame.R;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Level;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.LevelRepository;
import com.group.textgame.repo.RoomsRepository;

public class LevelOne {

    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;
    private final LevelRepository levelRepo;

    private String[] roomNames, initialText, returnText;

    public LevelOne(CharacterRepository characterRepo, RoomsRepository roomsRepo, LevelRepository levelRepo, String[] roomNames, String[] initialText, String[] returnText){
        this.characterRepo = characterRepo;
        this.roomsRepo = roomsRepo;
        this.levelRepo = levelRepo;
        this.initialText = initialText;
        this.returnText = returnText;
        this.roomNames = roomNames;

        setupGrid();
    }

    private void setupGrid() {
        Player player = new Player("player");

        player.setHealth(100);

        player.setDamage(10);

        characterRepo.addPlayer(player);

        characterRepo.setActivePlayer(player);

        Level level = new Level("Level 1");

        levelRepo.addLevel(level);

        for(int i =0; i < 10; i++){
            Rooms room = new Rooms(roomNames[i], initialText[i], returnText[i]);
        }

        Rooms activeRoom = new Rooms;

        activeRoom.

//        //Room 1
//        Rooms room = new Rooms("Room 1");
//
//        roomsRepo.addRoom(room);
//        roomsRepo.setActiveRoom(room);
//
//        room.setNorthRoom(2);
//        room.setEastRoom(4);
//
//        room.setLevel(level.getID());
//
//        roomsRepo.updateRoom(room);
//
//        //Room 2
//        Rooms room2 = new Rooms("Room 2");
//
//        roomsRepo.addRoom(room2);
//
//        Enemy enemy1 = new Enemy("Enemy 2");
//
//        enemy1.setHealth(90);
//
//        enemy1.setDamage(10);
//
//        characterRepo.addEnemy(enemy1);
//
//        room2.setEnemy(enemy1.getID());
//        room2.setSouthRoom(1);
//        room2.setEastRoom(3);
//
//        room2.setLevel(level.getID());
//
//        roomsRepo.updateRoom(room2);
//
//        //Room 3
//        Rooms room3 = new Rooms("Room 3");
//
//        roomsRepo.addRoom(room3);
//
//        Enemy enemy2 = new Enemy("Enemy 3");
//
//        enemy2.setHealth(80);
//
//        enemy2.setDamage(10);
//
//        characterRepo.addEnemy(enemy2);
//
//        room3.setEnemy(enemy2.getID());
//        room3.setWestRoom(2);
//        room3.setSouthRoom(4);
//
//        room3.setLevel(level.getID());
//
//        roomsRepo.updateRoom( room3);
//
//        //Room 4
//        Rooms room4 = new Rooms("Room 4");
//
//        roomsRepo.addRoom(room4);
//
//        Enemy enemy3 = new Enemy("Enemy 4");
//
//        enemy3.setHealth(70);
//
//        enemy3.setDamage(10);
//
//        characterRepo.addEnemy(enemy3);
//
//        room4.setEnemy(enemy3.getID());
//        room4.setNorthRoom(3);
//        room4.setWestRoom(1);
//
//        room4.setLevel(level.getID());
//
//        roomsRepo.updateRoom(room4);
    }
}
