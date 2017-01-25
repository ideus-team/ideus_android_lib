package biz.ideus.ideuslibexample.ui.main_screen.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.orhanobut.hawk.Hawk;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.MainFragmentPagerAdapter;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.utils.Constants;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;


public class MainActivity extends BaseActivity<StartView, MainActivityVM, ActivityMainBinding>
        implements StartView {
    private ImageChooserListener imageChooserListener;
    private BottomNavigationBar bottomNavigationBar;

    public void setImageChooserListener(ImageChooserListener imageChooserListener) {
        this.imageChooserListener = imageChooserListener;
    }



    @Inject
    MainFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        if(!Hawk.contains(USER_TOKEN)){
            goToLoginScreen();
        }

                new RelativeLayout(this, null, R.style.ButtonGreenStyle);
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



    private void goToLoginScreen(){
        startActivity(new Intent(this, StartActivity.class));
        finish();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    Uri resultPick = CropImage.getPickImageResultUri(this, intent);
                    if (resultPick != null) {
                        CropImage.activity(resultPick)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(this);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    Uri resultUri = result.getUri();
                    imageChooserListener.onChooseImage(resultUri.getPath());
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    break;
            }
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public interface ImageChooserListener {
        void onChooseImage(String imagePath);
    }
}

