package biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerFragment;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 23.11.16.
 */

@PerFragment
public class TutorialFragmentVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;
    private TutorialPagerAdapter adapter;

    @Inject
    public TutorialFragmentVM(@AppContext Context context) {
        this.context = context;
    }

}
