package com.group.textgame.domain;

import android.util.Log;

import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.RoomsRepository;

import java.util.List;

public class GetRooms {

    private CharacterRepository characterRepo;
    private RoomsRepository roomsRepo;

    private List<Rooms> rooms;

    public List<Rooms> getRooms(RoomsRepository roomsRepo, CharacterRepository characterRepo) {
        this.characterRepo = characterRepo;
        this.roomsRepo = roomsRepo;

        rooms = roomsRepo.getRooms();

        return rooms;
    }
}
