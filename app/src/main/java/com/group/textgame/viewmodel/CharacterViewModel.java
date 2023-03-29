package com.group.textgame.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Query;

import com.group.textgame.model.Player;
import com.group.textgame.model.Enemy;
import com.group.textgame.repo.CharacterRepository;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {
    private final CharacterRepository characterRepo;

    public CharacterViewModel(Application application) {
        super(application);
        characterRepo = CharacterRepository.getInstance(application.getApplicationContext());
    }

    public Player getActivePlayer() {
        return characterRepo.getActivePlayer();
    }
}


