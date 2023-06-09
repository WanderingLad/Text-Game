package com.group.textgame.repo;

import androidx.room.Dao;
import androidx.room.*;
import com.group.textgame.model.Player;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Rooms;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT *  FROM Player WHERE id = :id" )
    Player getPlayer(long id);

    @Query("SELECT * FROM Enemy WHERE id = :id")
    Enemy getEnemy(long id);

    @Query("SELECT * FROM Enemy WHERE roomID = :roomID ORDER BY id COLLATE NOCASE")
    Enemy getRoomEnemy(long roomID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addPlayer(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addEnemy(Enemy enemy);

    @Update
    void updatePlayer(Player player);

    @Update
    void updateEnemy(Enemy enemy);

    @Delete
    void deletePlayer(Player player);

    @Delete
    void deleteEnemy(Enemy enemy);
}