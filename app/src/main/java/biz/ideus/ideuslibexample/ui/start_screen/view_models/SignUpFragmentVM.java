package biz.ideus.ideuslibexample.ui.start_screen.view_models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.activity.TutorialActivity;

/**
 * Created by blackmamba on 16.11.16.
 */

public class SignUpFragmentVM extends AutorisationVM implements OnValidateSignUpScreen {
    private boolean isValidName = false;
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;


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

    private boolean isValidData(BaseActivity activity) {
        if (!(isValidEmail && isValidPassword && isValidName)) {
            showAttentionDialog(activity, context.getString(R.string.invalidate_sign_in_text));
        }
        return isValidEmail && isValidPassword && isValidName;

    }

    public void onCreateAccountClick(View view) {
        StartActivity startActivity = (StartActivity) view.getContext();
        if (isValidData(startActivity)) {

        }

        // showLoadingPage(ctx.getString(R.string.creating_an_account), ctx.getString(R.string.about_creating_account), View.VISIBLE);
        startActivity.startActivity(new Intent(startActivity, TutorialActivity.class));
    }

    public void onCheckedChangedTermAndPolicy(View v) {
        isTermAndPolicy.set((!((CheckBox) v).isChecked()));
    }

    public void onClickGooglePlus(View view) {
        signInWithGooglePlus((StartActivity) view.getContext());
    }

    public void onClickTwitterLogin(View view) {
        onClickTwitterLogin((StartActivity) view.getContext());
    }

    public void onClickFaceBookLogin(View view) {
        onClickFaceBookLogin((StartActivity) view.getContext());
    }

    public void onClickTermsAndPolicy(View view) {

    }

    // CheckBox change listener
    public void onCheckedChanged(View v) {
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
