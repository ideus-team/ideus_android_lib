package biz.ideus.ideuslib.listeners;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by blackmamba on 10.11.16.
 */

public class SwipeImageTouchListener implements View.OnTouchListener {
    private final View swipeView;
    private AnimationListener animationListener;
    private Handler closeFragmentHandler;
    private Runnable closeFragmentRunnable;

    public SwipeImageTouchListener(View swipeView, AnimationListener animationListener) {
        this.swipeView = swipeView;
        this.animationListener = animationListener;
    }

    private float startY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getRawY();
                return true;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:
                animateSwipeView(v.getHeight());
                return true;
            case MotionEvent.ACTION_MOVE:
                swipeView.setTranslationY(event.getRawY() - startY);
                return true;
            default:
                return false;
        }

    }

    private void animateSwipeView(int parentHeight) {
        closeFragmentHandler = new Handler();
        closeFragmentRunnable = new Runnable() {
            @Override
            public void run() {
                animationListener.closeFragment();
            }
        };
        int partHeight = parentHeight / 7;
        float currentPosition = swipeView.getTranslationY();
        float animateTo = 0.0f;
        if (currentPosition < -partHeight) {
            animateTo = -parentHeight;
            closeFragmentHandler.postDelayed(closeFragmentRunnable, 200);

        } else if (currentPosition > partHeight) {
            animateTo = parentHeight;
            closeFragmentHandler.postDelayed(closeFragmentRunnable, 200);
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(swipeView, View.TRANSLATION_Y
                , animateTo);
        animator.setDuration(200);
        animator.start();

    }

    public interface AnimationListener {
        void closeFragment();
    }

}