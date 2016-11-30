package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.main_screen.MainFragmentPagerAdapter;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 24.11.16.
 */
//@PerActivity
public class MainActivityVM extends AbstractViewModel<StartView> {


    private BottomNavigationBar bottomNavigationBar;

    public MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter(((MainActivity)getView()).getSupportFragmentManager());
    ViewPager.OnPageChangeListener pageListeneer = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    } ;

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        initBottomBar();
    }

    public FragmentPagerAdapter getAdapter(){
        return pagerAdapter;
    }


    private void initBottomBar() {
        bottomNavigationBar = ((MainActivity)getView()).getBinding().abBottomNavigationBar;

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_android_white_24dp
                                                , R.string.home)
                        .setActiveColorResource(android.R.color.darker_gray)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_bug_report_white_24dp
                                                , R.string.people)
                        .setActiveColorResource(android.R.color.holo_green_dark)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_pets_white_24dp
                                                , R.string.settings)
                        .setActiveColorResource(android.R.color.holo_orange_dark)
                )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

//                switch (position) {
//                    case ANDROID_TAB_POSITION:
//                        presenter.onTabAndroidClick();
//                        break;
//                    case BUG_TAB_POSITION:
//                        presenter.onTabBugClick();
//                        break;
//                    case DOG_TAB_POSITION:
//                        presenter.onTabDogClick();
//                        break;
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                onTabSelected(position);
            }
        });
    }

}

