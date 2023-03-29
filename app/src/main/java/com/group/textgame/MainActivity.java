package com.group.textgame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.CharacterViewModel;
import com.group.textgame.viewmodel.RoomsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RoomsViewModel roomsViewModel;

    private CharacterViewModel characterViewModel;

    private Rooms activeRoom;

    private TextView changeableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomsViewModel = new ViewModelProvider(this).get(RoomsViewModel.class);
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        changeableText = findViewById(R.id.text_holder);

        activeRoom = roomsViewModel.getRoom(1);
    }

    public void checkNorth(View view){
        if(activeRoom.getNorthRoom() != 0){
            activeRoom = roomsViewModel.getRoom(activeRoom.getNorthRoom());
            setText(activeRoom.getRoomName());
        }
    }

    public void checkSouth(View view){
        if(activeRoom.getSouthRoom() != 0){
            activeRoom = roomsViewModel.getRoom(activeRoom.getSouthRoom());
            setText(activeRoom.getRoomName());
        }
    }

    public void checkEast(View view){
        if(activeRoom.getEastRoom() != 0){
            activeRoom = roomsViewModel.getRoom(activeRoom.getEastRoom());
            setText(activeRoom.getRoomName());
        }
    }

    public void checkWest(View view){
        if(activeRoom.getWestRoom() != 0){
            activeRoom = roomsViewModel.getRoom(activeRoom.getWestRoom());
            setText(activeRoom.getRoomName());
        }
    }

    private void setText(String text) {
        changeableText.setText(text);
    }

}
