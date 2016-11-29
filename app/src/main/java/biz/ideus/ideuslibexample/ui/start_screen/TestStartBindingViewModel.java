package biz.ideus.ideuslibexample.ui.start_screen;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.Arrays;

import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.utils.UtilsValidation;

/**
 * Created by user on 28.11.2016.
 */

public class TestStartBindingViewModel extends AbstractViewModel<StartView> implements BaseMvvmInterface.StartActivityVmListener, OnValidateField {
    private boolean isValidEmail = false;
       private boolean isValidPassword = false;
    protected UtilsValidation utilsValidation;
       public static final int GOOGLE_SIGN_IN = 2222;
    public final ObservableField<String> text = new ObservableField<>();


    public final ObservableField<Editable> name = new ObservableField<>();

    public final ObservableField<Integer> titleColorName = new ObservableField<>();

    public final ObservableField<Integer> visibilityClearNameImage = new ObservableField<>();

    public final ObservableField<Boolean> isTermAndPolicy = new ObservableField<>();

    public final ObservableField<Editable> email = new ObservableField<>();

    public final ObservableField<Editable> password = new ObservableField<>();

    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();

    public final ObservableField<Integer> titleColorPassword = new ObservableField<>();

    public final ObservableField<Integer> visibilityClearEmailImage = new ObservableField<>();

    public final ObservableField<Integer> visibilityClearPasswordImage = new ObservableField<>();

    public final ObservableField<Boolean> isPasswordShow = new ObservableField<>();

    public final ObservableField<String> titleAutorisationScreen = new ObservableField<>();

    public final ObservableField<String> aboutTitleAutorisationScreen = new ObservableField<>();

    public final ObservableField<Integer> visibilityLoadingPage = new ObservableField<>();

    public final ObservableField<Boolean> isValidFields = new ObservableField<>();


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        Log.d("ACTIVITY", "TestStartBindingViewModel");
//        Log.d("www",""+getView().getViewModelBindingConfig().getContext());
//      //  utilsValidation = new UtilsValidation(context);
//        visibilityLoadingPage.set(View.GONE);
//        isValidFields.set(false);
//        visibilityClearEmailImage.set(View.INVISIBLE);
//        visibilityClearPasswordImage.set(View.INVISIBLE);
//        isPasswordShow.set(true);
       // utilsValidation.setOnValidateField(this);
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
          utilsValidation = new UtilsValidation(view.getViewModelBindingConfig().getContext());
        visibilityLoadingPage.set(View.GONE);
        isValidFields.set(false);
        visibilityClearEmailImage.set(View.INVISIBLE);
        visibilityClearPasswordImage.set(View.INVISIBLE);
        isPasswordShow.set(true);
        utilsValidation.setOnValidateField(this);
    }

    private boolean isValidData(View view) {
        if (!(isValidEmail && isValidPassword)) {
           // showAttentionDialog((StartActivity)view.getContext(), ctx.getString(R.string.invalidate_login_text));
        }
        return isValidEmail && isValidPassword;

    }

    public void onClickSelectPhoto(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((StartActivity) view.getContext());
      //  navigator.get().startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }


    @Override
    public void onFaceBookClick(View view) {
        onClickFaceBookLogin((StartActivity) view.getContext());
    }

    @Override
    public void onTwitterClick(View view) {
        onClickTwitterLogin((StartActivity) view.getContext());
    }

    @Override
    public void onGoogleClick(View view) {
        signInWithGooglePlus((StartActivity) view.getContext());
    }

    @Override
    public void onSignInClick(View view) {
        if (isValidData(view)) {


        }
       // showLoadingPage(ctx.getString(R.string.logging_to_app_temp), ctx.getString(R.string.about_logining_account), View.VISIBLE);

    }

    @Override
    public void onSignUpClick(View view) {
       // navigator.get().addFragmentToBackStack(android.R.id.content, new SignUpFragment(), Constants.SIGN_UP_FRAGMENT, null, true, null);
    }

    @Override
    public void onShowPasswordClick(View view) {
        isPasswordShow.set((!((CheckBox) view).isChecked()));
    }

    @Override
    public void onForgotPasswordClick(View view) {
       // navigator.get().addFragmentToBackStack(android.R.id.content, new ForgotPasswordFragment(), Constants.SIGN_UP_FRAGMENT, null, true, null);
    }

    @Override
    public void onClearEmailClick(View view) {
        email.set(Editable.Factory.getInstance().newEditable(""));
    }

    @Override
    public void onClearPasswordClick(View view) {
        password.set(Editable.Factory.getInstance().newEditable(""));
    }


    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        utilsValidation.onTextChangedEmail(s.toString());
    }

    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
        utilsValidation.onTextChangedPassword(s.toString());
    }

    @Override
    public void setVisibilityImageEmail(int visibility) {
        visibilityClearEmailImage.set(visibility);
    }

    @Override
    public void setVisibilityImagePassword(int visibility) {
        visibilityClearPasswordImage.set(visibility);
    }

    @Override
    public void setTitleColorEmail(int color) {
        titleColorEmail.set(color);
    }

    @Override
    public void setValidAutorisationBtn() {
        isValidFields.set(isValidEmail && isValidPassword);

    }

    @Override
    public void setTitleColorPassword(int color) {
        titleColorPassword.set(color);
    }

    @Override
    public void isValidEmail(boolean isValid) {
        isValidEmail = isValid;
    }

    @Override
    public void isValidPassword(boolean isValid) {
        isValidPassword = isValid;
    }
    public void showLoadingPage(String title, String aboutTilte, int visibility) {
        titleAutorisationScreen.set(title);
        aboutTitleAutorisationScreen.set(aboutTilte);
        visibilityLoadingPage.set(visibility);
    }


//    public void showAttentionDialog(BaseActivity activity, String dialogMessage) {
//        CustomAttentionDialog dialog = new CustomAttentionDialog(activity);
//        dialog.setOnBtnDialogClickListener(view1 -> dialog.hide())
//                .setOnCloseClickListener(view12 -> dialog.hide())
//                .setAboutActionText(dialogMessage)
//                .setVisbilityStatusImage(View.VISIBLE)
//                .setColorTitle(Color.RED)
//                .setBtnName(context.getString(R.string.try_again))
//                .setTitle(context.getString(R.string.error))
//                .show();
//    }

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
