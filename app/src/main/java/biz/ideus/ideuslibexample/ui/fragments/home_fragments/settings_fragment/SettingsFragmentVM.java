package biz.ideus.ideuslibexample.ui.fragments.home_fragments.settings_fragment;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;

    @Inject
    public SettingsFragmentVM(@AppContext Context context) {
        this.context = context;
    }
}
