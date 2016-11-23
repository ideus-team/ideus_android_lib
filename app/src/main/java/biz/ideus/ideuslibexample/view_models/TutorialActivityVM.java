package biz.ideus.ideuslibexample.view_models;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.interfaces.TutorialPagerActivityListener;

/**
 * Created by blackmamba on 23.11.16.
 */
@PerActivity
public class TutorialActivityVM extends BaseViewModel<BaseMvvmInterface.View> implements BaseMvvmInterface.TutorialVmListener, TutorialPagerActivityListener {
    private Context context;
    private TutorialPagerAdapter adapter;

    //    @Bindable
//    public final ObservableField<Integer> image = new ObservableField<>();
    @Bindable
    public final ObservableField<String> title = new ObservableField<>();
    @Bindable
    public final ObservableField<Boolean> isBtnSelectorDefoult = new ObservableField<>();

    public void setAdapter(TutorialPagerAdapter adapter) {
        this.adapter = adapter;
        adapter.setTutorialPagerActivityListener(this);
    }

    @Inject
    public TutorialActivityVM(@AppContext Context context) {
        this.context = context;
        adapter.setTutorialPagerActivityListener(this);
        isBtnSelectorDefoult.set(true);

    }

    @Override
    public void onSkipAllClick(View view) {

    }

    @Override
    public void onGetStartedClick(View view) {

    }

    public void onPageChanged(int position) {

    }

    @Override
    public void changeIndicator(int position) {

    }

    @Override
    public void changeSelectorBtn(int position) {
        if (position == 3) {
            isBtnSelectorDefoult.set(false);
        } else {
            isBtnSelectorDefoult.set(true);
        }
    }
}
