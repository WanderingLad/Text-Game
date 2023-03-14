package com.group.textgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final int SLICES_PER_PIZZA = 8;
    private TextView changeableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeableText = findViewById(R.id.hello_world);
    }

    public void changeText(View view) {

        int x = R.string.test1;
        if(changeableText.getText() == getString(R.string.test1))
            changeableText.setText(R.string.test2);
        else
            changeableText.setText(R.string.test1);
    }
}
