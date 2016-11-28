package biz.ideus.ideuslibexample.view_models;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import biz.ideus.ideuslibexample.R;



/**
 * Created by blackmamba on 11.11.16.
 */

public class LoginActivityVM extends ViewModel {
    private Activity activity;
//    private CallbackManager faceBookCallbackManager;
//    private TwitterAuthClient twitterAuthClient;
//    private GoogleApiClient googleApiClient;
//    private UtilsValidationETFields utilsValidationETFields = new UtilsValidationETFields();

    private boolean isValidEmail = false;
    private boolean isValidPassword = false;

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


//    public LoginActivityVM(CallbackManager faceBookCallbackManager
//            , TwitterAuthClient twitterAuthClient
//            , GoogleApiClient googleApiClient
//            , Activity activity) {
//        this.activity = activity;
//        this.faceBookCallbackManager = faceBookCallbackManager;
//        this.twitterAuthClient = twitterAuthClient;
//        this.googleApiClient = googleApiClient;
//        this.visibilityClearEmailImage.set(View.INVISIBLE);
//        this.visibilityClearPasswordImage.set(View.INVISIBLE);
//        this.isPasswordShow.set(true);
//    }

    public void onClickSignUp(View view) {
        goToSignUp();
    }


    public void onClickGooglePlus(View view) {
        signInWithGooglePlus();
    }

    public void onClickTwitterLogin(View view) {
//        twitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> twitterSessionResult) {
//                System.out.println("TwitterSession  " + twitterSessionResult.data.getUserName());
//            }
//
//            @Override
//            public void failure(TwitterException e) {
//                System.out.println("TwitterSession   failure");
//            }
//        });

    }

    private void goToSignUp() {
       // activity.startActivity(new Intent(activity, SignUpActivity.class));
    }

    private void signInWithGooglePlus() {
        //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
     //   activity.startActivityForResult(signInIntent, LoginActivity.GOOGLE_SIGN_IN);
    }

    public void onClickFaceBookLogin(View view) {
//        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
//        LoginManager.getInstance().registerCallback(faceBookCallbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        System.out.println("facebook  " + loginResult.getAccessToken().getToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });
    }

    // CheckBox change listener
    public void onCheckedChanged(View v) {
        isPasswordShow.set((!((CheckBox) v).isChecked()));
    }

    private boolean isValidData() {
     //   if (!isValidEmail) {
      //      Utils.toast(activity.getString(R.string.invalid_email));
    //        return isValidEmail && isValidPassword;
  //      }
     //   if (!isValidPassword)
      //      Utils.toast(activity.getString(R.string.invalid_password));
        return isValidEmail && isValidPassword;

    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
//        Observable.just(s.toString())
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .doOnNext(text -> {
//                    visibilityClearEmailImage.set((!text.equals("")) ? View.VISIBLE : View.INVISIBLE);
//
//                })
//                .flatMap(text -> Observable.just(utilsValidationETFields.validateEmail(text)))
//
//                .map(isValid -> {
//                    titleColorEmail.set((isValid) ? ContextCompat.getColor(activity, R.color.black)
//                            : ContextCompat.getColor(activity, R.color.error_color));
//                    return isValid;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Boolean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Boolean isValid) {
//                        isValidEmail = isValid;
//                    }
//
//                });
    }

    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
//        Observable.just(s.toString())
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .doOnNext(text -> {
//                    visibilityClearPasswordImage.set((!text.equals("")) ? View.VISIBLE : View.INVISIBLE);})
//                .flatMap(text -> Observable.just(utilsValidationETFields.validatePassword(text)))
//                .map(isValid -> {
//                    titleColorPassword.set((isValid) ? ContextCompat.getColor(activity, R.color.black)
//                            : ContextCompat.getColor(activity, R.color.error_color));
//                    return isValid;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Boolean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Boolean isValid) {
//                        isValidPassword = isValid;
//                    }
//
//                });

    }
    
    public void onClickClearFieldImage(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel_email:
                email.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_password:
                password.set(Editable.Factory.getInstance().newEditable(""));
                break;
        }
    }


    public void onSignInClick(View view) {
        if (isValidData()) {

        }
    }


}
