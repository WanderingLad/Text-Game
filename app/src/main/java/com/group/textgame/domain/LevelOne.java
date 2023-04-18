package com.group.textgame.domain;

import com.group.textgame.R;

import android.content.Context;
import android.content.res.Resources;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Level;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.model.Object;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.LevelRepository;
import com.group.textgame.repo.ObjectRepository;
import com.group.textgame.repo.RoomsRepository;

public class LevelOne {

    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;
    private final LevelRepository levelRepo;
    private final ObjectRepository objectRepo;
    private String[] roomNames, initialText, returnText, lookText;

    public LevelOne(Context context){
        roomsRepo = RoomsRepository.getInstance(context.getApplicationContext());
        characterRepo = CharacterRepository.getInstance(context.getApplicationContext());
        levelRepo = LevelRepository.getInstance(context.getApplicationContext());
        objectRepo = ObjectRepository.getInstance(context.getApplicationContext());

        this.initialText = context.getResources().getStringArray(R.array.initial_text);
        this.returnText = context.getResources().getStringArray(R.array.return_text);
        this.roomNames = context.getResources().getStringArray(R.array.room_names);
        this.lookText = context.getResources().getStringArray(R.array.look_text);

        setupGrid();
    }

    private void setupGrid() {
        Player player = new Player(1, "player");

        player.setHealth(100);

        player.setDamage(10);

        characterRepo.addPlayer(player);

        characterRepo.setActivePlayer(player);

        Level level = new Level(1, "Level 1");

        levelRepo.addLevel(level);

        for(int i = 1; i < 11; i++){
            Rooms room = new Rooms(i, roomNames[i - 1], initialText[i - 1], returnText[i - 1], lookText[i - 1], level.getID(), false);
            roomsRepo.addRoom(room);
        }

        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));
        Rooms activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEastRoom(2);
        activeRoom.setEastText("You pull on the heavy wooden door handle... It’s unlocked! You open the door slowly, and step into the room to the East.");

        roomsRepo.updateRoom(activeRoom);

        Object object = new Object(1, "Pallet", activeRoom.getRoomName(), "The pallet is covered in straw and slightly damp. This must have been where you’ve been sleeping. You find nothing useful.");
        objectRepo.addObject(object);

        object = new Object(2, "Crates", activeRoom.getRoomName(), "The crates are crudely built, and are missing a few pieces of wood. You see some dishes and moldy bread. Is this what you’ve been eating? Hidden under one of the plates, you find a rusty knife.");
        objectRepo.addObject(object);

        roomsRepo.setActiveRoom(roomsRepo.getRoom(2));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setSouthRoom(3);
        activeRoom.setSouthText("You pull on the heavy wooden door handle… It’s unlocked! You open the door, and step into the room to the South. ");

        activeRoom.setWestRoom(1);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        roomsRepo.updateRoom(activeRoom);

        object = new Object(4, "Bars", activeRoom.getRoomName(), "You can’t reach anything through the bars.");
        objectRepo.addObject(object);

        object = new Object(5, "Door", activeRoom.getRoomName(), "You stand on your toes, and peek through the doors window into the room to the South… There’s a person! Or at least … you think it’s a person. They are hunched over in the corner fiddling with something you can’t see. You think to yourself, “I should make sure I find something to defend myself before entering”");
        objectRepo.addObject(object);

        object = new Object(6, "Torch", activeRoom.getRoomName(), "The torch is very well secured to the wall by a metal bracket. You can’t free it.");
        objectRepo.addObject(object);

        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));

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
