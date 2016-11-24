package biz.ideus.ideuslibexample.view_models;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.theartofdev.edmodo.cropper.CropImage;

import javax.inject.Inject;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.activities.StartActivity;
import biz.ideus.ideuslibexample.ui.fragments.ForgotPasswordFragment;
import biz.ideus.ideuslibexample.ui.fragments.SignUpFragment;
import biz.ideus.ideuslibexample.utils.Constants;

/**
 * Created by user on 18.11.2016.
 */
@PerActivity
public class StartActivityVM extends AutorisationVM implements BaseMvvmInterface.StartActivityVmListener, OnValidateField {

    private final Context ctx;
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

    @Inject
    public StartActivityVM(@AppContext Context context) {
        super(context);
        this.ctx = context.getApplicationContext();
        this.visibilityClearEmailImage.set(View.INVISIBLE);
        this.visibilityClearPasswordImage.set(View.INVISIBLE);
        this.isPasswordShow.set(true);
        utilsValidation.setOnValidateField(this);
    }


    private boolean isValidData() {
        if (!isValidEmail) {
            Utils.toast(ctx, ctx.getString(R.string.invalid_email));
            return isValidEmail && isValidPassword;
        }
        if (!isValidPassword)
            Utils.toast(ctx, ctx.getString(R.string.invalid_password));
        return isValidEmail && isValidPassword;

    }

    public void onClickSelectPhoto(View view) {
            StartActivity activity = (StartActivity) view.getContext();
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent(activity);
        navigator.get().startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
  //  activity.startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
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
        if (isValidData()) {

        }
    }
    @Override
    public void onSignUpClick(View view) {
        navigator.get().addFragmentToBackStack(android.R.id.content, new SignUpFragment(), Constants.SIGN_UP_FRAGMENT,null, true, null);
    }

    @Override
    public void onShowPasswordClick(View view) {
        isPasswordShow.set((!((CheckBox) view).isChecked()));
    }

    @Override
    public void onForgotPasswordClick(View view) {
        navigator.get().addFragmentToBackStack(android.R.id.content, new ForgotPasswordFragment(), Constants.SIGN_UP_FRAGMENT,null, true, null);
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
