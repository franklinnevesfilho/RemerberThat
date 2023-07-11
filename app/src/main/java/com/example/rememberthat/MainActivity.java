package com.example.rememberthat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();

        playButton();
    }


    void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar_layout);


    }
  void playButton(){
      Button playButton = findViewById(R.id.playBtn);

      playButton.setOnClickListener(v -> {
          Intent gameScreen = new Intent(MainActivity.this, GameScreen.class);
            startActivity(gameScreen);
      });
  }


}