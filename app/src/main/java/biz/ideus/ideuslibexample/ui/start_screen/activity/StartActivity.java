package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.os.Bundle;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityStartBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartMvvm;

/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends BaseActivity<ActivityStartBinding, StartMvvm.ViewModel> implements StartMvvm.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_start, savedInstanceState);

        //setSupportActionBar(binding.toolbar);

        //binding.viewPager.setAdapter(adapter);
        //binding.tabLayout.setupWithViewPager(binding.viewPager);
    }


}
