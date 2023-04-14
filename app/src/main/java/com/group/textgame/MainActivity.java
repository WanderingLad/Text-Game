package com.group.textgame;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.group.textgame.fragments.EndGameFragment;
import com.group.textgame.fragments.MainScreenFragment;
import com.group.textgame.fragments.StartScreenFragment;
import com.group.textgame.model.Rooms;
import com.group.textgame.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            setContentView(R.layout.activity_main);

            controller = new Controller();

            mainViewModel = new ViewModelProvider(this, new MainViewModel(this.getApplication(), getResources().getStringArray(R.array.room_names),
                    getResources().getStringArray(R.array.initial_text), getResources().getStringArray(R.array.return_text), getResources().getStringArray(R.array.look_text))).get(MainViewModel.class);

            final Observer<Integer> screenObserver = new Observer<Integer>() {
                @Override
                public void onChanged(Integer i) {
                    if(i == 1){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen, StartScreenFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("start") // name can be null
                                .commit();
                    }
                    else if(i == 0){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen, MainScreenFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("main") // name can be null
                                .commit();
                    }
                    else if(i == -1){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.screen, EndGameFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("end") // name can be null
                                .commit();
                    }
                }
            };

            mainViewModel.getActiveScreen().observe(this, screenObserver);

            controller.StartGame();
        }
    }


}
