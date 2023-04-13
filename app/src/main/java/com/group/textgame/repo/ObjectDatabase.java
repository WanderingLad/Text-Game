package com.group.textgame.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.group.textgame.model.Object;
import com.group.textgame.model.Rooms;

@Database(entities = {Object.class}, version = 1)
public abstract class ObjectDatabase extends RoomDatabase {

    public abstract ObjectDao objectDao();
}
