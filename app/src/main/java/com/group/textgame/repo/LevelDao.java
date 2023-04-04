package com.group.textgame.repo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.group.textgame.model.Level;

import java.util.List;

@Dao
public interface LevelDao {

    @Query("SELECT * FROM Levels WHERE id = :id")
    Level getLevel(long id);

    @Query("SELECT * FROM Levels ORDER BY id COLLATE NOCASE")
    List<Level> getLevels();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addLevel(Level room);

    @Update
    void updateLevel(Level room);

    @Delete
    void deleteLevel(Level room);
}
