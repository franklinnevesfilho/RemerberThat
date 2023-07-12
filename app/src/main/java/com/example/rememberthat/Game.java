package com.example.rememberthat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private LinkedList<Icon> buttonsFlipped = new LinkedList<>();
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
        buttonsFlipped.clear();
        startButton.setClickable(false);
        generateRandomLists();
    }

    void generateRandomLists(){

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
        for(Icon icon: buttonList) icon.getIcon().setOnClickListener(oc ->{
            if(previouslySelected == null){
                previouslySelected = icon;
            }
            buttonClick(icon);
        });
    }

    public void buttonClick(Icon icon){
        icon.flip();
        buttonsFlipped.add(icon);
        if(buttonsFlipped.size() == buttonList.size()) restartGame();
        else if (buttonsFlipped.size() % 2 == 0 && previouslySelected != null){
            if(!buttonsMatch()){
                previouslySelected.flip();
                icon.flip();
            }else{
                // play sound
            }
            previouslySelected = null;
        }
    }
    public boolean buttonsMatch(){
        boolean match = false;
        if(buttonsFlipped.getLast().isButtonFlipped() && previouslySelected.isButtonFlipped()){
            match = buttonsFlipped.getLast().getFront() == previouslySelected.getFront();
        }
        return match;
    }

    public void restartGame(){

    }
    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}