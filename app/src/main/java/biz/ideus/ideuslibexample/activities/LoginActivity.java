package biz.ideus.ideuslibexample.activities;

/**
 * Created by blackmamba on 10.11.16.
 */

/*
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.view_models.LoginActivityVM;

import static com.testfairy.l.R;

public class LoginActivity extends DLibBindingActivity<ActivityLoginBinding> implements GoogleApiClient.OnConnectionFailedListener {
    private LoginActivityVM loginActivityVM;
    private CallbackManager faceBookCallbackManager;
    private TwitterAuthClient twitterAuthClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleApiClient;
    public static final int GOOGLE_SIGN_IN = 2222;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onInit(View rootView) {
        faceBookCallbackManager = CallbackManager.Factory.create();
        createGoogleSignInOptions();
        createGoogleApiClient();
        twitterAuthClient = new TwitterAuthClient();
        loginActivityVM = new LoginActivityVM(faceBookCallbackManager
                , twitterAuthClient
                , googleApiClient, this);
        binding.setLoginVM(loginActivityVM);
        
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        faceBookCallbackManager.onActivityResult(requestCode, resultCode, data);
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    private void createGoogleSignInOptions() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

    }

    private void createGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("googleSignIn", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d("googleSignIn", "handleSignInResult:" + acct.getDisplayName());
        } else {

        }
    }
}
*/