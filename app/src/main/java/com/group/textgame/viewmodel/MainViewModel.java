package com.group.textgame.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.CharacterRepository;
import com.group.textgame.repo.LevelRepository;
import com.group.textgame.repo.RoomsRepository;
import com.group.textgame.domain.*;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final RoomsRepository roomsRepo;
    private final CharacterRepository characterRepo;

    private final LevelRepository levelRepo;
    private MutableLiveData<Rooms> _activeRoom;
    private MutableLiveData<Enemy> _activeEnemy;
    private MutableLiveData<Integer> _activePlayerHealth;
    private MutableLiveData<Integer> _activeEnemyHealth;

    public MainViewModel(Application application) {
        super(application);
        roomsRepo = RoomsRepository.getInstance(application.getApplicationContext());
        characterRepo = CharacterRepository.getInstance(application.getApplicationContext());
        levelRepo = LevelRepository.getInstance(application.getApplicationContext());

        if (roomsRepo.getRooms().isEmpty()) {
            SetupStarterData starterData = new SetupStarterData(characterRepo, roomsRepo, levelRepo);
        }

        _activeRoom = new MutableLiveData<Rooms>();

        _activePlayerHealth = new MutableLiveData<Integer>();

        _activeEnemyHealth = new MutableLiveData<Integer>();

        _activeEnemy = new MutableLiveData<>();

        _activeRoom.setValue(roomsRepo.getActiveRoom());

        _activePlayerHealth.setValue(characterRepo.getActivePlayer().getHealth());

        _activeEnemyHealth.setValue(getActiveRoomEnemy().getHealth());

        _activeEnemy.setValue(getActiveRoomEnemy());
    }

    public void setActiveRoom(Rooms room){
        _activeRoom.setValue(room);
    }

    public void setEnemyHealth(){
        _activeEnemyHealth.setValue(getActiveEnemy().getValue().getHealth());
        characterRepo.updateEnemy(getActiveEnemy().getValue());
    }

    public void setPlayerHealth(){
        _activePlayerHealth.setValue(_activePlayerHealth.getValue());
        characterRepo.updatePlayer(getActivePlayer());
    }

    public LiveData<Rooms> getActiveRoom(){
        return _activeRoom;
    }

    public LiveData<Integer> getPlayerHealth(){
        return _activePlayerHealth;
    }

    public LiveData<Integer> getEnemyHealth(){
        return _activeEnemyHealth;
    }

    public List<Rooms> getRooms() {
        return roomsRepo.getRooms();
    }

    public Rooms getRoom(long roomId) {
        return roomsRepo.getRoom(roomId);
    }

    public Player getActivePlayer() {
        return characterRepo.getActivePlayer();
    }

    public Enemy getActiveRoomEnemy() {
        return characterRepo.getEnemy(getActiveRoom().getValue().getEnemy());
    }

    public void setActiveEnemy(Enemy enemy) {
        _activeEnemy.setValue(enemy);
    }

    public LiveData<Enemy> getActiveEnemy() {
        return _activeEnemy;
    }

    public long getActiveLevel() {
        return _activeRoom.getValue().getLevel();
    }
}


