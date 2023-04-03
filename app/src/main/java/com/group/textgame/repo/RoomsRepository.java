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

    private Rooms activeRoom;

    public static RoomsRepository getInstance(Context context) {
        if (roomsRepo == null) {
            roomsRepo = new RoomsRepository(context);
        }
        return roomsRepo;
    }

    private RoomsRepository(Context context) {
      RoomsDatabase database = Room.databaseBuilder(context, RoomsDatabase.class, "rooms.db")
                .allowMainThreadQueries()
                .build();

        roomsDao = database.roomsDao();


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

    public void deleteRoom(Rooms room) {
        roomsDao.deleteRoom(room);
    }

    public long getEnemy(long roomId) {
        return roomsDao.getEnemy(roomId);
    }

    public void updateRoom(Rooms room) {
        roomsDao.updateRoom(room);
    }
}
