package com.group.textgame.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.group.textgame.R;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.MainViewModel;

public class StartScreenFragment extends Fragment {

    private MainViewModel mainViewModel;

    private Rooms activeRoom;

    private Player activePlayer;

    private Enemy activeEnemy;

    private TextView changeableText, playerHealthText, enemyHealthText, enemyNameText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.start_screen, container, false);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Instantiate(parentView);

        return parentView;
    }

    private void Instantiate(View parentView) {

        setObserver();
        setInitialText(parentView);
        setButtons(parentView);
    }

    private void setObserver() {
        final Observer<Rooms> roomsObserver = new Observer<Rooms>() {
            @Override
            public void onChanged(Rooms rooms) {
                activeRoom = rooms;
                setText(activeRoom.getRoomName());
            }
        };

        final Observer<Integer> playerHealthObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer health) {
                setPlayerText(activePlayer.getHealth());
            }
        };

        final Observer<Enemy> enemyNameObserver = new Observer<Enemy>() {
            @Override
            public void onChanged(Enemy enemy) {
                activeEnemy = enemy;
                setEnemyNameText(activeEnemy.getName());
                setEnemyHealthText(activeEnemy.getHealth());
            }
        };

        final Observer<Integer> enemyHealthObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer health) {
                setEnemyHealthText(activeEnemy.getHealth());
            }
        };

        mainViewModel.getActiveRoom().observe(getViewLifecycleOwner(), roomsObserver);

        mainViewModel.getPlayerHealth().observe(getViewLifecycleOwner(), playerHealthObserver);

        mainViewModel.getEnemyHealth().observe(getViewLifecycleOwner(), enemyHealthObserver);

        mainViewModel.getActiveEnemy().observe(getViewLifecycleOwner(), enemyNameObserver);

        activePlayer = mainViewModel.getActivePlayer();

        activeRoom = mainViewModel.getActiveRoom().getValue();

        activeEnemy = mainViewModel.getActiveEnemy().getValue();
    }

    private void setInitialText(View parentView) {
        changeableText = parentView.findViewById(R.id.text_holder);
        playerHealthText = parentView.findViewById(R.id.player_holder);
        enemyHealthText = parentView.findViewById(R.id.enemy_holder);
        enemyNameText = parentView.findViewById(R.id.enemyname_holder);

        setPlayerText(activePlayer.getHealth());
        setEnemyHealthText(activeEnemy.getHealth());
        setEnemyNameText(activeEnemy.getName());
        setText(activeRoom.getRoomName());
    }

    private void setButtons(View parentView){
        Button north = (Button) parentView.findViewById(R.id.north_button);

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNorth(view);
            }
        });

        Button south = (Button) parentView.findViewById(R.id.south_button);

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSouth(view);
            }
        });

        Button east = (Button) parentView.findViewById(R.id.east_button);

        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEast(view);
            }
        });

        Button west = (Button) parentView.findViewById(R.id.west_button);

        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWest(view);
            }
        });

        Button player = (Button) parentView.findViewById(R.id.player_button);

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackEnemy(view);
            }
        });

        Button enemy = (Button) parentView.findViewById(R.id.enemy_button);

        enemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackPlayer(view);
            }
        });
    }

    private void checkNorth(View view){
        if(activeRoom.getNorthRoom() != 0 && activeEnemy.getHealth() <= 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkSouth(View view){
        if(activeRoom.getSouthRoom() != 0 && activeEnemy.getHealth() <= 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkEast(View view){
        if(activeRoom.getEastRoom() != 0 && activeEnemy.getHealth() <= 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkWest(View view){
        if(activeRoom.getWestRoom() != 0 && activeEnemy.getHealth() <= 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getWestRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void attackEnemy(View view){
        if(activeEnemy.getHealth() >= activePlayer.getDamage()){
            activePlayer.attackTarget(activeEnemy);
            mainViewModel.setEnemyHealth();
        }
    }

    public void attackPlayer(View view){
        if(activePlayer.getHealth() >= activeEnemy.getDamage()){
            activeEnemy.attackTarget(activePlayer);
            mainViewModel.setPlayerHealth();
        }
    }

    private void setText(String text) {
        changeableText.setText(text);
    }

    private void setPlayerText(int text) {
        playerHealthText.setText(String.valueOf(text));
    }

    private void setEnemyHealthText(int text) {
        enemyHealthText.setText(String.valueOf(text));
    }

    private void setEnemyNameText(String text) {
        enemyNameText.setText(text);
    }
}