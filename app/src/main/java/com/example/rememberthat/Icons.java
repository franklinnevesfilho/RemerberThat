package com.example.rememberthat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Icons {
    Icons icon = new Icons();
    Icons[] iconsArray;

    ImageView button;
    Integer image;

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImageView(ImageView button) {
        this.button = button;
    }

    public ImageView getImageView() {
        return button;
    }


    void startAnimation(){
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(button, "scaleX", 0f, 1f);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                button.setImageResource(image.intValue());
                objectAnimator2.start();
            }
        });

        objectAnimator1.start();
    }
}
