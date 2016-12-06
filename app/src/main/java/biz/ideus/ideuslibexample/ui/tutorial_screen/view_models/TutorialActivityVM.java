package biz.ideus.ideuslibexample.ui.tutorial_screen.view_models;


import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.TutorialView;
import biz.ideus.ideuslibexample.ui.tutorial_screen.activity.TutorialActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.adapters.TutorialPagerAdapter;
import rx.Observable;

/**
 * Created by blackmamba on 23.11.16.
 */
//@PerActivity
public class TutorialActivityVM extends AbstractViewModel<TutorialView> implements TutorialPagerAdapter.OnPagerListener, BaseMvvmInterface.TutorialVmListener {
    private TutorialPagerAdapter adapter;


    public void setAdapter(TutorialPagerAdapter adapter) {
        this.adapter = adapter;
        if (adapter != null)
            this.adapter.setOnPagerListener(this);
    }

    private final int ABOUT_APP = 0;
    private final int NETWORK = 1;
    private final int PROTECTION = 2;
    private final int CUSTOMISATION = 3;

    public final ObservableField<Boolean> isRadioBtnWelcomeChecked = new ObservableField<>();
    public final ObservableField<Boolean> isRadioBtnProtectionChecked = new ObservableField<>();
    public final ObservableField<Boolean> isRadioBtnNetworkChecked = new ObservableField<>();
    public final ObservableField<Boolean> isRadioBtnCustomChecked = new ObservableField<>();
    public final ObservableField<Boolean> isBtnActive = new ObservableField<>();

    private List<ObservableField<Boolean>> radioBtnCheckList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        isBtnActive.set(false);
        addItemsRadioBtnCheckList();
        checkCurrentRadioBtn(ABOUT_APP);
    }

    @Override
    public void onSkipAllClick(View view) {

    }

    @Override
    public void onGetStartedClick(View view) {
        if (isBtnActive.get()) {
            ((TutorialActivity)view.getContext()).startActivity(new Intent(((TutorialActivity) view.getContext()), MainActivity.class));
        }
    }

    private void checkCurrentRadioBtn(int positionPage) {
        Observable.from(radioBtnCheckList).map(item -> {item.set(radioBtnCheckList.indexOf(item) == positionPage);
            return item;})
                .subscribe();
    }

    private void addItemsRadioBtnCheckList() {
        radioBtnCheckList.add(isRadioBtnWelcomeChecked);
        radioBtnCheckList.add(isRadioBtnNetworkChecked);
        radioBtnCheckList.add(isRadioBtnProtectionChecked);
        radioBtnCheckList.add(isRadioBtnCustomChecked);
    }

    @Override
    public void selectPage(int position) {
        switch (position) {
            case ABOUT_APP:
                checkCurrentRadioBtn(ABOUT_APP);
                break;
            case NETWORK:
                checkCurrentRadioBtn(NETWORK);
                break;
            case PROTECTION:
                checkCurrentRadioBtn(PROTECTION);
                break;
            case CUSTOMISATION:
                checkCurrentRadioBtn(CUSTOMISATION);
                isBtnActive.set(true);
                break;
        }
    }
}
