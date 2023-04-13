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

    public abstract long getID();

    public abstract void setID(long ID);

    @NonNull
    @ColumnInfo(name = "characterName")
    protected String name;

    @NonNull
    public abstract String getName();

    public abstract void setName(@NonNull String name);

    @ColumnInfo(name = "health")
    protected int health;

    public abstract int getHealth();

    public abstract void setHealth(int health);

    @ColumnInfo(name = "damange")
    protected int damage;

    public abstract int getDamage();

    public abstract void setDamage(int damage);

    public abstract void attackTarget(Player player);

    public abstract void attackTarget(Enemy enemy);
}

