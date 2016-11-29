package biz.ideus.ideuslibexample.ui.start_screen;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;

/**
 * Created by user on 28.11.2016.
 */

public class TestStartBindingViewModel extends AbstractViewModel<StartView> {

    public final ObservableField<String> text = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        Log.d("ACTIVITY", "TestStartBindingViewModel");
    }

    public void onFaceBookClick(View view) {
        onClickFaceBookLogin((StartActivity) getView().getViewModelBindingConfig().getContext());
    }


    public void onTwitterClick(View view) {
        Toast.makeText(view.getContext(), "Twitter", Toast.LENGTH_LONG).show();
    }


    public void onGoogleClick(View view) {

    }


    public void onSignInClick(View view) {

    }


    public void onSignUpClick(View view) {

    }


    public void onShowPasswordClick(View view) {

    }


    public void onForgotPasswordClick(View view) {

    }


    public void onClearEmailClick(View view) {

    }


    public void onClearPasswordClick(View view) {

    }

        protected void onClickFaceBookLogin(StartActivity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(activity.getFaceBookCallbackManager(),
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("facebook  " + loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
 }

}
