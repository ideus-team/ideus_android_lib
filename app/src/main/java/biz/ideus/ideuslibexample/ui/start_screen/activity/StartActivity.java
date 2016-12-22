package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import javax.inject.Inject;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.rx_buses.RxBusActionEditDialogBtn;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.COPY_TEXT;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.DELETE;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.DETAILS;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.EDIT;
import static biz.ideus.ideuslibexample.ui.start_screen.SocialsLogin.faceBookCallbackManager;
import static biz.ideus.ideuslibexample.utils.Constants.GOOGLE_SIGN_IN;


/**
 * Created by user on 11.11.2016.
 */

public class StartActivity extends BaseActivity<StartView, StartActivityVM, ActivityLoginBinding> implements StartView {

    private TwitterAuthClient twitterAuthClient;
    protected Subscription RxBusActionEditDialogBtnSubscription;
    private GoogleAutorisationListener googleAutorisationListener;

    public void setGoogleAutorisationListener(GoogleAutorisationListener googleAutorisationListener) {
        this.googleAutorisationListener = googleAutorisationListener;
    }

    public TwitterAuthClient getTwitterAuthClient() {
        return twitterAuthClient;
    }

    @Inject
    RequeryApi requeryApi;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        twitterAuthClient = new TwitterAuthClient();
        RxBusActionEditDialogBtnSubscription = startRxBusActionEditDialogBtnSubscription();



    }



    public Subscription startRxBusActionEditDialogBtnSubscription() {
        return RxBusActionEditDialogBtn.instanceOf().getEvents()
                .subscribe(dialogCommand -> {
                    switch (dialogCommand.getDialogCommandModel()) {
                        case COPY_TEXT:
                            Log.d("dialogCommand", COPY_TEXT.name());
                            break;
                        case EDIT:
                            Log.d("dialogCommand", EDIT.name());
                            break;
                        case DETAILS:
                            Log.d("dialogCommand", DETAILS.name());
                            break;
                        case DELETE:
                            Log.d("dialogCommand", DELETE.name());
                            break;
                    }
                });
    }

    private void getTokenGooglePlus(String accName) {
        Observable.just("").map(token -> {
            try {
                token = GoogleAuthUtil.getToken(this, accName,
                        Constants.GOOGLE_SCOPES);
            } catch (Exception transientEx) {
                Log.e("google", "Google token filed!");
            }
            return token;
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(token -> {
                    if (googleAutorisationListener != null)
                        googleAutorisationListener.getGoogleToken(token);
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == GOOGLE_SIGN_IN && resultCode == RESULT_OK) {
            getTokenGooglePlus(intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME));
        } else {
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (RxBusActionEditDialogBtnSubscription != null && !RxBusActionEditDialogBtnSubscription.isUnsubscribed())
            RxBusActionEditDialogBtnSubscription.unsubscribe();

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

    public interface GoogleAutorisationListener {
        void getGoogleToken(String googlePlusToken);
    }
}
