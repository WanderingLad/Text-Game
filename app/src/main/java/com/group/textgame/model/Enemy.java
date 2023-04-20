package com.group.textgame.model;

import static androidx.room.ForeignKey.CASCADE;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Enemy extends Character {

    @NonNull
    @ColumnInfo(name = "roomID")
    protected long roomID;

    public Enemy(@NonNull long ID, String name, long roomID) {
        this.ID = ID;
        this.name = name;
        this.roomID = roomID;
    }

    @NonNull
    public String getName(){
        return this.name;
    }

    public void setName(@NonNull String name){
        this.name = name;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getID(){
        return this.ID;
    }

    public void setID(long ID){
        this.ID = ID;
    }

    @Override
    public void attackTarget(Player player, int bonus) {
        player.setHealth(player.getHealth() - (getDamage() + bonus));
    }

    @Override
    public void attackTarget(Enemy enemy, int bonus) {}

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }
}

