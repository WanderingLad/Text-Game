package com.group.textgame.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Levels")
public class Level {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private long ID;

    @NonNull
    @ColumnInfo(name = "text")
    private String levelName;

    public Level() {}

    public Level(@NonNull long ID, @NonNull String text) {
        this.ID = ID;
        this.levelName = text;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @NonNull
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(@NonNull String levelName) {
        this.levelName = levelName;
    }
}
