package com.example.rememberthat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class FlipAnimation {
    FlipAnimation flipAnimation = new FlipAnimation();

    ImageView imageView;
    Integer image;

    public FlipAnimation setImage(Integer image) {
        this.image = image;
        return null;
    }

    public Integer getImage() {
        return image;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public FlipAnimation getFlipAnimation() {
        return flipAnimation;
    }

    void startAnimation(){
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 0f, 1f);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setImageResource(image.intValue());
                objectAnimator2.start();
            }
        });

        objectAnimator1.start();
    }



}


