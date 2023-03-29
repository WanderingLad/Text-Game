package com.group.textgame.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.RoomsRepository;
import com.group.textgame.domain.*;

import java.util.List;

public class RoomsViewModel extends AndroidViewModel {
    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;

    private GetRooms getRooms = new GetRooms();

    public RoomsViewModel(Application application) {
        super(application);
        roomsRepo = RoomsRepository.getInstance(application.getApplicationContext());
        characterRepo = CharacterRepository.getInstance(application.getApplicationContext());
    }

    public List<Rooms> getRooms() {
        return roomsRepo.getRooms();
    }

    public List<Rooms> getRoomsCase() {
        return getRooms.getRooms(roomsRepo, characterRepo);
    }

    public Rooms getRoom(long roomId) {
        return roomsRepo.getRoom(roomId);
    }

    public void addRoom(Rooms room) {
        roomsRepo.addRoom(room);
    }

    public void deleteRoom(Rooms room) {
        roomsRepo.deleteRoom(room);
    }

    public long getNorthRoom(long roomId) {
        return roomsRepo.getNorthRoom(roomId);
    }

    public long getSouthRoom(long roomId) {
        return roomsRepo.getSouthRoom(roomId);
    }

    public long getEastRoom(long roomId) {
        return roomsRepo.getEastRoom(roomId);
    }

    public long getWestRoom(long roomId) {
        return roomsRepo.getWestRoom(roomId);
    }
}


