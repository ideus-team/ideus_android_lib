package biz.ideus.ideuslibexample.ui.view_behavior;

import android.support.v7.widget.RecyclerView;

/**
 * Created by blackmamba on 09.02.17.
 */
public abstract class ScrollListenerForFAB extends RecyclerView.OnScrollListener {


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0 || dy < 0) {
            hideFab();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            showFab();
        }

        super.onScrollStateChanged(recyclerView, newState);
    }

    public abstract void showFab();

    public abstract void hideFab();

}