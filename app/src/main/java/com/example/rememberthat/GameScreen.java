package com.example.rememberthat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameScreen extends AppCompatActivity {
    
   public List<ImageView> buttonList = new ArrayList<>();
   public List<Integer> imageList = new ArrayList<>();
   public List<Icons> iconsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setActionBar();

        addItemsToList();

        findViewById(R.id.startBtn).setOnClickListener(buttonClickListener);





    }

    private final View.OnClickListener buttonClickListener= v->{
        int id = v.getId();

        switch (id){
            case R.id.startBtn:
                startButton();
        }
    };

    void startButton(){
        game();
        //generateRandomLists();
        //FlipAnimation.startAnimation(newButtonList.get(0),newImageList.get(0));
    }

    void game() {
        generateRandomLists();



    }

    void generateRandomLists(){
        Collections.shuffle(imageList);

        for(int i = 0; i < buttonList.size();i++){
            iconsList.get(i).setImage(imageList.get(i));
        }

    }


    void setPairs(List<ImageView> buttons, List<Integer> images) {
        int buttonIndex = 0;
        int imageIndex = 0;
        for (; buttonIndex < buttons.size(); ) {
            for (int j = 0; j < 2; j++) {
                FlipAnimation.startAnimation(buttons.get(buttonIndex), images.get(imageIndex));
                buttonIndex++;
            }
            imageIndex++;
        }


        Icons[] icons = new Icons[12];

        for (int i = 0; i < icons.length; i++) {
            icons[i] = new Icons();
        }
        iconsList.addAll(Arrays.asList(icons));
    }

    void addItemsToList() {



        Integer[] images = {
                R.drawable.bull_nobackground,R.drawable.chick_nobackground,
                R.drawable.fox_nobackground, R.drawable.tiger_nobackground,
                R.drawable.pig_nobackground, R.drawable.zebra_nobackground,
                R.drawable.bull_nobackground,R.drawable.chick_nobackground,
                R.drawable.fox_nobackground, R.drawable.tiger_nobackground,
                R.drawable.pig_nobackground, R.drawable.zebra_nobackground
        };

        imageList.addAll(Arrays.asList(images));

        ImageView[] buttons ={
                findViewById(R.id.button1),findViewById(R.id.button2),findViewById(R.id.button3),
                findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6),
                findViewById(R.id.button7),findViewById(R.id.button8),findViewById(R.id.button9),
                findViewById(R.id.button10),findViewById(R.id.button11),findViewById(R.id.button12)
        };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setTag(buttons[i]);
            iconsList.get(i).setImageView(buttons[i]);
        }

    }

    void playAnimations(){
        for (Icons icons: iconsList) {
            icons.startAnimation();
        }
    }

    boolean cardBack(ImageView v){
        if(v.getTag() = ){
          return true;
        }
      };




    void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}