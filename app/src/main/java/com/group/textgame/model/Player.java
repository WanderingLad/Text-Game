package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "Characters")
public class Player extends Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long ID;

    @NonNull
    @ColumnInfo(name = "text")
    private String name;

    @NonNull
    public String getName(){
        return name;
    }

    public void setName(@NonNull String name){
        this.name = name;
    }

    public long getID(){
        return ID;
    }

    public void setID(long ID){
        this.ID = ID;
    }
}

