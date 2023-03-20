package com.group.textgame.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.RoomsRepository;

import java.util.List;

public class RoomsViewModel extends AndroidViewModel {
    private final RoomsRepository roomsRepo;

    public RoomsViewModel(Application application) {
        super(application);
        roomsRepo = RoomsRepository.getInstance(application.getApplicationContext());
    }

    public List<Rooms> getRooms() {
        return roomsRepo.getRooms();
    }

    public void addRoom(Rooms room) {
        roomsRepo.addRoom(room);
    }
}


