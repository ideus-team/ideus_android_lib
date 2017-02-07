package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.main_screen.MainFragmentPagerAdapter;
import biz.ideus.ideuslibexample.utils.Constants;


public class MainActivity extends AbstractMainActivity {

    private BottomNavigationBar bottomNavigationBar;

    @Inject
    MainFragmentPagerAdapter pagerAdapter;

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
                hideKeyboard();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        getBinding().viewPager.setAdapter(pagerAdapter);
        getBinding().viewPager.addOnPageChangeListener(pageListeneer);
    }


    private void initBottomBar() {
        int buttonsColorResource = R.color.color_main;
        bottomNavigationBar = getBinding().abBottomNavigationBar;

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_android_white_24dp
                        , R.string.home)
                        .setActiveColorResource(buttonsColorResource)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_bug_report_white_24dp
                        , R.string.people)
                        .setActiveColorResource(buttonsColorResource)
                )
                .addItem(new BottomNavigationItem(R.drawable.ic_pets_white_24dp
                        , R.string.settings)
                        .setActiveColorResource(buttonsColorResource)
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
