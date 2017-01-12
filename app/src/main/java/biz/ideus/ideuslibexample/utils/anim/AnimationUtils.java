package biz.ideus.ideuslibexample.utils.anim;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.LinkedList;

/**
 * Created by blackmamba on 12.01.17.
 */

public class AnimationUtils {

    public static void startLoopAlphaAnimation(LinkedList<View> viewQueue
            , long duration
            , int visibility) {

        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);

        if (viewQueue.peek() == null) {
            return;
        }

        viewQueue.peek().startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                viewQueue.peek().setVisibility(visibility);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewQueue.poll();
                startLoopAlphaAnimation(viewQueue, duration, visibility);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void startLoopTranslateAnimation(LinkedList<View> viewQueue
            , long duration
            , int fromX, int toX
            , int fromY, int toY) {

        if (viewQueue.peek() == null) {
            return;
        }

        TranslateAnimation translate = new TranslateAnimation(fromX, toX, fromY, toY);
        translate.setDuration(duration);
        translate.setFillAfter(true);

        viewQueue.peek().startAnimation(translate);

        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                viewQueue.peek().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewQueue.poll();
                startLoopTranslateAnimation(viewQueue, duration, fromX, toX, fromY, toY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void startLoopScaleAnimation(LinkedList<View> viewQueue
            , long duration
            , float startScale
            , float endScale) {

        if (viewQueue.peek() == null) {
            return;
        }

        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(duration);

        viewQueue.peek().startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                viewQueue.peek().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewQueue.poll();
                startLoopScaleAnimation(viewQueue, duration, startScale, endScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void startAlphaAnimation(View v
            , long duration
            , int visibility
            , AnimationFinishListener animationFinishListener) {

        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(visibility);
                if (animationFinishListener != null) {
                    animationFinishListener.onFinishAnimate(v);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    public static void startAlphaAnimation(View v, long duration, int visibility) {
        startAlphaAnimation(v, duration, visibility, null);
    }

    public static void startTranslateAnimation(View v, long duration,
                                               int fromX, int toX,
                                               int fromY, int toY) {
        TranslateAnimation translate = new TranslateAnimation(fromX, toX, fromY, toY);
        translate.setDuration(duration);
        translate.setFillAfter(true);
        v.startAnimation(translate);
    }

    public void startScaleAnimation(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        v.startAnimation(anim);
    }
}
