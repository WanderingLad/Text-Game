package com.group.textgame.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.group.textgame.model.Character;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;

@Database(entities = {Character.class, Enemy.class, Player.class, Rooms.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();
}
