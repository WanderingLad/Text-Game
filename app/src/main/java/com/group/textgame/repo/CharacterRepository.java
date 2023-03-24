package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Player;
import java.util.List;

public class CharacterRepository {

    private static CharacterRepository characterRepo;

    private final CharacterDao characterDao;

    private Player activePlayer;

    public static CharacterRepository getInstance(Context context) {
        if (characterRepo == null) {
            characterRepo = new CharacterRepository(context);
        }
        return characterRepo;
    }

    private CharacterRepository(Context context) {
        CharacterDatabase database = Room.databaseBuilder(context, CharacterDatabase.class, "character.db")
                .allowMainThreadQueries()
                .build();

        characterDao = database.characterDao();

//        if (characterDao.getRooms().isEmpty()) {
//            addStarterData();
//        }
    }

    private void addStarterData() {
    }
}
