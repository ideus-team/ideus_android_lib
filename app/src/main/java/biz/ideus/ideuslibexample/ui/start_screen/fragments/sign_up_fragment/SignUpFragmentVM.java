package biz.ideus.ideuslibexample.ui.start_screen.fragments.sign_up_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.request.SignUpModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.SocialsLogin;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.BaseValidationVM;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.terms_of_service_fragment.TermsOfServiceFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.activity.TutorialActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;
import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.FACEBOOK_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.GOOGLE_PLUS_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.TWITTER_NET;
import static biz.ideus.ideuslibexample.utils.Constants.USER_ID;
import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;

/**
 * Created by blackmamba on 16.11.16.
 */

public class SignUpFragmentVM extends BaseValidationVM implements OnValidateSignUpScreen, SocialsLogin.SocialRegistrationListener, StartActivity.GoogleAutorisationListener {
    private boolean isValidName = false;
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;
    private boolean isAgreeTermOfService = false;
    private SocialsLogin socialsLogin = new SocialsLogin(this);


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        visibilityLoadingPage.set(View.GONE);
        visibilityClearEmailImage.set(View.INVISIBLE);
        visibilityClearPasswordImage.set(View.INVISIBLE);
        visibilityClearNameImage.set(View.INVISIBLE);
        isPasswordShow.set(true);
        setOnValidateField(this);


    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        ((StartActivity) context).setGoogleAutorisationListener(this);


    }

    public void onClickClearFieldImage(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel_email:
                email.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_password:
                password.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_name:
                name.set(Editable.Factory.getInstance().newEditable(""));
                break;
        }
    }

    private boolean isValidData() {
        return isValidEmail && isValidPassword && isValidName;
    }

    public void onCreateAccountClick(View view) {
        if (isCheckTermsOfService()) {
            if (isValidData()) {
                signUpUser();
            } else {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.SIGN_UP_ATTENTION);
            }
        }

    }

    private boolean isCheckTermsOfService() {
        if (isAgreeTermOfService) {
            return isAgreeTermOfService;
        } else {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.TERMS_OF_SERVICE_ATTENTION);
            return isAgreeTermOfService;
        }
    }

    public void onCheckedChangedTermOfService(View v) {
        isAgreeTermOfService = ((((CheckBox) v).isChecked()));
    }

    public void onClickGooglePlus(View view) {
        if (isCheckTermsOfService()) {
            socialsLogin.signInWithGooglePlus((StartActivity) context);
        }
    }

    public void onClickTwitterLogin(View view) {
        if (isCheckTermsOfService()) {
            socialsLogin.onClickTwitterLogin((StartActivity) context);
        }
    }

    public void onClickFaceBookLogin(View view) {
        if (isCheckTermsOfService()) {
            socialsLogin.onClickFaceBookLogin((StartActivity) context);
        }
    }

    public void onClickTermsAndPolicy(View view) {
        ((BaseActivity) context).addFragmentToBackStack(new TermsOfServiceFragment(), null, true, null);
    }

    // CheckBox change listener
    public void onCheckedChangedShowPassword(View v) {
        isPasswordShow.set((!((CheckBox) v).isChecked()));
    }

    public void onTextChangedEmail(CharSequence text, int start, int before, int count) {
        onTextChangedEmail(text);
    }

    public void onTextChangedPassword(CharSequence text, int start, int before, int count) {
        onTextChangedPassword(text);

    }

    public void onTextChangedName(CharSequence text, int start, int before, int count) {
        onTextChangedName(text);

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
        isValidFields.set(isValidEmail && isValidPassword && isValidName);
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
    public void setVisibilityImageDeleteName(int visibility) {
        visibilityClearNameImage.set(visibility);
    }

    @Override
    public void isValidName(boolean isValid) {
        isValidName = isValid;
    }

    @Override
    public void setTitleColorName(int color) {
        titleColorName.set(color);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.sign_up);
    }

    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }


    @Override
    public void getGoogleToken(String googlePlusToken) {
        autorisationSocial(googlePlusToken, GOOGLE_PLUS_NET.networkName, null);
    }


    @Override
    public void getTwitterAutorisationData(String userName, String twitterToken) {
        autorisationSocial(twitterToken, TWITTER_NET.networkName, userName);
    }

    @Override
    public void getFacebookToken(String facebookToken) {
        autorisationSocial(facebookToken, FACEBOOK_NET.networkName, null);
    }


    private void autorisationSocial(String socialToken, String socialName, @Nullable String twitterUserName) {

        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
        SocialsAutorisationModel sotialAuthModel = new SocialsAutorisationModel(socialToken, socialName);
        sotialAuthModel.setIsAgree(isAgreeTermOfService);

        if (socialName.equals(TWITTER_NET.networkName)) {
            sotialAuthModel.setTwitterUsername(twitterUserName);
        }
        netApi.autorisationSocial(sotialAuthModel)
                .lift(new CheckError<>())
                .map(autorisationAnswer -> {
                     requeryApi.storeAutorisationInfo(autorisationAnswer.data);
                    return autorisationAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(AutorisationAnswer autorisationAnswer) {
                       Hawk.put(USER_TOKEN, autorisationAnswer.data.getApi_token());
                        Hawk.put(USER_ID, autorisationAnswer.data.getIdent());
                        goToTutorialScreen();
                    }
                });
    }


    private void signUpUser() {

        SignUpModel signUpModel = new SignUpModel(email.get().toString(), password.get().toString(), name.get().toString());
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);

        netApi.signUp(signUpModel)
                .lift(new CheckError<>())
                .map(autorisationAnswer -> {
                    requeryApi.storeAutorisationInfo(autorisationAnswer.data);
                    return autorisationAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(AutorisationAnswer autorisationAnswer) {
                        Hawk.put(USER_TOKEN, autorisationAnswer.data.getApi_token());
                        Hawk.put(USER_ID, autorisationAnswer.data.getIdent());
                        goToTutorialScreen();
                    }
                });
    }

    protected void goToTutorialScreen() {
        StartActivity startActivity = (StartActivity) context;
        startActivity.startActivity(new Intent(startActivity, TutorialActivity.class));
        startActivity.finish();
    }

}
