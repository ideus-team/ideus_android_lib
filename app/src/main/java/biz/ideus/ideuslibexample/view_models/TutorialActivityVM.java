package biz.ideus.ideuslibexample.view_models;

import android.content.Context;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 23.11.16.
 */
@PerActivity
public class TutorialActivityVM extends BaseViewModel<BaseMvvmInterface.View> implements BaseMvvmInterface.TutorialVmListener, TutorialPagerAdapter.OnPagerListener {
    private Context context;
    private TutorialPagerAdapter adapter;
    private final int ABOUT_APP = 0;
    private final int NETWORK = 1;
    private final int PROTECTION = 2;
    private final int CUSTOMISATION = 3;

    private boolean isBtnSelectorDefault = true;
    private boolean isRadioBtnWelcomeChecked = true;
    private boolean isRadioBtnNetworkChecked;
    private boolean isRadioBtnProtectionChecked;
    private boolean isRadioBtnCustomChecked;

    @Bindable
    public boolean isRadioBtnWelcomeChecked() {
        return isRadioBtnWelcomeChecked;
    }

    @Bindable
    public boolean isRadioBtnProtectionChecked() {
        return isRadioBtnProtectionChecked;
    }

    @Bindable
    public boolean isRadioBtnNetworkChecked() {
        return isRadioBtnNetworkChecked;
    }

    @Bindable
    public boolean isRadioBtnCustomChecked() {
        return isRadioBtnCustomChecked;
    }

    @Bindable
    public boolean isBtnSelectorDefault() {
        return isBtnSelectorDefault;
    }


    @Inject
    public TutorialActivityVM(@AppContext Context context, TutorialPagerAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        this.adapter.setOnPagerListener(this);
    }


    @Override
    public void onSkipAllClick(View view) {

    }

    @Override
    public void onGetStartedClick(View view) {

    }

    @Override
    public void selectPage(int position) {
        switch (position) {
            case ABOUT_APP:
                isRadioBtnWelcomeChecked = true;
                notifyPropertyChanged(BR.radioBtnWelcomeChecked);
                break;
            case NETWORK:
                isRadioBtnNetworkChecked = true;
                notifyPropertyChanged(BR.radioBtnNetworkChecked);
                break;
            case PROTECTION:
                isRadioBtnProtectionChecked = true;
                notifyPropertyChanged(BR.radioBtnProtectionChecked);
                break;
            case CUSTOMISATION:
                isRadioBtnCustomChecked = true;
                isBtnSelectorDefault = false;
                notifyPropertyChanged(BR.radioBtnCustomChecked);
                notifyPropertyChanged(BR.btnSelectorDefault);
                break;
        }
    }
}
