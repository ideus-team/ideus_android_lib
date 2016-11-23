package biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 23.11.16.
 */

public class PersonalNetworkFragment extends BaseTutorialFragment {
    @Override
    public String setTitle() {
        return getString(R.string.tutorial_title_network);
    }

    @Override
    public String setAbout() {
        return getString(R.string.about_network);
    }

    @Override
    public Drawable setImage() {
        return getResources().getDrawable(R.drawable.logo_circle);
    }
}
