package biz.ideus.ideuslibexample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.interfaces.TutorialPagerActivityListener;
import biz.ideus.ideuslibexample.interfaces.TutorialPagerFragmentListener;
import biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments.AboutAppFragment;
import biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments.AccountProtectionFragment;
import biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments.BaseTutorialFragment;
import biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments.CustomisationFragment;
import biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments.PersonalNetworkFragment;

/**
 * Created by blackmamba on 23.11.16.
 */

@PerActivity
public class TutorialPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_COUNT = 4;
List<BaseTutorialFragment> fragmentList = new ArrayList<>();
    private TutorialPagerFragmentListener tutorialPagerFragmentListener;
    private TutorialPagerActivityListener tutorialPagerActivityListener;

    public void setTutorialPagerActivityListener(TutorialPagerActivityListener tutorialPagerActivityListener) {
        this.tutorialPagerActivityListener = tutorialPagerActivityListener;
    }

    public void setTutorialPagerFragmentListener(TutorialPagerFragmentListener tutorialPagerFragmentListener) {
        this.tutorialPagerFragmentListener = tutorialPagerFragmentListener;
    }

    @Inject
    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
        setFragments();
    }

    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = fragmentList.get(position).setAdapter(this);
//        if(tutorialPagerFragmentListener != null) {
//            tutorialPagerFragmentListener.getPosition(position);
//        }
        tutorialPagerActivityListener.changeSelectorBtn(position);
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

}
