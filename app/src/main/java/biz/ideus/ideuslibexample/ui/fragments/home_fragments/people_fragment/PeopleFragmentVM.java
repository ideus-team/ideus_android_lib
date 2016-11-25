package biz.ideus.ideuslibexample.ui.fragments.home_fragments.people_fragment;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragmentVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;

    @Inject
    public PeopleFragmentVM(@AppContext Context context) {
        this.context = context;
    }
}
