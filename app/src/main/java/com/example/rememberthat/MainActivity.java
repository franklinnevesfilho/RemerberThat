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
        this.setActionBar();

          Button playButton = findViewById(R.id.playBtn);

          playButton.setOnClickListener(oc -> openGameScreen());
    }


    public void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar_layout);


    }
    public void openGameScreen(){
          Intent intent = new Intent(MainActivity.this,Game.class);
          startActivity(intent);
    }



}