package com.group.textgame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    private Rooms activeRoom;

    private Player activePlayer;

    private Enemy activeEnemy;

    private TextView changeableText, playerHealthText, enemyHealthText, enemyNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new MainViewModel(getApplication());
        changeableText = findViewById(R.id.text_holder);
        playerHealthText = findViewById(R.id.player_holder);
        enemyHealthText = findViewById(R.id.enemy_holder);
        enemyNameText = findViewById(R.id.enemyname_holder);

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

        mainViewModel.getActiveRoom().observe(this, roomsObserver);

        mainViewModel.getPlayerHealth().observe(this, playerHealthObserver);

        mainViewModel.getEnemyHealth().observe(this, enemyHealthObserver);

        mainViewModel.getActiveEnemy().observe(this, enemyNameObserver);

        activePlayer = mainViewModel.getActivePlayer();

        activeRoom = mainViewModel.getActiveRoom().getValue();

        activeEnemy = mainViewModel.getActiveEnemy().getValue();

        setPlayerText(activePlayer.getHealth());

        setEnemyHealthText(activeEnemy.getHealth());

        setEnemyNameText(activeEnemy.getName());

        setText(activeRoom.getRoomName());
    }

    public void checkNorth(View view){
        if(activeRoom.getNorthRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkSouth(View view){
        if(activeRoom.getSouthRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkEast(View view){
        if(activeRoom.getEastRoom() != 0){
            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
        }
    }

    public void checkWest(View view){
        if(activeRoom.getWestRoom() != 0){
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
