package biz.ideus.ideuslib.temp.activities;

/**
 * Created by blackmamba on 10.11.16.
 */

/*
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;


import java.io.IOException;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;


import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.view_models.LoginActivityVM;

import static com.testfairy.l.R;

public class LoginActivity extends DLibBindingActivity<ActivityLoginBinding> implements GoogleApiClient.OnConnectionFailedListener {
    private LoginActivityVM loginActivityVM;

    private CallbackManager faceBookCallbackManager;

    public CallbackManager getFaceBookCallbackManager() {
        return faceBookCallbackManager;
    }

    public TwitterAuthClient getTwitterAuthClient() {
        return twitterAuthClient;
    }

    public GoogleSignInOptions getGoogleSignInOptions() {
        return googleSignInOptions;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

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
        loginActivityVM = new LoginActivityVM(this);
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

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    Uri resultPick = CropImage.getPickImageResultUri(this, data);
                    if (resultPick != null) {
                        CropImage.activity(resultPick)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(this);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    Uri resultUri = result.getUri();
                    binding.imageView.setImageBitmap(useImage(resultUri));
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    break;
            }
        }


    }


    private Bitmap useImage(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
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