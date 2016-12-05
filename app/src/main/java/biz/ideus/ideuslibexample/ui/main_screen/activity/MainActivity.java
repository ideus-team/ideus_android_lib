package biz.ideus.ideuslibexample.ui.main_screen.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.inputmethod.InputMethodManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.MainFragmentPagerAdapter;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;

///**
// * Created by blackmamba on 24.11.16.
// */
//

public class MainActivity extends BaseActivity<StartView, MainActivityVM, ActivityMainBinding>
        implements StartView {

    private BottomNavigationBar bottomNavigationBar;
    @Inject MainFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        initBottomBar();
        initPager();
    }

    private void initPager() {
        getBinding().viewPager.setOffscreenPageLimit(Constants.MAIN_SCREEN_PAGES_COUNT);
        ViewPager.OnPageChangeListener pageListeneer = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }



            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
              hideKeyboard(MainActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } ;
        getBinding().viewPager.setAdapter(pagerAdapter);
        getBinding().viewPager.addOnPageChangeListener(pageListeneer);
    }




    private void initBottomBar() {
        bottomNavigationBar = getBinding().abBottomNavigationBar;

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_disactive
                        , R.string.home)
                        .setActiveColorResource(R.color.midle_color_main_light)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_people_disactive
                        , R.string.people)
                        .setActiveColorResource(R.color.color_main)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_settings_disactive
                        , R.string.settings)
                        .setActiveColorResource(android.R.color.holo_blue_dark)
                )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                getBinding().viewPager.setCurrentItem(position, true);
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

    @Nullable
    @Override
    public Class<MainActivityVM> getViewModelClass() {
        return MainActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_main, BR.viewModel, this);
    }
}

