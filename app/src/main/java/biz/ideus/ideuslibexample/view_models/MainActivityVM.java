package biz.ideus.ideuslibexample.view_models;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 24.11.16.
 */
@PerActivity
public class MainActivityVM extends BaseViewModel<BaseMvvmInterface.View> implements BaseMvvmInterface {
    private Context context;
    @Inject
    public MainActivityVM(@AppContext Context context){
        this.context = context;
    }
}
