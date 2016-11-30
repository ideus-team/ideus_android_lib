package biz.ideus.ideuslibexample.ui.tutorial_screen.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.AboutAppFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.AccountProtectionFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.BaseTutorialFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.CustomisationFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.PersonalNetworkFragment;

/**
 * Created by blackmamba on 23.11.16.
 */

@PerActivity
public class TutorialPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{
    private final int PAGE_COUNT = 4;
    private OnPagerListener onPagerListener;

    public void setOnPagerListener(OnPagerListener onPagerListener) {
        this.onPagerListener = onPagerListener;
    }

    List<BaseTutorialFragment> fragmentList = new ArrayList<>();

    @Inject
    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
        setFragments();
    }

    @Override
    public Fragment getItem(int position) {
            return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    private void setFragments(){
        fragmentList.add(new AboutAppFragment());
        fragmentList.add(new PersonalNetworkFragment());
        fragmentList.add(new AccountProtectionFragment());
        fragmentList.add(new CustomisationFragment());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onPagerListener.selectPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnPagerListener {
        void selectPage(int position);
    }
}
