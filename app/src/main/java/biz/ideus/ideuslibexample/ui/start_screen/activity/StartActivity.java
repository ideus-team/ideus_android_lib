package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.CallbackManager;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBaseSampleBindingActivity;
import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.databinding.ActivityStartBinding;
import biz.ideus.ideuslibexample.injection.components.ActivityComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerActivityComponent;
import biz.ideus.ideuslibexample.injection.modules.ActivityModule;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.TestStartBindingViewModel;


/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends ViewModelBaseSampleBindingActivity<StartView, TestStartBindingViewModel, ActivityStartBinding>
    implements StartView {

    //extends ViewModelBaseBindingFragment<ISampleBindingView, SampleBindingViewModel, FragmentSampleBindingBinding>
//        implements ISampleBindingView {


        //BaseActivity<ActivityStartBinding, StartMvvm.ViewModel> implements StartMvvm.View {

    private CallbackManager faceBookCallbackManager;
    public CallbackManager getFaceBookCallbackManager() {
        return faceBookCallbackManager;
    }
    private ActivityComponent mActivityComponent;

    @Inject
    RequeryApi requeryApi;

    @Inject
    NetApi netApi;

    protected final ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(SampleApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return mActivityComponent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setModelView(this);
        faceBookCallbackManager = CallbackManager.Factory.create();
        //setAndBindContentView(R.layout.activity_start, savedInstanceState);

        //setSupportActionBar(binding.toolbar);

        //binding.viewPager.setAdapter(adapter);
        //binding.tabLayout.setupWithViewPager(binding.viewPager);
     //   requeryApi.getFavoriteChangeObservable().subscribe(a -> Log.d("str", "" + a.length()));
      //  netApi.getSpecializations().subscribe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        faceBookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public Class<TestStartBindingViewModel> getViewModelClass() {
        return TestStartBindingViewModel.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_start, BR.viewModel, this);
    }
}
