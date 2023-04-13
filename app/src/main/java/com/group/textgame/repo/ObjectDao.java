package com.group.textgame.repo;

import androidx.room.Dao;
import androidx.room.*;

import com.group.textgame.model.Level;
import com.group.textgame.model.Object;
import com.group.textgame.model.Player;
import com.group.textgame.model.Enemy;

import java.util.List;

@Dao
public interface ObjectDao {

    @Query("SELECT *  FROM Objects WHERE id = :id" )
    Object getObject(long id);

    @Query("SELECT * FROM Objects WHERE parentName = :parentName ORDER BY id COLLATE NOCASE")
    List<Object> getObjects(String parentName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addObject(Object object);

    @Update
    void updateObject(Object object);

    @Delete
    void deleteObject(Object object);
}