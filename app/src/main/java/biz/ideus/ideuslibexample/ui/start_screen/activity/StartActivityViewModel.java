package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartMvvm;

/**
 * Created by user on 18.11.2016.
 */
@PerActivity
public class StartActivityViewModel extends BaseViewModel<StartMvvm.View> implements StartMvvm.ViewModel {



    protected final Context ctx;


    @Inject
    public StartActivityViewModel(@AppContext Context context) {
        this.ctx = context.getApplicationContext();
    }

    @Override
    public void onFaceBookClick(View view) {
        navigator.get().startActivity(new Intent());
    }

    @Override
    public void onTwitterClick(View view) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
    }

    @Override
    public void onGoogleClick(View view) {

    }

    @Override
    public void onSignInClick(View view) {

    }

    @Override
    public void onSignUpClick(View view) {

    }

    @Override
    public void onShowPasswordClick(View view) {

    }

    @Override
    public void onForgotPasswordClick(View view) {

    }

    @Override
    public void onClearEmailClick(View view) {

    }

    @Override
    public void onClearPasswordClick(View view) {

    }
}
