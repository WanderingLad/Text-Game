package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "Rooms")
public class Rooms {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long ID;

    @NonNull
    @ColumnInfo(name = "text")
    private String roomName;

    @NonNull
    @ColumnInfo(name = "initial")
    private String initialText;

    @NonNull
    @ColumnInfo(name = "return")
    private String returnText;

    @ColumnInfo(name = "north")
    private long northRoom;

    @ColumnInfo(name = "northText")
    private String northText;

    @ColumnInfo(name = "south")
    private long southRoom;

    @ColumnInfo(name = "southText")
    private String southText;

    @ColumnInfo(name = "east")
    private long eastRoom;

    @ColumnInfo(name = "eastText")
    private String eastText;

    @ColumnInfo(name = "west")
    private long westRoom;

    @ColumnInfo(name = "westText")
    private String westText;

    @ColumnInfo(name = "enemy")
    private long enemy;

    @ColumnInfo(name = "level")
    private long level;

    public Rooms() {}
    public Rooms(@NonNull String text, @NonNull String initialText, @NonNull String returnText) {
        this.roomName = text;
        this.initialText = initialText;
        this.returnText = returnText;
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

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    @NonNull
    public String getInitialText() {
        return initialText;
    }

    public void setInitialText(@NonNull String initialText) {
        this.initialText = initialText;
    }

    @NonNull
    public String getReturnText() {
        return returnText;
    }

    public void setReturnText(@NonNull String returnText) {
        this.returnText = returnText;
    }

    public String getNorthText() {
        return northText;
    }

    public void setNorthText(String northText) {
        this.northText = northText;
    }

    public String getSouthText() {
        return southText;
    }

    public void setSouthText(String southText) {
        this.southText = southText;
    }

    public String getEastText() {
        return eastText;
    }

    public void setEastText(String eastText) {
        this.eastText = eastText;
    }

    public String getWestText() {
        return westText;
    }

    public void setWestText(String westText) {
        this.westText = westText;
    }
}

