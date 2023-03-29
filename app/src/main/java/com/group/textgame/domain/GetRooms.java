package com.group.textgame.domain;

import android.util.Log;

import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.RoomsRepository;

public class GetRooms {

    private final CharacterRepository characterRepo;
    private final RoomsRepository roomsRepo;

    public GetRooms(CharacterRepository characterRepo, RoomsRepository roomsRepo) {
        this.characterRepo = characterRepo;
        this.roomsRepo = roomsRepo;
    }
}
