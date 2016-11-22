package biz.ideus.ideuslibexample.view_models;

import android.content.Context;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.ui.start_screen.StartMvvm;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.utils.UtilsValidation;

/**
 * Created by blackmamba on 16.11.16.
 */

public abstract class AutorisationVM extends BaseViewModel<StartMvvm.View> {

    private CallbackManager faceBookCallbackManager;
    private TwitterAuthClient twitterAuthClient;
    private GoogleApiClient googleApiClient;
    protected UtilsValidation utilsValidation;
    public static final int GOOGLE_SIGN_IN = 2222;

    public UtilsValidation getUtilsValidation() {
        return utilsValidation;
    }

    public void setUtilsValidation(Context context) {
        this.utilsValidation = new UtilsValidation(context);
    }

    public AutorisationVM(Context context){
        this.utilsValidation = new UtilsValidation(context);
    }




    protected void signInWithGooglePlus(StartActivity activity) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(activity.getGoogleApiClient());
        activity.startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    protected void onClickTwitterLogin(StartActivity activity) {
        activity.getTwitterAuthClient().authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                System.out.println("TwitterSession  " + twitterSessionResult.data.getUserName());
            }

            @Override
            public void failure(TwitterException e) {
                System.out.println("TwitterSession   failure");
            }
        });

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
