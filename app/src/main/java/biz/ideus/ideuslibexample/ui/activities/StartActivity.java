package biz.ideus.ideuslibexample.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

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

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.interfaces.OnActivityResultInterface;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.view_models.StartActivityVM;

import static biz.ideus.ideuslibexample.view_models.AutorisationVM.GOOGLE_SIGN_IN;

/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends BaseActivity<ActivityLoginBinding, StartActivityVM> implements BaseMvvmInterface.View ,GoogleApiClient.OnConnectionFailedListener, OnActivityResultInterface {

    private CallbackManager faceBookCallbackManager;
    private TwitterAuthClient twitterAuthClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleApiClient;

    public CallbackManager getFaceBookCallbackManager() {
        return faceBookCallbackManager;
    }

    public TwitterAuthClient getTwitterAuthClient() {
        return twitterAuthClient;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        faceBookCallbackManager = CallbackManager.Factory.create();
        createGoogleSignInOptions();
        createGoogleApiClient();
        twitterAuthClient = new TwitterAuthClient();
        activityComponent().inject(this);
        setAndBindContentView(R.layout.activity_login, savedInstanceState);
        setOnActivityResultInterface(this);
        //setSupportActionBar(binding.toolbar);

        //binding.viewPager.setAdapter(adapter);
        //binding.tabLayout.setupWithViewPager(binding.viewPager);

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

    @Override
    public void onActivityResultCurrentActivity(int requestCode, int resultCode, Intent intent) {
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            handleSignInResult(result);
        }
        faceBookCallbackManager.onActivityResult(requestCode, resultCode, intent);
        twitterAuthClient.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    Uri resultPick = CropImage.getPickImageResultUri(this, intent);
                    if (resultPick != null) {
                        CropImage.activity(resultPick)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(this);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    Uri resultUri = result.getUri();

                    binding.imageViewHeader.setImageBitmap(useImage(resultUri));
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    break;
            }
        }
    }

}
