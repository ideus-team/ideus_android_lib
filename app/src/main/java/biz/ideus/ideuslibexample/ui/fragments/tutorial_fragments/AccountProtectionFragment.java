package biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 23.11.16.
 */

public class AccountProtectionFragment extends BaseTutorialFragment {
    @Override
    public String setTitle() {
        return getString(R.string.tutorial_title_protection);
    }

    @Override
    public String setAbout() {
        return getString(R.string.about_protection);
    }

    @Override
    public Drawable setImage() {
        return getResources().getDrawable(R.drawable.protection);
    }
}
