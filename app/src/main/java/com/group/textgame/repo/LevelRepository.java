package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Level;
import com.group.textgame.model.Rooms;

import java.util.List;

public class LevelRepository {

    private static LevelRepository levelRepo;
    private final LevelDao levelDao;
    private final LevelDatabase database;

    private Level activeLevel;

    public static LevelRepository getInstance(Context context) {
        if (levelRepo == null) {
            levelRepo = new LevelRepository(context);
        }
        return levelRepo;
    }

    private LevelRepository(Context context) {
        database = Room.databaseBuilder(context, LevelDatabase.class, "levels.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        levelDao = database.levelDao();
    }

    public void resetData(){
        database.clearAllTables();
        database.close();
    }

    public void addLevel(Level level) {
        long levelId = levelDao.addLevel(level);
        level.setID(levelId);
    }

    public Level getLevel(long levelID) {
        return levelDao.getLevel(levelID);
    }
}
