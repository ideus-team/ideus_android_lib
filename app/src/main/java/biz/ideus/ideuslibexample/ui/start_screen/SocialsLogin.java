package biz.ideus.ideuslibexample.ui.start_screen;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.AccountPicker;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.Arrays;

import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;

import static biz.ideus.ideuslibexample.utils.Constants.GOOGLE_SIGN_IN;

/**
 * Created by blackmamba on 13.12.16.
 */

public class SocialsLogin {

    private SocialRegistrationListener socialRegistrationListener;
public static CallbackManager faceBookCallbackManager;

    public SocialsLogin(SocialRegistrationListener socialRegistrationListener){
        this.socialRegistrationListener = socialRegistrationListener;
        faceBookCallbackManager = CallbackManager.Factory.create();

    }

    public void signInWithGooglePlus(StartActivity activity) {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        activity.startActivityForResult(intent, GOOGLE_SIGN_IN);
    }

    public void onClickTwitterLogin(StartActivity activity) {
        activity.getTwitterAuthClient().authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                socialRegistrationListener.getTwitterAutorisationData(twitterSessionResult.data.getUserName()
                        , twitterSessionResult.data.getAuthToken().token);
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
                        socialRegistrationListener.getFacebookToken(loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });
    }


    public interface SocialRegistrationListener {
        void getTwitterAutorisationData(String userName, String twitterToken);
        void getFacebookToken(String facebookToken);

    }
}
