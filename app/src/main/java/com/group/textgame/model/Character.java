package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "Characters")
public abstract class Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected long ID;

    @NonNull
    @ColumnInfo(name = "text")
    protected String name;
    @NonNull
    public abstract String getName();

    public abstract void setName(@NonNull String name);

    public abstract long getID();

    public abstract void setID(long ID);
}

