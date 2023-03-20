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
        }

        activeRoom = roomsDao.getRoom(1);

        activeRoom.setNorthRoom(2);
        activeRoom.setSouthRoom(3);
        activeRoom.setEastRoom(4);
        activeRoom.setWestRoom(5);

        roomsDao.updateRoom(activeRoom);
    }

    private void addStarterData() {
        Rooms room = new Rooms("Room");
        Rooms room2 = new Rooms("Room 2");
        Rooms room3 = new Rooms("Room 3");
        Rooms room4 = new Rooms("Room 4");
        Rooms room5 = new Rooms("Room 5");

        roomsDao.addRoom(room);
        roomsDao.addRoom(room2);
        roomsDao.addRoom(room3);
        roomsDao.addRoom(room4);
        roomsDao.addRoom(room5);
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

    public void deleteSubject(Rooms room) {
        roomsDao.deleteRoom(room);
    }
}
