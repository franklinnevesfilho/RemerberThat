package com.example.rememberthat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game extends AppCompatActivity {
    private Button startButton;
    private final List<Icon> buttonList = new ArrayList<>();
    private final List<Integer> imageList = new ArrayList<>();
    private Icon previouslySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setActionBar();
        setUpResources();
        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener(oc -> startButton());

    }

    void startButton(){
        startButton.setClickable(false);
        startButton.setVisibility(View.GONE);
        setUpMatches();
    }

    void setUpMatches(){
        Collections.shuffle(imageList);
        LinkedList<Integer> imagePattern = new LinkedList<>();
        for(int i = 0; i < buttonList.size()/2; i++) imagePattern.add(i);

        LinkedList<Integer> buttonPattern = new LinkedList<>();
        for(int i = 0; i < buttonList.size(); i++) buttonPattern.add(i);
        Collections.shuffle(buttonPattern);

        for(int i = 0, j = 0; i < imageList.size(); i++, j++){
                int image = imageList.get(imagePattern.get(i));
                buttonList.get(buttonPattern.get(j)).setFront(image);
                j++;
                buttonList.get(buttonPattern.get(j)).setFront(image);
        }
        for(Icon icon: buttonList) icon.flip();
        Handler handler = new Handler();
        handler.postDelayed(()->{
            for(Icon icon: buttonList) {
                icon.flip();
                icon.clickable(true);
            }
        },1000);
    }
    private void setUpResources() {

        Integer[] images = {
                R.drawable.bull_nobackground,
                R.drawable.chick_nobackground,
                R.drawable.fox_nobackground,
                R.drawable.tiger_nobackground,
                R.drawable.pig_nobackground,
                R.drawable.crab_nobackground
        };

        imageList.addAll(Arrays.asList(images));

        ImageView[] buttons ={
                findViewById(R.id.button1),findViewById(R.id.button2),findViewById(R.id.button3),
                findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6),
                findViewById(R.id.button7),findViewById(R.id.button8),findViewById(R.id.button9),
                findViewById(R.id.button10),findViewById(R.id.button11),findViewById(R.id.button12)
        };

        for (ImageView button: buttons) buttonList.add(new Icon(button));
        for(Icon icon: buttonList) {
            icon.getIcon().setOnClickListener(oc -> buttonClick(icon));
            icon.clickable(false);
        }
    }

    public void buttonClick(Icon icon){
        icon.flip();
        Handler handler = new Handler();
        handler.postDelayed(()->{
            if (previouslySelected != null){
                if(!buttonsMatch(icon)){
                    previouslySelected.flip();
                    icon.flip();
                }
            }
            if(matchesFound()) restartGame();
            if(previouslySelected == null) previouslySelected = icon;
            else previouslySelected = null;
        },800);
    }
    public boolean buttonsMatch(Icon icon){
        boolean match = false;
        if(icon.isButtonFlipped() && previouslySelected.isButtonFlipped()){
            match = icon.getFront() == previouslySelected.getFront();
        }
        return match;
    }

    public boolean matchesFound(){
        boolean result = true;
        for(Icon icon: buttonList){
            if(!icon.isButtonFlipped()){
                result = false;
                break;
            }
        }
        return result;
    }

    @SuppressLint("SetTextI18n")
    public void restartGame(){
        startButton.setText("Restart");
        startButton.setVisibility(View.VISIBLE);
        startButton.setClickable(true);
        for(Icon button: buttonList) button.initialState();
    }
    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}