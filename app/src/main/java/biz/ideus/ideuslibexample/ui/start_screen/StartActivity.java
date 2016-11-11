package biz.ideus.ideuslibexample.ui.start_screen;

import android.os.Bundle;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityStartBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.base.viewmodel.NoOpViewModel;

/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends BaseActivity<ActivityStartBinding, NoOpViewModel> implements MvvmView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_start, savedInstanceState);

        //setSupportActionBar(binding.toolbar);

        //binding.viewPager.setAdapter(adapter);
        //binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}
