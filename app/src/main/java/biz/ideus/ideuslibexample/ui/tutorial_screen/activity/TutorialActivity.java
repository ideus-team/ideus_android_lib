package biz.ideus.ideuslibexample.ui.tutorial_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.databinding.ActivityTutorialBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.TutorialView;
import biz.ideus.ideuslibexample.ui.tutorial_screen.adapters.TutorialPagerAdapter;

/**
 * Created by blackmamba on 18.11.16.
 */

public class TutorialActivity extends BaseActivity<TutorialView, TutorialActivityVM, ActivityTutorialBinding>
        implements TutorialView{
    @Inject
   TutorialPagerAdapter adapter;

    @Inject
    RequeryApi requeryApi;

    @Inject
    NetApi netApi;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        binding.viewFlipper.setAdapter(adapter);
        binding.viewFlipper.addOnPageChangeListener(adapter);
        viewModel.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public Class<TutorialActivityVM> getViewModelClass() {
        return TutorialActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_tutorial, BR.viewModel, this);
    }
}

