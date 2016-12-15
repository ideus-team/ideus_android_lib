package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.theartofdev.edmodo.cropper.CropImage;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterSession;

import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.LoginAnswer;
import biz.ideus.ideuslibexample.data.model.response.SocialsAutorisationAnswer;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.start_screen.SocialsLogin;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.forgot_password_fragment.ForgotPasswordFragment;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.sign_up_fragment.SignUpFragment;
import hugo.weaving.DebugLog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.data.model.SocialNetworks.FACEBOOK_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.GOOGLE_PLUS_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.TWITTER_NET;


/**
 * Created by user on 28.11.2016.
 */

public class StartActivityVM extends BaseValidationVM implements BaseMvvmInterface.StartActivityVmListener
        , OnValidateField, SocialsLogin.SocialRegistrationListener, StartActivity.GoogleAutorisationListener {
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;
    private SocialsLogin socialsLogin = new SocialsLogin(this);
    public final ObservableField<Drawable> headerImage = new ObservableField<>();


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        visibilityClearEmailImage.set(View.INVISIBLE);
        visibilityClearPasswordImage.set(View.INVISIBLE);
        isPasswordShow.set(true);
        setOnValidateField(this);
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        ((StartActivity) context).setGoogleAutorisationListener(this);
    }

    @DebugLog
    public void onTestClick(View view) {
         RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.NO_INTERNET_CONNECTION);
//        LoginModel loginModel = new LoginModel(email.get().toString(), password.get().toString());
//
//        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
//
//        netApi.login(loginModel)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new NetSubscriber<LoginAnswer>(netSubscriberSettings){
//            @Override
//            public void onNext(LoginAnswer loginAnswer) {
//                super.onNext(loginAnswer);
//            }
//        });

    }

    private boolean isValidFields() {
        return isValidEmail && isValidPassword;

    }

    public void onClickSelectPhoto(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((StartActivity) context);
        ((StartActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }


    @Override
    public void onFaceBookClick(View view) {
        socialsLogin.onClickFaceBookLogin((StartActivity) context);
    }

    @Override
    public void onTwitterClick(View view) {
        socialsLogin.onClickTwitterLogin((StartActivity) context);
    }

    @Override
    public void onGoogleClick(View view) {
        socialsLogin.signInWithGooglePlus((StartActivity) context);

    }

    @Override
    public void onSignInClick(View view) {
        if (isValidFields()) {
            loginUser();
        } else {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
        }

    }

    private void loginUser() {
        LoginModel loginModel = new LoginModel(email.get().toString(), password.get().toString());
        netApi.login(loginModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginAnswer>() {
            @Override
            public void onStart() {
                super.onStart();
                showProgress();
//                showLoadingPage(context.getString(R.string.logging_to_app_temp)
//                        , context.getString(R.string.about_logining_account)
//                        , View.VISIBLE);
            }

            @Override
            public void onCompleted() {
               // hideProgress();
               // hideLoadingPage(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
               // hideLoadingPage(View.GONE);
               // RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);


            }

            @Override
            public void onNext(LoginAnswer loginAnswer) {
                if(!loginAnswer.message.isEmpty()){
                    hideProgress();
                    RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
                }else {

                }

            }
        });
    }

    protected void goToMainScreen(){
        StartActivity startActivity = (StartActivity) context;
        startActivity.startActivity(new Intent(startActivity, MainActivity.class));
        startActivity.finish();
    }




    @Override
    public void onSignUpClick(View view) {

        ((BaseActivity) context)
                .addFragmentToBackStack(new SignUpFragment(), null, true, null);
    }

    @Override
    public void onShowPasswordClick(View view) {
        isPasswordShow.set((!((CheckBox) view).isChecked()));
    }

    @Override
    public void onForgotPasswordClick(View view) {
        ((BaseActivity) context)
                .addFragmentToBackStack(new ForgotPasswordFragment(), null, true, null);
    }

    @Override
    public void onClearEmailClick(View view) {
        email.set(Editable.Factory.getInstance().newEditable(""));
    }

    @Override
    public void onClearPasswordClick(View view) {
        password.set(Editable.Factory.getInstance().newEditable(""));
    }


    public void onTextChangedEmail(CharSequence text, int start, int before, int count) {
        onTextChangedEmail(text);
    }

    public void onTextChangedPassword(CharSequence text, int start, int before, int count) {
        onTextChangedPassword(text);
    }

    @Override
    public void setVisibilityImageDeleteEmail(int visibility) {
        visibilityClearEmailImage.set(visibility);
    }

    @Override
    public void setVisibilityImageDeletePassword(int visibility) {
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

    @Override
    public String getToolbarTitle() {
        return null;
    }


    @Override
    public void getGoogleToken(GoogleSignInAccount googleAcc) {
        autorisationSocial(googleAcc.getIdToken(), GOOGLE_PLUS_NET.networkName);
    }

    @Override
    public void getTwitterToken(Result<TwitterSession> twitterSessionResult) {
        autorisationSocial(twitterSessionResult.data.getAuthToken().token, TWITTER_NET.networkName);
    }

    @Override
    public void getFacebookToken(LoginResult loginResult) {
        autorisationSocial(loginResult.getAccessToken().getToken(), FACEBOOK_NET.networkName);
    }


    private void autorisationSocial(String socialToken, String socialName) {
        SocialsAutorisationModel sotialAuthModel = new SocialsAutorisationModel(socialToken, socialName);
        netApi.autorisationSocial(sotialAuthModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SocialsAutorisationAnswer>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        showProgress();
//                showLoadingPage(context.getString(R.string.logging_to_app_temp)
//                        , context.getString(R.string.about_logining_account)
//                        , View.VISIBLE);
                    }

                    @Override
                    public void onCompleted() {
                        hideProgress();
                        // hideLoadingPage(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
                        // hideLoadingPage(View.GONE);
                        // RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);


                    }

                    @Override
                    public void onNext(SocialsAutorisationAnswer socialsAutorisationAnswer) {
                        if(!socialsAutorisationAnswer.message.isEmpty()){
                            hideProgress();
                            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
                        }else {

                        }

                    }
                });
    }
}
