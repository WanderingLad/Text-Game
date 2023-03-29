package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.group.textgame.data.Armor;
import com.group.textgame.data.MainHand;

@Entity (tableName = "Characters")
public abstract class Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected long ID;

    @NonNull
    @ColumnInfo(name = "characterName")
    protected String name;

    @ColumnInfo(name = "weapon")
    protected MainHand weapon;

    @NonNull
    @ColumnInfo(name = "armor")
    protected Armor armor;

    @NonNull
    public abstract String getName();

    public abstract void setName(@NonNull String name);

    public abstract long getID();

    public abstract void setID(long ID);

    @NonNull
    public abstract Armor getArmor();

    public abstract void setArmor(@NonNull Armor armor);

    @NonNull
    public abstract MainHand getWeapon();

    public abstract void setWeapon(MainHand weapon);
}

