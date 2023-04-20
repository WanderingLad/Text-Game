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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addRoom(Rooms room);

    @Update
    void updateRoom(Rooms room);

    @Delete
    void deleteRoom(Rooms room);
}