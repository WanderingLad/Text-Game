package com.group.textgame.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.group.textgame.model.Character;

@Database(entities = {Character.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();
}
