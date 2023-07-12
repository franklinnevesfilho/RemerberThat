package com.example.rememberthat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Icon {
    private ButtonSide side;
    private enum ButtonSide{FRONT , BACK}
    private final ImageView icon;
    private final int back = R.drawable.button;
    private int front;

    public Icon(ImageView icon) {
        this.icon = icon;
        initialState();
    }

    public void initialState() {
        icon.setImageResource(back);
        side = ButtonSide.BACK;
    }
    public ImageView getIcon() {
        return icon;
    }

    public int getFront() {
        return front;
    }
    public void setFront(int front) {
        this.front = front;
    }
    public void flip(){
        int newImage;
        if(isButtonFlipped()){
            newImage = back;
            icon.setClickable(true);
        }
        else {
            newImage = front;
            icon.setClickable(false);

        }
        startAnimation(newImage);
    }
    public void clickable(boolean clickState){
        icon.setClickable(clickState);
    }
    public boolean isButtonFlipped() {
        return side == ButtonSide.FRONT;
    }
    private void flipButtonStatus(){
        if(side == ButtonSide.FRONT)side = ButtonSide.BACK;
        else side = ButtonSide.FRONT;
    }

    private void startAnimation(int newImage){

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(icon, "scaleX", 1f, 0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(icon, "scaleX", 0f, 1f);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                icon.setImageResource(newImage);
                objectAnimator2.start();
            }
        });
        objectAnimator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flipButtonStatus();
            }
        });
        objectAnimator1.start();
    }
}
