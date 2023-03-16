package com.group.textgame;

import java.util.HashSet;
import java.util.Set;

public class RoomController {

    private Rooms activeRoom;

    public Set<Rooms> roomList = new HashSet<Rooms>();

    private Rooms room1 = new Rooms();
    private Rooms room2 = new Rooms();
    private Rooms room3 = new Rooms();
    private Rooms room4 = new Rooms();

    public RoomController() {
        this.activeRoom = null;
    }

    public void setActiveRoom(Rooms room) {
        this.activeRoom = room;
    }

    public Rooms getActiveRoom(){
        return this.activeRoom;
    }


}
