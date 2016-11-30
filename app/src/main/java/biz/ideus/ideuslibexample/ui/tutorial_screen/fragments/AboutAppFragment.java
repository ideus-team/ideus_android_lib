package biz.ideus.ideuslibexample.ui.tutorial_screen.fragments;

import android.graphics.drawable.Drawable;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 23.11.16.
 */

public class AboutAppFragment extends BaseTutorialFragment {
    @Override
    public String setTitle() {
        return getString(R.string.tutorial_title_welcome);
    }

    @Override
    public String setAbout() {
        return getString(R.string.about_welcome);
    }

    @Override
    public Drawable setImage() {
        return getResources().getDrawable(R.drawable.logo_circle);
    }
}
