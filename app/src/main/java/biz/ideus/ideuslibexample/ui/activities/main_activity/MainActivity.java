package biz.ideus.ideuslibexample.ui.activities.main_activity;

import android.os.Bundle;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.MainPagerAdapter;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;

/**
 * Created by blackmamba on 24.11.16.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityVM> implements BaseMvvmInterface.View {
    @Inject
    MainPagerAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_main, savedInstanceState);
        binding.viewPager.setAdapter(mainAdapter);
        binding.viewPager.addOnPageChangeListener(mainAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            binding.tabLayout.getTabAt(i).setCustomView(mainAdapter.getTabResource(i));
        }
    }

    @Override
    public String getToolbarName() {
        return null;
    }
}

