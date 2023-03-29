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
        CharacterDatabase database = Room.databaseBuilder(context, CharacterDatabase.class, "characters.db")
                .allowMainThreadQueries()
                .build();

        characterDao = database.characterDao();

        Player player = new Player("player");

        characterDao.addPlayer(player);

        Log.d("test", String.valueOf(characterDao.getPlayer(1)));

//        if (characterDao.getCharacters().isEmpty()) {
//            addStarterData();
//        }
    }

    private void addStarterData() {
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
}
