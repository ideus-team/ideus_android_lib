package biz.ideus.ideuslibexample.view_models;

import android.content.Intent;
import android.view.View;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.Arrays;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.activities.SignUpActivity;

import static biz.ideus.ideuslibexample.SampleApplication.faceBookCallbackManager;
import static biz.ideus.ideuslibexample.SampleApplication.twitterAuthClient;

/**
 * Created by blackmamba on 11.11.16.
 */

public class LoginActivityVM extends ViewModel {
    private DLibBindingActivity activity;

    public LoginActivityVM(DLibBindingActivity activity) {
        this.activity = activity;
    }

    public void onClickSignUp(View view){
        goToSignUp();
    }

    private void goToSignUp(){
        activity.startActivity(new Intent(activity, SignUpActivity.class));
    }

    public void onClickTwitterLogin(View view){
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

    public void onClickFaceBookLogin(View view){
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
            LoginManager.getInstance().registerCallback(faceBookCallbackManager,
                    new FacebookCallback<LoginResult>()
                    {
                        @Override
                        public void onSuccess(LoginResult loginResult)
                        {
                            System.out.println("facebook  "+loginResult.getAccessToken().getToken());
                        }

                        @Override
                        public void onCancel()
                        {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception)
                        {
                            // App code
                        }
                    });
        }

}
