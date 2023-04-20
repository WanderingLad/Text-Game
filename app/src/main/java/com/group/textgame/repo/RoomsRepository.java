package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Rooms;
import java.util.List;

public class RoomsRepository {

    private static RoomsRepository roomsRepo;
    private final RoomsDao roomsDao;

    private final RoomsDatabase database;

    private Rooms activeRoom;

    public static RoomsRepository getInstance(Context context) {
        if (roomsRepo == null) {
            roomsRepo = new RoomsRepository(context);
        }
        return roomsRepo;
    }

    private RoomsRepository(Context context) {
        database = Room.databaseBuilder(context, RoomsDatabase.class, "rooms.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

      roomsDao = database.roomsDao();
    }

    public void resetData(){
        database.clearAllTables();
        database.close();
    }

    public void setActiveRoom(Rooms room){
        this.activeRoom = room;
    }

    public Rooms getActiveRoom() {
        return this.activeRoom;
    }

    public void addRoom(Rooms room) {
        long roomId = roomsDao.addRoom(room);
        room.setID(roomId);
    }

    public Rooms getRoom(long roomId) {
        return roomsDao.getRoom(roomId);
    }

    public List<Rooms> getRooms() {
        return roomsDao.getRooms();
    }

    public List<Rooms> getRooms(long level) {
        return roomsDao.getRooms(level);
    }

    public void deleteRoom(Rooms room) {
        roomsDao.deleteRoom(room);
    }

    public void updateRoom(Rooms room) {
        roomsDao.updateRoom(room);
    }

    public void hasEntered(boolean entered){
        getActiveRoom().setEnteredBool(entered);
        updateRoom(getActiveRoom());
    }
}
