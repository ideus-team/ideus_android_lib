package biz.ideus.ideuslibexample.view_models;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.theartofdev.edmodo.cropper.CropImage;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.fragments.ForgotPasswordFragment;
import biz.ideus.ideuslibexample.fragments.SignUpFragment;


/**
 * Created by blackmamba on 11.11.16.
 */

public class LoginActivityVM extends AutorisationVM implements OnValidateField {
    private DLibBindingActivity activity;

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


    public LoginActivityVM(DLibBindingActivity activity) {
        super(activity);
        this.activity = activity;
        this.visibilityClearEmailImage.set(View.INVISIBLE);
        this.visibilityClearPasswordImage.set(View.INVISIBLE);
        this.isPasswordShow.set(true);
        utilsValidation.setOnValidateField(this);
    }

    public void onClickSignUp(View view) {
        goToSignUp();
    }

    public void onClickSelectPhoto(View view) {
        selectPhoto();
    }

    private void selectPhoto() {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent(activity);
        activity.startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    public void onClickGooglePlus(View view) {
        signInWithGooglePlus();
    }

    public void onClickTwitterLogin(View view) {
        onClickTwitterLogin();

    }

    public void onClickForgotPassword(View view) {
        activity.addFragment(new ForgotPasswordFragment());
    }

    private void goToSignUp() {
        activity.addFragment(new SignUpFragment());
    }


    public void onClickFaceBookLogin(View view) {
        onClickFaceBookLogin();
    }

    // CheckBox change listener
    public void onCheckedChanged(View v) {
        isPasswordShow.set((!((CheckBox) v).isChecked()));
    }

    private boolean isValidData() {
        if (!isValidEmail) {
            Utils.toast(activity.getString(R.string.invalid_email));
            return isValidEmail && isValidPassword;
        }
        if (!isValidPassword)
            Utils.toast(activity.getString(R.string.invalid_password));
        return isValidEmail && isValidPassword;

    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        utilsValidation.onTextChangedEmail(s.toString());
    }

    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
        utilsValidation.onTextChangedPassword(s.toString());

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
}
