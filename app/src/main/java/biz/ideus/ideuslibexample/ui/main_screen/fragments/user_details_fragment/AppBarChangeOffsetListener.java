package biz.ideus.ideuslibexample.ui.main_screen.fragments.user_details_fragment;

import android.support.design.widget.AppBarLayout;

/**
 * Created by blackmamba on 12.01.17.
 */

public abstract class AppBarChangeOffsetListener implements AppBarLayout.OnOffsetChangedListener {

    private float percentage;
    private boolean readyForExit;

    public AppBarChangeOffsetListener(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage > 0.2f) {
            onChangePercentOffset(percentage);
        }

        if (!readyForExit && percentage > 0.2f) {
            readyForExit = true;
        }

        if (readyForExit && percentage < 0.2f) {
            readyForExit = false;
            onCloseFragment(0);
        }
    }


    public abstract void onChangePercentOffset(float percent);

    public abstract void onCloseFragment(float percent);

}

