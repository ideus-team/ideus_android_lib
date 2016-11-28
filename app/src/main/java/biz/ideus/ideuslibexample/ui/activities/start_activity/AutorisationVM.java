package biz.ideus.ideuslibexample.ui.activities.start_activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.text.Editable;
import android.view.View;

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

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.CustomAttentionDialog;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.utils.UtilsValidation;

/**
 * Created by blackmamba on 16.11.16.
 */

public abstract class AutorisationVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;
    protected UtilsValidation utilsValidation;
    public static final int GOOGLE_SIGN_IN = 2222;

    @Bindable
    public final ObservableField<Editable> name = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> titleColorName = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> visibilityClearNameImage = new ObservableField<>();
    @Bindable
    public final ObservableField<Boolean> isTermAndPolicy = new ObservableField<>();
    @Bindable
    public final ObservableField<Editable> email = new ObservableField<>();
    @Bindable
    public final ObservableField<Editable> password = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> titleColorPassword = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> visibilityClearEmailImage = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> visibilityClearPasswordImage = new ObservableField<>();
    @Bindable
    public final ObservableField<Boolean> isPasswordShow = new ObservableField<>();
    @Bindable
    public final ObservableField<String> titleAutorisationScreen = new ObservableField<>();
    @Bindable
    public final ObservableField<String> aboutTitleAutorisationScreen = new ObservableField<>();
    @Bindable
    public final ObservableField<Integer> visibilityLoadingPage = new ObservableField<>();
    @Bindable
    public final ObservableField<Boolean> isValidFields = new ObservableField<>();

    public AutorisationVM(Context context) {
        this.context = context;
        this.utilsValidation = new UtilsValidation(context);
        visibilityLoadingPage.set(View.GONE);
        isValidFields.set(false);
    }

    public void showLoadingPage(String title, String aboutTilte, int visibility) {
        titleAutorisationScreen.set(title);
        aboutTitleAutorisationScreen.set(aboutTilte);
        visibilityLoadingPage.set(visibility);
    }


    public void showAttentionDialog(BaseActivity activity, String dialogMessage) {
        CustomAttentionDialog dialog = new CustomAttentionDialog(activity);
        dialog.setOnBtnDialogClickListener(view1 -> dialog.hide())
                .setOnCloseClickListener(view12 -> dialog.hide())
                .setAboutActionText(dialogMessage)
                .setVisbilityStatusImage(View.VISIBLE)
                .setColorTitle(Color.RED)
                .setBtnName(context.getString(R.string.try_again))
                .setTitle(context.getString(R.string.error))
                .show();
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
