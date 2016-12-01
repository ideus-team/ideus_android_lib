package biz.ideus.ideuslibexample.ui.start_screen.view_models;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */

public abstract class AutorisationVM extends AbstractViewModel<StartView> {
    protected Context context;
    public static final int GOOGLE_SIGN_IN = 2222;
    public static final int MIN_COUNT_CHARACTER_NAME = 3;
    public static final int MIN_COUNT_CHARACTER_PASSWORD = 6;
    private OnValidateField onValidateField;

    public void setOnValidateField(OnValidateField onValidateField) {
        this.onValidateField = onValidateField;
    }

    public final ObservableField<CharSequence> name = new ObservableField<>();
    public final ObservableField<CharSequence> email = new ObservableField<>();

    public final ObservableField<CharSequence> password = new ObservableField<>();

    public final ObservableField<Integer> titleColorName = new ObservableField<>();

    public final ObservableField<Integer> visibilityClearNameImage = new ObservableField<>();

    public final ObservableField<Boolean> isTermAndPolicy = new ObservableField<>();

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
        visibilityLoadingPage.set(View.GONE);
        isValidFields.set(false);




    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = getView().getViewModelBindingConfig().getContext();

    }

    public void onTextChangedEmail(CharSequence text) {
        email.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImageEmail(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateEmail(currentText, Constants.EMAIL_PATTERN)))
                .map(isValid -> {
                    onValidateField.setTitleColorEmail(getColor(isValid, context));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidEmail(aBoolean);
                    onValidateField.setValidAutorisationBtn();
                });
    }

    public void onTextChangedPassword(CharSequence text) {
        password.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImagePassword(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validatePassword(currentText, MIN_COUNT_CHARACTER_PASSWORD)))
                .map(isValid -> {
                    onValidateField.setTitleColorPassword(getColor(isValid, context));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidPassword(aBoolean);
                    onValidateField.setValidAutorisationBtn();
                });
    }

    public void onTextChangedName(CharSequence text) {
        name.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    ((OnValidateSignUpScreen) onValidateField).setVisibilityImageName(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateName(currentText, MIN_COUNT_CHARACTER_NAME)))
                .map(isValid -> {
                    ((OnValidateSignUpScreen) onValidateField).setTitleColorName(getColor(isValid, context));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    ((OnValidateSignUpScreen) onValidateField).isValidName(aBoolean);
                    onValidateField.setValidAutorisationBtn();
                });

    }

    private int getColor(Boolean isValid, Context context) {
        return (isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color);
    }

    private int getVisibility(String currentText) {
        return (!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE;
    }


    public void showLoadingPage(String title, String aboutTitle, int visibility) {
        titleAutorisationScreen.set(title);
        aboutTitleAutorisationScreen.set(aboutTitle);
        visibilityLoadingPage.set(visibility);
    }

    @Override
    public void onStop() {
        super.onStop();

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
