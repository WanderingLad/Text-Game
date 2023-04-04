package com.group.textgame.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.*;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Rooms;
import java.util.List;

@Dao
public interface RoomsDao {
    @Query("SELECT * FROM Rooms WHERE id = :id")
    Rooms getRoom(long id);

    @Query("SELECT * FROM Rooms ORDER BY id COLLATE NOCASE")
    List<Rooms> getRooms();

    @Query("SELECT * FROM Rooms WHERE level = :level ORDER BY id COLLATE NOCASE")
    List<Rooms> getRooms(long level);

    @Query("SELECT north FROM Rooms WHERE id = :id")
    long getNorthRoom(long id);

    @Query("SELECT south FROM Rooms WHERE id = :id")
    long getSouthRoom(long id);

    @Query("SELECT east FROM Rooms WHERE id = :id")
    long getEastRoom(long id);

    @Query("SELECT west FROM Rooms WHERE id = :id")
    long getWestRoom(long id);

    @Query("SELECT enemy FROM Rooms WHERE id = :id")
    long getEnemy(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addRoom(Rooms room);

    @Update
    void updateRoom(Rooms room);

    @Delete
    void deleteRoom(Rooms room);
}