package biz.ideus.ideuslibexample.ui.activities;

import android.os.Bundle;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.databinding.ActivityTutorialBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.view_models.TutorialActivityVM;

/**
 * Created by blackmamba on 18.11.16.
 */

public class TutorialActivity extends BaseActivity<ActivityTutorialBinding, TutorialActivityVM> implements MvvmView {
    @Inject
    TutorialPagerAdapter adapter;

    public TutorialPagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_tutorial, savedInstanceState);
        viewModel.setAdapter(adapter);
        binding.viewFlipper.setAdapter(adapter);



    }
}
