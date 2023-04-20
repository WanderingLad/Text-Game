package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;

import java.util.List;

public class CharacterRepository {

    private static CharacterRepository characterRepo;

    private final CharacterDao characterDao;

    private final CharacterDatabase database;

    private Player activePlayer;

    public static CharacterRepository getInstance(Context context) {
        if (characterRepo == null) {
            characterRepo = new CharacterRepository(context);
        }
        return characterRepo;
    }

    private CharacterRepository(Context context) {
        database = Room.databaseBuilder(context, CharacterDatabase.class, "characters.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        characterDao = database.characterDao();
    }

    public void resetData(){
        database.clearAllTables();
        database.close();
    }

    public void addPlayer(Player player) {
        long id = characterDao.addPlayer(player);

        player.setID(id);
    }

    public void addEnemy(Enemy enemy) {
        long id = characterDao.addEnemy(enemy);

        enemy.setID(id);
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void updatePlayer(Player player) {
        characterDao.updatePlayer(player);
    }

    public Enemy getEnemy(long roomId) {
        return characterDao.getEnemy(roomId);
    }

    public Enemy getRoomEnemy(long roomId) {
        return characterDao.getRoomEnemy(roomId);
    }

    public void updateEnemy(Enemy enemy) {
        characterDao.updateEnemy(enemy);
    }
}
