package com.group.textgame.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.group.textgame.data.Armor;
import com.group.textgame.data.MainHand;

@Entity
public class Player extends Character {

    public Player(String name) {
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

//    @NonNull
    public MainHand getWeapon() {
        return weapon;
    }

    public void setWeapon(@NonNull MainHand weapon) {
        this.weapon = weapon;
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
    public void attackTarget(Player player) {}

    @Override
    public void attackTarget(Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - getDamage());
    }

    //    @NonNull
    public Armor getArmor() {
        return armor;
    }

    public void setArmor(@NonNull Armor armor) {
        this.armor = armor;
    }
}

