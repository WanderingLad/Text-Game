package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "Objects")
public class Object {

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getParentName() {
        return parentName;
    }

    public void setParentName(@NonNull String parentName) {
        this.parentName = parentName;
    }

    public String getObjectText() {
        return objectText;
    }

    public void setObjectText(String objectText) {
        this.objectText = objectText;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected long ID;

    @NonNull
    @ColumnInfo(name = "objectName")
    protected String name;

    @NonNull
    @ColumnInfo(name = "parentName")
    protected String parentName;

    @ColumnInfo(name = "objectText")
    protected String objectText;
}

