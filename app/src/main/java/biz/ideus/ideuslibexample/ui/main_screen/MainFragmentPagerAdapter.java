package biz.ideus.ideuslibexample.ui.main_screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment.HomeFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment.PeopleFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment.SettingsFragment;
import biz.ideus.ideuslibexample.utils.Constants;

/**
 * Created by user on 30.11.2016.
 */

@PerActivity
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    @Inject
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.HOME_TAB_POSITION : return new HomeFragment();
            case Constants.PEOPLE_TAB_POSITION : return new PeopleFragment();
            case Constants.SETTINGS_TAB_POSITION: return new SettingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constants.MAIN_SCREEN_PAGES_COUNT;
    }

}
