package biz.ideus.ideuslibexample.ui.start_screen.view_models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.TermsOfServiceFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.activity.TutorialActivity;
import biz.ideus.ideuslibexample.utils.Constants;

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

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
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

    private boolean isValidData(StartActivity activity) {
//        return isValidEmail && isValidPassword && isValidName;
        return true;
    }

    public void onCreateAccountClick(View view) {
        StartActivity startActivity = (StartActivity) view.getContext();
        if (isValidData(startActivity)) {
            showLoadingPage(startActivity.getString(R.string.creating_an_account), startActivity.getString(R.string.about_creating_account), View.VISIBLE);
            startActivity.startActivity(new Intent(startActivity, TutorialActivity.class));
            startActivity.finish();
        } else {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.SIGN_IN_ATTENTION);
        }

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
        ((BaseActivity) context)
                .addFragmentToBackStack( ((BaseActivity) context).getSupportFragmentManager()
                        , android.R.id.content, new TermsOfServiceFragment()
                        , Constants.TERMS_OF_SERVICE_FRAGMENT, null, true, null);
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

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.sign_up);
    }
    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }
}
