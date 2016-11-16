package biz.ideus.ideuslib.view_models;

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

import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.activity.DLibBindingActivity;

import static biz.ideus.ideuslib.activity.DLibBindingActivity.GOOGLE_SIGN_IN;

/**
 * Created by blackmamba on 16.11.16.
 */

public abstract class AutorisationVM extends ViewModel {

    protected DLibBindingActivity activity;
    protected CallbackManager faceBookCallbackManager;
    protected TwitterAuthClient twitterAuthClient;
    protected GoogleApiClient googleApiClient;
    protected UtilsValidationETFields utilsValidationETFields;


    public AutorisationVM(DLibBindingActivity activity
            , CallbackManager faceBookCallbackManager
    , TwitterAuthClient twitterAuthClient
            , GoogleApiClient googleApiClient
            , UtilsValidationETFields utilsValidationETFields) {

        this.activity = activity;
        this.faceBookCallbackManager = faceBookCallbackManager;
        this.twitterAuthClient = twitterAuthClient;
        this.googleApiClient = googleApiClient;
        this.utilsValidationETFields = utilsValidationETFields;
    }

    protected void signInWithGooglePlus() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        activity.startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    protected void onClickTwitterLogin() {
        twitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
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

    protected void onClickFaceBookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(faceBookCallbackManager,
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
