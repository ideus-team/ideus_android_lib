package biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 23.11.16.
 */

public class PersonalNetworkFragment extends BaseTutorialFragment {
    @Override
    public String getTitle() {
        return getString(R.string.tutorial_title_network);
    }

    @Override
    public String getAbout() {
        return getString(R.string.about_network);
    }

    @Override
    public Drawable getImage() {
        return getResources().getDrawable(R.drawable.networking);
    }
}
