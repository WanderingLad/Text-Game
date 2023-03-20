package com.group.textgame.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.group.textgame.model.Rooms;

@Database(entities = {Rooms.class}, version = 1)
public abstract class RoomsDatabase extends RoomDatabase {

    public abstract RoomsDao roomsDao();
}
