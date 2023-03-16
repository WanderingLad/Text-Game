package com.group.textgame.repo;

import androidx.room.*;
import com.group.textgame.model.Rooms;
import java.util.List;

@Dao
public interface RoomsDao {
    @Query("SELECT * FROM Rooms WHERE id = :id")
    Rooms getRooms(long id);

    @Query("SELECT * FROM Rooms ORDER BY name COLLATE NOCASE")
    List<Rooms> getRooms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addRoom(Rooms rooms);

    @Update
    void updateRoom(Rooms rooms);

    @Delete
    void deleteRooms(Rooms rooms);
}