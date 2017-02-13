package biz.ideus.ideuslibexample.ui.listeners;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

/**
 * Created by blackmamba on 09.02.17.
 */
public class ScrollListenerForFAB extends RecyclerView.OnScrollListener {

    private FloatingActionButton fabButton;

    public ScrollListenerForFAB(FloatingActionButton fabButton){
        this.fabButton = fabButton;
    }


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (fabButton != null && (dy > 0 || dy < 0)) {
                fabButton.hide();
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (fabButton != null && newState == RecyclerView.SCROLL_STATE_IDLE ) {
                fabButton.show();
            }
        }


}