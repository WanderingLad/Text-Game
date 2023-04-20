package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Player extends Character {

    public Player(@NonNull long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    @NonNull
    public String getName(){
        return this.name;
    }

    public void setName(@NonNull String name){
        this.name = name;
    }

    public long getID(){
        return this.ID;
    }

    public void setID(long ID){
        this.ID = ID;
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

    @Override
    public void attackTarget(Player player, int bonus) {}

    @Override
    public void attackTarget(Enemy enemy, int bonus) {
        enemy.setHealth(enemy.getHealth() - (getDamage() + bonus));
    }
}

