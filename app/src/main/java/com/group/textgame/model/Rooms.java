package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity (tableName = "Rooms")
public class Rooms {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long ID;

    @NonNull
    @ColumnInfo(name = "text")
    private String roomName;

    @ColumnInfo(name = "north")
    private long northRoom;

    @ColumnInfo(name = "south")
    private long southRoom;

    @ColumnInfo(name = "east")
    private long eastRoom;

    @ColumnInfo(name = "west")
    private long westRoom;

    @ColumnInfo(name = "enemy")
    private long enemy;

    public Rooms() {}
    public Rooms(@NonNull String text) {
        this.roomName = text;
    }

    @NonNull
    public void setID(long id) {
        this.ID = id;
    }

    public long getID() {
        return ID;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NonNull String roomName) {
        this.roomName = roomName;
    }

    public long getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(long northRoom) {
        this.northRoom = northRoom;
    }

    public long getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(long southRoom) {
        this.southRoom = southRoom;
    }

    public long getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(long eastRoom) {
        this.eastRoom = eastRoom;
    }

    public long getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(long westRoom) {
        this.westRoom = westRoom;
    }

    public long getEnemy() {
        return enemy;
    }

    public void setEnemy(long enemy) {
        this.enemy = enemy;
    }


}

