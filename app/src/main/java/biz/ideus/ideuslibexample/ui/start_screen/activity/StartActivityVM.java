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

import com.orhanobut.hawk.Hawk;
import com.theartofdev.edmodo.cropper.CropImage;

import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.data.model.SocialNetworks.FACEBOOK_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.GOOGLE_PLUS_NET;
import static biz.ideus.ideuslibexample.data.model.SocialNetworks.TWITTER_NET;
import static biz.ideus.ideuslibexample.utils.Constants.USER_ID;
import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;


/**
 * Created by user on 28.11.2016.
 */

public class StartActivityVM extends BaseValidationVM implements BaseMvvmInterface.StartActivityVmListener
        , OnValidateField, SocialsLogin.SocialRegistrationListener, StartActivity.GoogleAutorisationListener {
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;
    private SocialsLogin socialsLogin = new SocialsLogin(this);
    public final ObservableField<Drawable> headerImage = new ObservableField<>();
 //   protected Subscription testSubscription;
    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        visibilityClearEmailImage.set(View.INVISIBLE);
        visibilityClearPasswordImage.set(View.INVISIBLE);
        isPasswordShow.set(true);
        setOnValidateField(this);

//        testSubscription = chatEventSubscribe();


    }


    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        ((StartActivity) context).setGoogleAutorisationListener(this);
    }

    @DebugLog
    public void onTestClick(View view) {
     //   SampleApplication.getInstance().getWebSocket().sendText("wwwwwwww");

//
//        LoginModel loginModel = new LoginModel(email.get().toString(), password.get().toString());
//        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
//
//        netApi.login(loginModel)
//                .lift(new CheckError<>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new NetSubscriber<LoginAnswer>(netSubscriberSettings){
//            @Override
//            public void onNext(LoginAnswer loginAnswer) {
//                Hawk.put(USER_TOKEN, loginAnswer.data.getUserToken());
//                Hawk.put(USER_ID, loginAnswer.data.getUserId());
//
//
//                Log.d("loginAnswer", Hawk.get(USER_TOKEN));
//                Log.d("loginAnswer", Hawk.get(USER_ID));
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
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);

        netApi.login(loginModel)
                .lift(new CheckError<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings){
                    @Override
                    public void onNext(AutorisationAnswer loginAnswer) {
                        Hawk.put(USER_TOKEN, loginAnswer.data.getUserToken());
                        Hawk.put(USER_ID, loginAnswer.data.getUserId());
                        goToMainScreen();
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

        SocialsAutorisationModel sotialAuthModel = new SocialsAutorisationModel(socialToken, socialName);
        if(socialName.equals(TWITTER_NET.networkName)){
            sotialAuthModel.setTwitterUsername(twitterUserName);
        }
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);

        netApi.autorisationSocial(sotialAuthModel)
                .lift(new CheckError<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(AutorisationAnswer autorisationAnswer) {
                        Hawk.put(USER_TOKEN, autorisationAnswer.data.getUserToken());
                        Hawk.put(USER_ID, autorisationAnswer.data.getUserId());
                        goToMainScreen();
                    }
                });
    }


//    private Subscription chatEventSubscribe(){
//
//        return RxChatMessageEvent.instanceOf().getEvents()
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String message) {
//                        email.set(message);
//                    }
//                });
//
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (testSubscription != null && !testSubscription.isUnsubscribed())
//            testSubscription.unsubscribe();
//    }


}
