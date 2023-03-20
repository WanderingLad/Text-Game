package com.group.textgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.RoomsViewModel;

import com.group.textgame.viewmodel.RoomsViewModel;

public class MainActivity extends AppCompatActivity {

    private RoomsViewModel roomsViewModel;

    private TextView changeableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomsViewModel = new ViewModelProvider(this).get(RoomsViewModel.class);

        changeableText = findViewById(R.id.hello_world);
    }


}
