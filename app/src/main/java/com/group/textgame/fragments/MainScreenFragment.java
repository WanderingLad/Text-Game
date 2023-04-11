package com.group.textgame.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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

public class MainScreenFragment extends Fragment {

    private MainViewModel mainViewModel;

    private Rooms activeRoom;

    private long activeLevelNumber;

    private Player activePlayer;

    private Enemy activeEnemy;

    private TextView enemyHealthText, enemyNameText, roomInfo, textBox, actionText;

    private ProgressBar playerHealth;

    private Button actionButton, startButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.main_screen, container, false);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Instantiate(parentView);

        return parentView;
    }

    private void Instantiate(View parentView) {

        setObserver();
        setInitialText(parentView);
//        setButtons(parentView);
    }

    private void setObserver() {
        final Observer<Rooms> roomsObserver = new Observer<Rooms>() {
            @Override
            public void onChanged(Rooms rooms) {
                activeRoom = rooms;
                setRoomInfo(activeRoom.getID(), activeLevelNumber);
            }
        };

        final Observer<Integer> playerHealthObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer health) {
                playerHealth.setProgress(activePlayer.getHealth(), true);
            }
        };

        final Observer<Enemy> enemyNameObserver = new Observer<Enemy>() {
            @Override
            public void onChanged(Enemy enemy) {
                activeEnemy = enemy;
//                setEnemyNameText(activeEnemy.getName());
//                setEnemyHealthText(activeEnemy.getHealth());
            }
        };

        final Observer<Integer> enemyHealthObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer health) {
//                setEnemyHealthText(activeEnemy.getHealth());
            }
        };

        mainViewModel.getActiveRoom().observe(getViewLifecycleOwner(), roomsObserver);

        mainViewModel.getPlayerHealth().observe(getViewLifecycleOwner(), playerHealthObserver);

        mainViewModel.getEnemyHealth().observe(getViewLifecycleOwner(), enemyHealthObserver);

        mainViewModel.getActiveEnemy().observe(getViewLifecycleOwner(), enemyNameObserver);

        activePlayer = mainViewModel.getActivePlayer();

        activeRoom = mainViewModel.getActiveRoom().getValue();

        activeLevelNumber = mainViewModel.getActiveLevel();

        activeEnemy = mainViewModel.getActiveEnemy().getValue();
    }

    private void setInitialText(View parentView) {
//        enemyHealthText = parentView.findViewById(R.id.enemy_holder);
//        enemyNameText = parentView.findViewById(R.id.enemyname_holder);
        roomInfo = parentView.findViewById(R.id.room_info);
        textBox = parentView.findViewById(R.id.text_box);
        actionText = parentView.findViewById(R.id.action_label);

        actionText.setText(getResources().getStringArray(R.array.planets_array)[0]);

        playerHealth = parentView.findViewById(R.id.playerHealthBar);

        playerHealth.setProgress(activePlayer.getHealth(), true);

        actionButton = parentView.findViewById(R.id.action_button);

        actionButton = parentView.findViewById(R.id.action_button);

        actionButton.setText(getResources().getStringArray(R.array.planets_array)[0]);

        ImageButton right = parentView.findViewById(R.id.right_button);

        ImageButton left = parentView.findViewById(R.id.left_button);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addText(actionButton.getText().toString());
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < getResources().getStringArray(R.array.planets_array).length; i++){
                    if(i == getResources().getStringArray(R.array.planets_array).length - 1 && actionText.getText().toString().equals(getResources().getStringArray(R.array.planets_array)[i])){
                        shiftRight(getResources().getStringArray(R.array.planets_array)[0], getResources().getStringArray(R.array.planet_array)[0]);
                        return;
                    }

                    else if(actionText.getText().toString().equals(getResources().getStringArray(R.array.planets_array)[i]))
                    {
                        shiftRight(getResources().getStringArray(R.array.planets_array)[i+1], getResources().getStringArray(R.array.planet_array)[i+1]);
                        return;
                    }
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < getResources().getStringArray(R.array.planets_array).length; i++){
                    if(i == 0 && actionText.getText().toString().equals(getResources().getStringArray(R.array.planets_array)[i])){
                        shiftLeft(getResources().getStringArray(R.array.planets_array)[getResources().getStringArray(R.array.planets_array).length - 1], getResources().getStringArray(R.array.planet_array)[getResources().getStringArray(R.array.planets_array).length - 1]);
                        return;
                    }

                    else if(actionText.getText().toString().equals(getResources().getStringArray(R.array.planets_array)[i]))
                    {
                        shiftLeft(getResources().getStringArray(R.array.planets_array)[i-1], getResources().getStringArray(R.array.planet_array)[i-1]);
                        return;
                    }
                }
            }
        });

//        setEnemyHealthText(activeEnemy.getHealth());
//        setEnemyNameText(activeEnemy.getName());
        setRoomInfo(activeRoom.getID(), activeLevelNumber);
    }

//    private void setButtons(View parentView){
//        Button north = (Button) parentView.findViewById(R.id.north_button);
//
//        north.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkNorth(view);
//            }
//        });
//
//        Button south = (Button) parentView.findViewById(R.id.south_button);
//
//        south.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkSouth(view);
//            }
//        });
//
//        Button east = (Button) parentView.findViewById(R.id.east_button);
//
//        east.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkEast(view);
//            }
//        });
//
//        Button west = (Button) parentView.findViewById(R.id.west_button);
//
//        west.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkWest(view);
//            }
//        });
//
//        Button player = (Button) parentView.findViewById(R.id.player_button);
//
//        player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                attackEnemy(view);
//            }
//        });
//
//        Button enemy = (Button) parentView.findViewById(R.id.enemy_button);
//
//        enemy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                attackPlayer(view);
//            }
//        });
//    }

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

    private void setRoomInfo(long room, long level) {
        roomInfo.setText(level + " - " + room);
    }

    private void addText(String text){
        textBox.append("\n" + text);
    }

    private void shiftRight(String label, String button){
        actionText.setText(label);
        actionButton.setText(button);
    }

    private void shiftLeft(String label, String button){
        actionText.setText(label);
        actionButton.setText(button);
    }

//    private void setEnemyHealthText(int text) {
//        enemyHealthText.setText(String.valueOf(text));
//    }
//
//    private void setEnemyNameText(String text) {
//        enemyNameText.setText(text);
//    }
}