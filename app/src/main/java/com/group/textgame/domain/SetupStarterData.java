package com.group.textgame.domain;

import android.util.Log;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.RoomsRepository;

public class SetupStarterData {

    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;

    public SetupStarterData(CharacterRepository characterRepo, RoomsRepository roomsRepo){
        this.characterRepo = characterRepo;
        this.roomsRepo = roomsRepo;

        addStarterData();
        setupGrid();
    }

    private void addStarterData() {
        Rooms room = new Rooms("Room 1");
        Rooms room2 = new Rooms("Room 2");
        Rooms room3 = new Rooms("Room 3");
        Rooms room4 = new Rooms("Room 4");

        roomsRepo.addRoom(room);
        roomsRepo.addRoom(room2);
        roomsRepo.addRoom(room3);
        roomsRepo.addRoom(room4);

        Player player = new Player("player");

        player.setHealth(100);

        player.setDamage(10);

        characterRepo.addPlayer(player);

        characterRepo.setActivePlayer(player);
    }

    private void setupGrid() {
        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));

        Enemy enemy = new Enemy("Enemy 1");

        enemy.setHealth(100);

        enemy.setDamage(10);

        characterRepo.addEnemy(enemy);

        roomsRepo.getActiveRoom().setEnemy(enemy.getID());
        roomsRepo.getActiveRoom().setNorthRoom(2);
        roomsRepo.getActiveRoom().setEastRoom(4);

        roomsRepo.updateRoom(roomsRepo.getActiveRoom());

        roomsRepo.setActiveRoom(roomsRepo.getRoom(2));

        Enemy enemy1 = new Enemy("Enemy 2");

        enemy1.setHealth(90);

        enemy1.setDamage(10);

        characterRepo.addEnemy(enemy1);

        roomsRepo.getActiveRoom().setEnemy(enemy1.getID());
        roomsRepo.getActiveRoom().setSouthRoom(1);
        roomsRepo.getActiveRoom().setEastRoom(3);

        roomsRepo.updateRoom( roomsRepo.getActiveRoom());

        roomsRepo.setActiveRoom(roomsRepo.getRoom(3));

        Enemy enemy2 = new Enemy("Enemy 3");

        enemy2.setHealth(80);

        enemy2.setDamage(10);

        characterRepo.addEnemy(enemy2);

        roomsRepo.getActiveRoom().setEnemy(enemy2.getID());
        roomsRepo.getActiveRoom().setWestRoom(2);
        roomsRepo.getActiveRoom().setSouthRoom(4);

        roomsRepo.updateRoom( roomsRepo.getActiveRoom());

        roomsRepo.setActiveRoom(roomsRepo.getRoom(4));

        Enemy enemy3 = new Enemy("Enemy 4");

        enemy3.setHealth(70);

        enemy3.setDamage(10);

        characterRepo.addEnemy(enemy3);

        roomsRepo.getActiveRoom().setEnemy(enemy3.getID());
        roomsRepo.getActiveRoom().setNorthRoom(3);
        roomsRepo.getActiveRoom().setWestRoom(1);

        roomsRepo.updateRoom(roomsRepo.getActiveRoom());

        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));
    }
}
