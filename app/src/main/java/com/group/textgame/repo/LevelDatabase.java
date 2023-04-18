package com.group.textgame.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.group.textgame.model.Level;

@Database(entities = {Level.class}, version = 2)
public abstract class LevelDatabase extends RoomDatabase {

    public abstract LevelDao levelDao();
}
