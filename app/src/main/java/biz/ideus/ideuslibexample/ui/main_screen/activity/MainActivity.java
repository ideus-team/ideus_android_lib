package biz.ideus.ideuslibexample.ui.main_screen.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;

///**
// * Created by blackmamba on 24.11.16.
// */
//
public class MainActivity extends BaseActivity<StartView, MainActivityVM, ActivityMainBinding>
        implements StartView {







//    @Inject
//    MainPagerAdapter mainAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        binding.viewPager.setOffscreenPageLimit(Constants.MAIN_SCREEN_PAGES_COUNT);
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

