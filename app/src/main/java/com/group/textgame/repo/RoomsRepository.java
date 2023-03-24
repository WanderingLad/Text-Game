package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
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

        if (roomsDao.getRooms().isEmpty()) {
            addStarterData();
            setupGrid();
        }
    }

    private void addStarterData() {
        Rooms room = new Rooms("Room 1");
        Rooms room2 = new Rooms("Room 2");
        Rooms room3 = new Rooms("Room 3");
        Rooms room4 = new Rooms("Room 4");

        roomsDao.addRoom(room);
        roomsDao.addRoom(room2);
        roomsDao.addRoom(room3);
        roomsDao.addRoom(room4);
    }

    private void setupGrid() {
        activeRoom = roomsDao.getRoom(1);

        activeRoom.setNorthRoom(2);
        activeRoom.setEastRoom(4);

        roomsDao.updateRoom(activeRoom);

        activeRoom = roomsDao.getRoom(2);

        activeRoom.setSouthRoom(1);
        activeRoom.setEastRoom(3);

        roomsDao.updateRoom(activeRoom);

        activeRoom = roomsDao.getRoom(3);

        activeRoom.setWestRoom(2);
        activeRoom.setSouthRoom(4);

        roomsDao.updateRoom(activeRoom);

        activeRoom = roomsDao.getRoom(4);

        activeRoom.setNorthRoom(3);
        activeRoom.setWestRoom(1);

        roomsDao.updateRoom(activeRoom);
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

    public long getNorthRoom(long roomId) {
        return roomsDao.getNorthRoom(roomId);
    }

    public long getSouthRoom(long roomId) {
        return roomsDao.getSouthRoom(roomId);
    }

    public long getEastRoom(long roomId) {
        return roomsDao.getEastRoom(roomId);
    }

    public long getWestRoom(long roomId) {
        return roomsDao.getWestRoom(roomId);
    }
}
