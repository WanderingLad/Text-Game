package com.group.textgame.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

    private ProgressBar playerHealth, enemyHealth;

    private Button actionButton, startButton;

    private ImageButton right, left;

    private String[] currentText;

    private LinearLayout enemyOverlay;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.main_screen, container, false);

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
                if(activeEnemy != null){
                    setEnemyNameText(activeEnemy.getName());
                    setEnemyHealth(activeEnemy.getHealth());
                    enemyOverlay.setVisibility(View.VISIBLE);
                    textBox.setPadding(0, 150, 0, 0);
                } else{
                    textBox.setPadding(0, 0, 0, 0);

                    enemyOverlay.setVisibility(View.INVISIBLE);
                }
            }
        };

        final Observer<Integer> enemyHealthObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer health) {
                setEnemyHealth(activeEnemy.getHealth());
                if(activeEnemy.getHealth() == 0){
                    enemyOverlay.setVisibility(View.INVISIBLE);
                }
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
        enemyNameText = parentView.findViewById(R.id.enemy_holder);
        roomInfo = parentView.findViewById(R.id.room_info);
        textBox = parentView.findViewById(R.id.text_box);
        actionText = parentView.findViewById(R.id.action_label);

        currentText = getResources().getStringArray(R.array.main_array);

        actionText.setText(currentText[0]);

        playerHealth = parentView.findViewById(R.id.playerHealthBar);

        enemyHealth = parentView.findViewById(R.id.enemyHealthBar);

        playerHealth.setProgress(activePlayer.getHealth(), true);

        enemyOverlay = parentView.findViewById(R.id.enemy_overlay);

        if (activeEnemy != null) {
            enemyHealth.setProgress(activeEnemy.getHealth(), true);

            setEnemyNameText(activeEnemy.getName());
        }
        setRoomInfo(activeRoom.getID(), activeLevelNumber);
    }

    private void setButtons(View parentView){
        actionButton = parentView.findViewById(R.id.action_button);

        right = parentView.findViewById(R.id.right_button);

        left = parentView.findViewById(R.id.left_button);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(actionText.getText().toString()){
                    case "Leave":
                        currentText = getResources().getStringArray(R.array.room_array);
                        actionText.setText(currentText[0]);
                        return;
                    case "Attack":
                        attackEnemy(view);
                        return;
                    case "North":
                        if(checkNorth()){
                            currentText = getResources().getStringArray(R.array.main_array);
                            actionText.setText(currentText[0]);
                        }
                        return;
                    case "South":
                        if(checkSouth()){
                            currentText = getResources().getStringArray(R.array.main_array);
                            actionText.setText(currentText[0]);
                        }
                        break;
                    case "East":
                        if(checkEast()){
                            currentText = getResources().getStringArray(R.array.main_array);
                            actionText.setText(currentText[0]);
                        }
                        return;
                    case "West":
                        if(checkWest()){
                            currentText = getResources().getStringArray(R.array.main_array);
                            actionText.setText(currentText[0]);
                        }
                        return;
                    default:
                        return;
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < currentText.length; i++){
                    if(i == currentText.length - 1 && actionText.getText().equals(currentText[i])){
                        shiftRight(currentText[0]);
                        return;
                    }

                    else if(actionText.getText().equals(currentText[i]))
                    {
                        shiftRight(currentText[i+1]);
                        return;
                    }
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < currentText.length; i++){
                    if(i == 0 && actionText.getText().equals(currentText[i])){
                        shiftLeft(currentText[currentText.length - 1]);
                        return;
                    }

                    else if(actionText.getText().equals(currentText[i]))
                    {
                        shiftLeft(currentText[i-1]);
                        return;
                    }
                }
            }
        });
    }

    private boolean checkNorth(){
        if(activeRoom.getNorthRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

            return true;
        }
        return false;
    }

    private boolean checkSouth(){
        if(activeRoom.getSouthRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

            return true;
        }

        return false;
    }

    private boolean checkEast(){
        if(activeRoom.getEastRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

            return true;
        }

        return false;
    }

    private boolean checkWest(){
        if(activeRoom.getWestRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getWestRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

            return true;
        }

        return false;
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

    private void shiftRight(String label){
        actionText.setText(label);
    }

    private void shiftLeft(String label){
        actionText.setText(label);
    }

    private void setEnemyHealth(int text) {
        enemyHealth.setProgress(text, true);
    }

    private void setEnemyNameText(String text) {
        enemyNameText.setText(text);
    }
}