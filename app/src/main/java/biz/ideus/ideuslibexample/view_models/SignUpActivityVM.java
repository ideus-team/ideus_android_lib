package biz.ideus.ideuslibexample.view_models;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.facebook.CallbackManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslib.view_models.AutorisationVM;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.fragments.TermsAndPrivacyFragment;

/**
 * Created by blackmamba on 16.11.16.
 */

public class SignUpActivityVM extends AutorisationVM implements OnValidateSignUpScreen {

    private boolean isValidName = false;
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;

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


    public SignUpActivityVM(CallbackManager faceBookCallbackManager
            , TwitterAuthClient twitterAuthClient
            , GoogleApiClient googleApiClient
            , DLibBindingActivity activity) {
        super(activity, faceBookCallbackManager, twitterAuthClient, googleApiClient, new UtilsValidationETFields(activity));
        this.visibilityClearEmailImage.set(View.INVISIBLE);
        this.visibilityClearPasswordImage.set(View.INVISIBLE);
        this.visibilityClearNameImage.set(View.INVISIBLE);
        this.isPasswordShow.set(true);
        utilsValidationETFields.setOnValidateField(this);
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

    public void onTextChangedName(CharSequence s, int start, int before, int count) {
        utilsValidationETFields.onTextChangedName(s.toString());

    }

    private boolean isValidData() {
        if (!isValidEmail) {
            Utils.toast(activity.getString(R.string.invalid_email));
            return isValidEmail && isValidPassword && isValidName;
        }
        if (!isValidPassword) {
            Utils.toast(activity.getString(R.string.invalid_password));
            return isValidEmail && isValidPassword && isValidName;
        }

        if (!isValidName)
            Utils.toast(activity.getString(R.string.invalid_name));
        return isValidEmail && isValidPassword && isValidName;

    }

    public void onCreateAccountClick(View view) {
        if (isValidData()) {

        }
    }

    public void onCheckedChangedTermAndPolicy(View v) {
        isTermAndPolicy.set((!((CheckBox) v).isChecked()));
    }


    public void onClickGooglePlus(View view) {
        signInWithGooglePlus();
    }

    public void onClickTwitterLogin(View view) {
        onClickTwitterLogin();
    }

    public void onClickFaceBookLogin(View view) {
      onClickFaceBookLogin();
    }
    public void onClickTermsAndPolicy(View view){
        activity.addFragment( new TermsAndPrivacyFragment());
    }

    // CheckBox change listener
    public void onCheckedChanged(View v) {
        isPasswordShow.set((!((CheckBox) v).isChecked()));
    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        utilsValidationETFields.onTextChangedEmail(s.toString());
    }

    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
        utilsValidationETFields.onTextChangedPassword(s.toString());

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
    public void setVisibilityImageName(int visibility) {
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
}
