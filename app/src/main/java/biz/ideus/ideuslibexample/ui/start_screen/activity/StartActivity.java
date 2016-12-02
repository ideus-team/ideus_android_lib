package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import javax.inject.Inject;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.dialogs.DialogFactory;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.view_models.StartActivityVM;
import biz.ideus.ideuslibexample.utils.RxBusShowDialog;
import rx.Subscription;

import static biz.ideus.ideuslibexample.ui.start_screen.view_models.AutorisationVM.GOOGLE_SIGN_IN;


/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends BaseActivity<StartView, StartActivityVM, ActivityLoginBinding>
        implements StartView, GoogleApiClient.OnConnectionFailedListener {

    private CallbackManager faceBookCallbackManager;
    private TwitterAuthClient twitterAuthClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleApiClient;
    private DialogFactory dialogFactory;
    protected Subscription rxBusSubscription;

    public CallbackManager getFaceBookCallbackManager() {
        return faceBookCallbackManager;
    }

    public TwitterAuthClient getTwitterAuthClient() {
        return twitterAuthClient;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    @Inject
    RequeryApi requeryApi;

    @Inject
    NetApi netApi;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        faceBookCallbackManager = CallbackManager.Factory.create();
        createGoogleSignInOptions();
        createGoogleApiClient();
        twitterAuthClient = new TwitterAuthClient();
        dialogFactory = new DialogFactory(this);
        rxBusSubscription = startRxBusShowDialogSubscription();
    }


    public Subscription startRxBusShowDialogSubscription() {
        return RxBusShowDialog.instanceOf().getEvents().filter(s -> s != null)
                .subscribe(dialogModel -> dialogFactory.getDialog(dialogModel));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxBusSubscription != null && !rxBusSubscription.isUnsubscribed())
            rxBusSubscription.unsubscribe();
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
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

                    getBinding().getViewModel().headerImage.set(Utils.getDrawableImage(getContentResolver(), resultUri));
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    break;
            }
        }
    }


    @Nullable
    @Override
    public Class<StartActivityVM> getViewModelClass() {
        return StartActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_login, BR.viewModel, this);
    }
}
