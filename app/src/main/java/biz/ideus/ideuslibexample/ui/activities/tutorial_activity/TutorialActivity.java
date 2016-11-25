package biz.ideus.ideuslibexample.ui.activities.tutorial_activity;

import android.os.Bundle;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.databinding.ActivityTutorialBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;

/**
 * Created by blackmamba on 18.11.16.
 */

public class TutorialActivity extends BaseActivity<ActivityTutorialBinding, TutorialActivityVM> implements MvvmView {
    @Inject
    TutorialPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_tutorial, savedInstanceState);
        binding.viewFlipper.setAdapter(adapter);
        binding.viewFlipper.addOnPageChangeListener(adapter);
    }
}
