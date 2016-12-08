package biz.ideus.ideuslibexample.ui.start_screen.view_models;

import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import com.theartofdev.edmodo.cropper.CropImage;

import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.ForgotPasswordFragment;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.SignUpFragment;
import biz.ideus.ideuslibexample.utils.Constants;

/**
 * Created by user on 28.11.2016.
 */

public class StartActivityVM extends AutorisationVM implements BaseMvvmInterface.StartActivityVmListener, OnValidateField {
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;

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
//        if(isShowingDialog)
//            showAttentionDialog((StartActivity) view.getViewModelBindingConfig().getContext(), context.getString(R.string.invalidate_login_text));
    }

    public void onTestClick(View view){
        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.EDIT_TEXT_DIALOG);
    }

    private boolean isValidData(View view) {
        if (!(isValidEmail && isValidPassword)) {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.LOGIN_ATTENTION);
        }
        return isValidEmail && isValidPassword;

    }

    public void onClickSelectPhoto(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((StartActivity) context);
        ((StartActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }


    @Override
    public void onFaceBookClick(View view) {
        onClickFaceBookLogin((StartActivity) context);
    }

    @Override
    public void onTwitterClick(View view) {
        onClickTwitterLogin((StartActivity) context);
    }

    @Override
    public void onGoogleClick(View view) {
        signInWithGooglePlus((StartActivity) context);
    }

    @Override
    public void onSignInClick(View view) {
        if (isValidData(view)) {
        }
        // showLoadingPage(context.getString(R.string.logging_to_app_temp), context.getString(R.string.about_logining_account), View.VISIBLE);
    }

    @Override
    public void onSignUpClick(View view) {
        ((BaseActivity) context)
                .addFragmentToBackStack(((BaseActivity) context).getSupportFragmentManager()
                        , android.R.id.content, new SignUpFragment()
                        , Constants.SIGN_UP_FRAGMENT, null, true, null);
    }

    @Override
    public void onShowPasswordClick(View view) {
        isPasswordShow.set((!((CheckBox) view).isChecked()));
    }

    @Override
    public void onForgotPasswordClick(View view) {
        ((BaseActivity) context)
                .addFragmentToBackStack(((BaseActivity)context).getSupportFragmentManager()
                        , android.R.id.content, new ForgotPasswordFragment()
                        , Constants.FORGOT_PASSWORD_FRAGMENT, null, true, null);
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

    @Override
    public String getToolbarTitle() {
        return null;
    }

}
