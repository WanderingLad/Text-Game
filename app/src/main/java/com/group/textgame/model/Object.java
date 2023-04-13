package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "Objects")
public abstract class Object {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected long ID;

    @NonNull
    @ColumnInfo(name = "characterName")
    protected String name;
}

