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

        for(int i = 1; i < 15; i++){
            Rooms room = new Rooms(i, roomNames[i - 1], initialText[i - 1], returnText[i - 1], lookText[i - 1], level.getID(), false);
            roomsRepo.addRoom(room);
        }

        //Room 1
        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));
        Rooms activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEastRoom(2);
        activeRoom.setEastText("You pull on the heavy wooden door handle... It’s unlocked! You open the door slowly, and step into the room to the East.");

        roomsRepo.updateRoom(activeRoom);

        Object object = new Object(1, "Pallet", activeRoom.getRoomName(), "The pallet is covered in straw and slightly damp. This must have been where you’ve been sleeping. You find nothing useful.");
        objectRepo.addObject(object);

        object = new Object(2, "Crates", activeRoom.getRoomName(), "The crates are crudely built, and are missing a few pieces of wood. You see some dishes and moldy bread. Is this what you’ve been eating? Hidden under one of the plates, you found a rusty knife.");
        objectRepo.addObject(object);

        object = new Object(3, "Rusty Knife", "Crates", "A crude knife. It should be better than your fists, you hope.");
        objectRepo.addObject(object);

        //Room 2
        roomsRepo.setActiveRoom(roomsRepo.getRoom(2));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setSouthRoom(3);
        activeRoom.setSouthText("You pull on the heavy wooden door handle… It’s unlocked! You open the door, and step into the room to the South. ");

        activeRoom.setEastText("You attempt to squeeze yourself through the bars, but to no avail. You are too big to fit, despite your diet of moldy bread.");

        activeRoom.setWestRoom(1);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        roomsRepo.updateRoom(activeRoom);

        object = new Object(4, "Bars", activeRoom.getRoomName(), "You can’t reach anything through the bars.");
        objectRepo.addObject(object);

        object = new Object(5, "Door", activeRoom.getRoomName(), "You stand on your toes, and peek through the doors window into the room to the South… There’s a person! Or at least … you think it’s a person. They are hunched over in the corner fiddling with something you can’t see. You think to yourself, “I should make sure I find something to defend myself before entering”");
        objectRepo.addObject(object);

        object = new Object(6, "Torch", activeRoom.getRoomName(), "The torch is very well secured to the wall by a metal bracket. You can’t free it.");
        objectRepo.addObject(object);

        //Room 3
        roomsRepo.setActiveRoom(roomsRepo.getRoom(3));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setNorthRoom(2);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        activeRoom.setSouthRoom(4);
        activeRoom.setSouthText("You open the door, and step into the room to the South.");

        Enemy enemy = new Enemy(1, "Short Creature", 3);

        enemy.setHealth(100);

        enemy.setDamage(100);

        characterRepo.addEnemy(enemy);

        object = new Object(15, "Refuse Pile", activeRoom.getRoomName(), "It's a pile of scraps. Nothing really useful here.");
        objectRepo.addObject(object);

        object = new Object(16, "Torch", activeRoom.getRoomName(), "The torch is very well secured to the wall by a metal bracket. You can’t free it.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 4
        roomsRepo.setActiveRoom(roomsRepo.getRoom(4));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setNorthRoom(3);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        activeRoom.setEastRoom(7);
        activeRoom.setEastText("You open the door, and step into the room to the East.");

        activeRoom.setWestRoom(5);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        object = new Object(17, "Line of Crates", activeRoom.getRoomName(), "You filter through all the crates. You find a couple pieces of moldy bread, and piece that looks almost edible. You decide to keep it.");
        objectRepo.addObject(object);

        object = new Object(18, "Torch", activeRoom.getRoomName(), "The torch is very well secured to the wall by a metal bracket. You can’t free it.");
        objectRepo.addObject(object);

        object = new Object(19, "Decent Looking Bread", "Line of Crates", "It looks edible. Maybe it'll help you out in a pinch.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 4a
        roomsRepo.setActiveRoom(roomsRepo.getRoom(5));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEastRoom(4);
        activeRoom.setEastText("You open the door, and step into the room to the East.");

        activeRoom.setSouthRoom(6);
        activeRoom.setSouthText("You open the door, and step into the room to the South.");

        object = new Object(20, "Body?", activeRoom.getRoomName(), "You think it's a body in a bag. But why would someone bother covering one up?");
        objectRepo.addObject(object);

        object = new Object(21, "Broken Pallets", activeRoom.getRoomName(), "Maybe these are connected to the body on the floor. But you're not sure. Best to just leave them be.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 4b
        roomsRepo.setActiveRoom(roomsRepo.getRoom(6));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setNorthRoom(5);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        object = new Object(7, "Old Small Chest", activeRoom.getRoomName(), "A small chest that is covered in dust. It looks like it's been untouched for a long time. I wonder what's inside.");
        objectRepo.addObject(object);

        object = new Object(8, "East Key", "Old Small Chest", "It's a small golden key. You don't think someone did a good job faking that it was gold, since the black underside is showing through.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 5
        roomsRepo.setActiveRoom(roomsRepo.getRoom(7));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setWestRoom(4);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        activeRoom.setEastRoom(8);
        activeRoom.setEastText("You open the door, and step into the room to the East.");

        activeRoom.setLockedBool(true);
        activeRoom.setKeyname("East Key");

        object = new Object(22, "Line of Torches", activeRoom.getRoomName(), "There's a ton of torches on both sides of the room. You really don't want to get too close to either side. You might burn yourself.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 6
        roomsRepo.setActiveRoom(roomsRepo.getRoom(8));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setWestRoom(7);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        activeRoom.setNorthRoom(9);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        enemy = new Enemy(2, "Hulking Creature", 8);

        enemy.setHealth(100);

        enemy.setDamage(12);

        characterRepo.addEnemy(enemy);

        object = new Object(23, "Broken Planks", activeRoom.getRoomName(), "Nails and splintered wood. That's all you find here. Better watch out for splinters.");
        objectRepo.addObject(object);

        object = new Object(24, "Wall Gashes", activeRoom.getRoomName(), "How did these even happen? It must've taken a huge swing to create gashes in stone walls.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 7
        roomsRepo.setActiveRoom(roomsRepo.getRoom(9));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setWestRoom(10);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        activeRoom.setSouthRoom(8);
        activeRoom.setSouthText("You open the door, and step into the room to the South.");

        activeRoom.setNorthRoom(11);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        object = new Object(9, "Small Brown Purse", activeRoom.getRoomName(), "A small brown purse lays in the rubble. It doesn't look like it's been there long enough to gather dust, the owner must've left in a rush.");
        objectRepo.addObject(object);

        object = new Object(10, "West Key", "Small Brown Purse", "The silver key shines back at you through the torchlight. You can almost see your reflection, not that you'd really want to.");
        objectRepo.addObject(object);

        object = new Object(25, "Workshop Tools", activeRoom.getRoomName(), "You think this might be useful to someone, but not to you. They look recently used, so maybe you weren't alone here.");
        objectRepo.addObject(object);

        object = new Object(26, "Wood Pile", activeRoom.getRoomName(), "If you ever took up carpentry, this wood be a great start. However, for now, it's just a foot note in here.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 7a
        roomsRepo.setActiveRoom(roomsRepo.getRoom(10));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEastRoom(9);
        activeRoom.setEastText("You open the door, and step into the room to the East.");

        activeRoom.setLockedBool(true);
        activeRoom.setKeyname("West Key");

        object = new Object(11, "Large Grey Chest", activeRoom.getRoomName(), "A large grey chest sits in the middle of the room. It's almost an ominous figure based on there being nothing else in here. Who would put it there?");
        objectRepo.addObject(object);

        object = new Object(12, "Short Sword", "Large Grey Chest", "A short sword? Why on earth would this be here? You question the validity of its existence, but at this point you really can't complain. It looks formidable enough.");
        objectRepo.addObject(object);

        object = new Object(27, "Sketchy Bread", "Large Grey Chest", "This loaf of bread isn't the best looking... But who are you to judge at this point?");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 8
        roomsRepo.setActiveRoom(roomsRepo.getRoom(11));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setWestRoom(12);
        activeRoom.setWestText("You open the door, and step into the room to the West.");

        activeRoom.setSouthRoom(9);
        activeRoom.setSouthText("You open the door, and step into the room to the South.");

        object = new Object(28, "Crates and Torch", activeRoom.getRoomName(), "You don't find anything useful. Though if you were desperate this might be a good place for a nap. You need to keep moving.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 9
        roomsRepo.setActiveRoom(roomsRepo.getRoom(12));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEastRoom(11);
        activeRoom.setEastText("You open the door, and step into the room to the East.");

        activeRoom.setNorthRoom(13);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        object = new Object(13, "Small Brown Shield", activeRoom.getRoomName(), "The shield of the knight lays mostly in tact. Outside of the obvious battlemarks it looks pretty well upkept.");
        objectRepo.addObject(object);

        object = new Object(14, "Shield", "Small Brown Shield", "Not huge, but it's more protection than you already have on. If it were to rain it wouldn't cover you that well. But then again, why is it raining in here?");
        objectRepo.addObject(object);

        object = new Object(29, "Suit of Armor", activeRoom.getRoomName(), "A couple years ago, this would've been a great museum piece. Now your only concern is to not end up like it. Left in the corner to rust.");
        objectRepo.addObject(object);

        roomsRepo.updateRoom(activeRoom);

        //Room 10
        roomsRepo.setActiveRoom(roomsRepo.getRoom(13));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setNorthRoom(14);
        activeRoom.setNorthText("You open the door, and step into the room to the North.");

        activeRoom.setSouthRoom(12);
        activeRoom.setSouthText("You open the door, and step into the room to the South.");

        enemy = new Enemy(3, "Emaciated  Creature", 13);

        enemy.setHealth(100);

        enemy.setDamage(14);

        characterRepo.addEnemy(enemy);

        roomsRepo.updateRoom(activeRoom);

        //Room End
        roomsRepo.setActiveRoom(roomsRepo.getRoom(14));

        activeRoom = roomsRepo.getActiveRoom();

        activeRoom.setEndRoom(true);

        roomsRepo.updateRoom(activeRoom);

        roomsRepo.setActiveRoom(roomsRepo.getRoom(1));
    }
}
