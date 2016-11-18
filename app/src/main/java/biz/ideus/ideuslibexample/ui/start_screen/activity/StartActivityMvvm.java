package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartMvvm;

/**
 * Created by user on 18.11.2016.
 */
@PerActivity
public class StartActivityMvvm extends BaseViewModel<StartMvvm.View> implements StartMvvm.ViewModel {

    protected final Context ctx;

    @Inject
    public StartActivityMvvm(@AppContext Context context) {
        this.ctx = context.getApplicationContext();
    }



    @Override
    public void onFaceBookClick(StartMvvm.View view) {

    }

    @Override
    public void onTwitterClick(StartMvvm.View view) {

    }

    @Override
    public void onGoogleClick(StartMvvm.View view) {

    }

    @Override
    public void onSignInClick(StartMvvm.View view) {

    }

    @Override
    public void onSignUpClick(StartMvvm.View view) {

    }

    @Override
    public void onShowPasswordClick(StartMvvm.View view) {

    }

    @Override
    public void onForgotPasswordClick(StartMvvm.View view) {

    }

    @Override
    public void onClearEmailClick(StartMvvm.View view) {

    }

    @Override
    public void onClearPasswordClick(StartMvvm.View view) {

    }


}
