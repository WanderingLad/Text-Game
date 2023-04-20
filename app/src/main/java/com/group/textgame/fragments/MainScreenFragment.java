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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.group.textgame.R;
import com.group.textgame.model.Enemy;
import com.group.textgame.model.Object;
import com.group.textgame.model.Player;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.MainViewModel;

import java.util.Arrays;
import java.util.List;

public class MainScreenFragment extends Fragment {

    private MainViewModel mainViewModel;

    private Rooms activeRoom;

    private long activeLevelNumber;

    private Player activePlayer;

    private Enemy activeEnemy;

    private TextView enemyNameText, roomInfo, textBox, actionText;

    private ProgressBar playerHealth, enemyHealth;

    private Button actionButton, backButton;

    private ImageButton right, left;

    private List<String> currentRoomItems, currentInventory, currentText, previousText;

    private LinearLayout enemyOverlay;

    private ScrollView scrollView;

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
                currentRoomItems = mainViewModel.getRoomsObjectString();
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
                if(activeEnemy != null && activeEnemy.getHealth() > 0){
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
                if(activeEnemy.getHealth() <= 0){
                    enemyOverlay.setVisibility(View.INVISIBLE);
                    addText("You have slain the " + activeEnemy.getName());
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

        currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
        previousText = Arrays.asList(getResources().getStringArray(R.array.main_array));

        currentRoomItems = mainViewModel.getRoomsObjectString();

        currentInventory = mainViewModel.getInventory();

        actionText.setText(currentText.get(0));

        playerHealth = parentView.findViewById(R.id.playerHealthBar);

        enemyHealth = parentView.findViewById(R.id.enemyHealthBar);

        playerHealth.setProgress(activePlayer.getHealth(), true);

        enemyOverlay = parentView.findViewById(R.id.enemy_overlay);

        scrollView = parentView.findViewById(R.id.text_scroller);

        if (activeEnemy != null) {
            enemyHealth.setProgress(activeEnemy.getHealth(), true);

            setEnemyNameText(activeEnemy.getName());
        }
        setRoomInfo(activeRoom.getID(), activeLevelNumber);

        addText(activeRoom.getInitialText());
        mainViewModel.setEnteredBool(true);
    }

    private void setButtons(View parentView){
        actionButton = parentView.findViewById(R.id.action_button);

        right = parentView.findViewById(R.id.right_button);

        left = parentView.findViewById(R.id.left_button);

        backButton = parentView.findViewById(R.id.back_button);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentRoomItems.contains(actionText.getText().toString())){

                    List<Object> x = mainViewModel.getObjects(activeRoom.getRoomName());

                    for(Object o : x){
                        if(o.getName().equals(actionText.getText())){
                            addText(o.getObjectText());

                            if(mainViewModel.getObjects(o.getName()) != null){
                                for(Object a : mainViewModel.getObjects(o.getName())){

                                    if(!currentInventory.contains(a.getName())){
                                        addText("You got a " + a.getName() + " from the " + o.getName());

                                        mainViewModel.changeParent(a, "player");

                                        currentInventory = mainViewModel.getInventory();
                                    } else {
                                        addText("You already got a " + a.getName() + " from the " + o.getName());
                                    }
                                }
                            }
                        }
                    }

                    currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                    actionText.setText(currentText.get(0));

                    return;
                } else if(currentInventory.contains(actionText.getText())){

                    List<Object> x = mainViewModel.getObjects(activePlayer.getName());

                    for(Object o : x){
                        if(o.getName().equals(actionText.getText())){
                            addText(o.getObjectText());
                        }
                        if(o.getName().contains("Bread")){
                            if(activePlayer.getHealth() < 100){
                                addText("You eat the " + o.getName());
                                activePlayer.setHealth(100);
                                mainViewModel.setPlayerHealth();
                                mainViewModel.changeParent(o, "Nothing");
                                currentInventory = mainViewModel.getInventory();
                            }
                        }
                    }

                    currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                    actionText.setText(currentText.get(0));

                    return;
                }
                else{

                    switch(actionText.getText().toString()){
                        case "Move":
                            if(activeEnemy != null){
                                if(activeEnemy.getHealth() > 0){
                                    addText("There's no way out but fighting");
                                } else {
                                    previousText = currentText;
                                    currentText = Arrays.asList(getResources().getStringArray(R.array.room_array));
                                    actionText.setText(currentText.get(0));
                                }
                            } else {
                                previousText = currentText;
                                currentText = Arrays.asList(getResources().getStringArray(R.array.room_array));
                                actionText.setText(currentText.get(0));
                            }
                            return;
                        case "Look":
                            addText(activeRoom.getLookText());
                            return;
                        case "Search":
                            previousText = currentText;
                            currentText = currentRoomItems;
                            actionText.setText(currentText.get(0));
                            return;
                        case "Inventory":
                            if(currentInventory.size() != 0){
                                previousText = currentText;
                                currentText = currentInventory;
                                actionText.setText(currentText.get(0));
                            } else {
                                addText("Your inventory is empty");
                            }
                            return;
                        case "Attack":
                            if(activeEnemy == null || activeEnemy.getHealth() <= 0){
                                addText("There's nothing to attack.");
                            } else if(activeEnemy.getHealth() > 0){
                                addText("You attack");
                                attackEnemy(view);
                                if(activeEnemy.getHealth() > 0){
                                    addText("The " + activeEnemy.getName() + " attacks");
                                    attackPlayer(view);
                                }
                            } else {
                                addText("You've slain the " + activeEnemy.getName());
                            }
                            return;
                        case "North":
                            if(checkNorth()){
                                previousText = currentText;
                                currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                                actionText.setText(currentText.get(0));
                            }
                            return;
                        case "South":
                            if(checkSouth()){
                                previousText = currentText;
                                currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                                actionText.setText(currentText.get(0));
                            }
                            break;
                        case "East":
                            if(checkEast()){
                                previousText = currentText;
                                currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                                actionText.setText(currentText.get(0));
                            }
                            return;
                        case "West":
                            if(checkWest()){
                                previousText = currentText;
                                currentText = Arrays.asList(getResources().getStringArray(R.array.main_array));
                                actionText.setText(currentText.get(0));
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < currentText.size(); i++){
                    if(i == currentText.size() - 1 && actionText.getText().equals(currentText.get(i))){
                        shiftRight(currentText.get(0));
                        return;
                    }

                    else if(actionText.getText().equals(currentText.get(i)))
                    {
                        shiftRight(currentText.get(i + 1));
                        return;
                    }
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < currentText.size(); i++){
                    if(i == 0 && actionText.getText().equals(currentText.get(i))){
                        shiftLeft(currentText.get(currentText.size() - 1));
                        return;
                    }

                    else if(actionText.getText().equals(currentText.get(i)))
                    {
                        shiftLeft(currentText.get(i - 1));
                        return;
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentText = previousText;
                actionText.setText(currentText.get(0));
            }
        });
    }

    private boolean checkNorth(){
        if(activeRoom.getNorthRoom() != 0){

            if(mainViewModel.getRoom(activeRoom.getNorthRoom()).isLockedBool() && !currentInventory.contains(mainViewModel.getRoom(activeRoom.getNorthRoom()).getKeyname())){
                addText("This room is locked, you'll have to find a key.");
                return false;
            }

            if(mainViewModel.getRoom(activeRoom.getNorthRoom()).hasEnteredBool()){
                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
                addText(activeRoom.getReturnText());
            } else{
                addText(activeRoom.getNorthText());

                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                if(activeRoom.isEndRoom()){
                    mainViewModel.setActiveScreen(-1);
                }

                addText(activeRoom.getInitialText());

                mainViewModel.setEnteredBool(true);
            }

            return true;
        } else if(activeRoom.getNorthText() != null){
            addText(activeRoom.getNorthText());

            return true;
        }

        addText("You can't go that way.");
        return false;
    }

    private boolean checkSouth(){
        if(activeRoom.getSouthRoom() != 0){

            if(mainViewModel.getRoom(activeRoom.getSouthRoom()).isLockedBool() && !currentInventory.contains(mainViewModel.getRoom(activeRoom.getSouthRoom()).getKeyname())){
                addText("This room is locked, you'll have to find a key.");
                return false;
            }

            if(mainViewModel.getRoom(activeRoom.getSouthRoom()).hasEnteredBool()){
                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
                addText(activeRoom.getReturnText());
            }
            else{
                addText(activeRoom.getSouthText());

                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                if(activeRoom.isEndRoom()){
                    mainViewModel.setActiveScreen(-1);
                }

                addText(activeRoom.getInitialText());
                mainViewModel.setEnteredBool(true);
            }

            return true;
        } else if(activeRoom.getSouthText() != null){

            addText(activeRoom.getSouthText());

            return true;
        }
        addText("You can't go that way.");
        return false;
    }

    private boolean checkEast(){
        if(activeRoom.getEastRoom() != 0){

            if(mainViewModel.getRoom(activeRoom.getEastRoom()).isLockedBool() && !currentInventory.contains(mainViewModel.getRoom(activeRoom.getEastRoom()).getKeyname())){
                addText("This room is locked, you'll have to find a key.");
                return false;
            }

            if(mainViewModel.getRoom(activeRoom.getEastRoom()).hasEnteredBool()){
                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                addText(activeRoom.getReturnText());
            }
            else{
                addText(activeRoom.getEastText());

                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                if(activeRoom.isEndRoom()){
                    mainViewModel.setActiveScreen(-1);
                }

                addText(activeRoom.getInitialText());

                mainViewModel.setEnteredBool(true);
            }

            return true;
        } else if(activeRoom.getEastText() != null){

            addText(activeRoom.getEastText());

            return true;
        }

        addText("You can't go that way.");

        return false;
    }

    private boolean checkWest(){
        if(activeRoom.getWestRoom() != 0){

            if(mainViewModel.getRoom(activeRoom.getWestRoom()).isLockedBool() && !currentInventory.contains(mainViewModel.getRoom(activeRoom.getWestRoom()).getKeyname())){
                addText("This room is locked, you'll have to find a key.");
                return false;
            }

            if(mainViewModel.getRoom(activeRoom.getWestRoom()).hasEnteredBool()){
                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getWestRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                addText(activeRoom.getReturnText());
            }
            else{
                addText(activeRoom.getWestText());

                mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getWestRoom()));
                mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());

                if(activeRoom.isEndRoom()){
                    mainViewModel.setActiveScreen(-1);
                }

                addText(activeRoom.getInitialText());

                mainViewModel.setEnteredBool(true);
            }

            return true;
        } else if(activeRoom.getWestText() != null){

            addText(activeRoom.getWestText());

            return true;
        }

        addText("You can't go that way.");

        return false;
    }

    public void attackEnemy(View view){
            if(currentInventory.contains("Short Sword")){
                activePlayer.attackTarget(activeEnemy, 4);
            } else if(currentInventory.contains("Rusty Knife")){
                activePlayer.attackTarget(activeEnemy, 2);
            } else {
                activePlayer.attackTarget(activeEnemy, 0);
            }
            mainViewModel.setEnemyHealth();
    }

    public void attackPlayer(View view){
        if(activePlayer.getHealth() >= activeEnemy.getDamage()){
            if(currentInventory.contains("Shield")){
                activeEnemy.attackTarget(activePlayer, -2);
            } else {
                activeEnemy.attackTarget(activePlayer, 0);
            }
            mainViewModel.setPlayerHealth();
        } else {
            mainViewModel.setActiveScreen(-1);
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

    private void addText(String text){
        textBox.append("\n" + text + "\n");
        scrollView.post(new Runnable()
        {
            public void run()
            {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}