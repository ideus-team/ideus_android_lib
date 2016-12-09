package biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 23.11.16.
 */

public class CustomisationFragment extends BaseTutorialFragment {
    @Override
    public String setTitle() {
        return getString(R.string.tutorial_title_customisation);
    }

    @Override
    public String setAbout() {
        return getString(R.string.about_customisation);
    }

    @Override
    public Drawable setImage() {
        return getResources().getDrawable(R.drawable.customisation);
    }
}
