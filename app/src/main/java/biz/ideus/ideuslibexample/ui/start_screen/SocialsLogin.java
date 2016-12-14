package biz.ideus.ideuslibexample.ui.start_screen;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.Arrays;

import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;

/**
 * Created by blackmamba on 13.12.16.
 */

public class SocialsLogin {
    public static final int GOOGLE_SIGN_IN = 2222;
    private SocialRegistrationListener socialRegistrationListener;
public static CallbackManager faceBookCallbackManager;

    public SocialsLogin(SocialRegistrationListener socialRegistrationListener){
        this.socialRegistrationListener = socialRegistrationListener;
        faceBookCallbackManager = CallbackManager.Factory.create();

    }

    public void signInWithGooglePlus(StartActivity activity) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(activity.getGoogleApiClient());
        activity.startActivityForResult(signInIntent, GOOGLE_SIGN_IN);


    }

    public void onClickTwitterLogin(StartActivity activity) {
        activity.getTwitterAuthClient().authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                System.out.println("TwitterSession  " + twitterSessionResult.data.getAuthToken());
                socialRegistrationListener.getTwitterToken(twitterSessionResult);
            }

            @Override
            public void failure(TwitterException e) {
                System.out.println("TwitterSession   failure");
            }
        });
    }

    public void onClickFaceBookLogin(StartActivity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(faceBookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("facebook  " + loginResult.getAccessToken().getToken());
                        socialRegistrationListener.getFacebookToken(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("facebook  " + "eada");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("facebook  " + exception.getMessage());
                    }
                });
    }


    public interface SocialRegistrationListener {
        void getTwitterToken(Result<TwitterSession> twitterSessionResult);
        void getFacebookToken(LoginResult loginResult);

    }
}
