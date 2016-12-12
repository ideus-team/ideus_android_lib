package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.BaseValidationVM;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends BaseValidationVM implements OnValidateSignUpScreen {
    private Context context;
    private boolean isValidName = false;
    private boolean isValidEmail = false;
    private boolean isValidCurrentPassword = false;
    private boolean isValidNewPassword = false;
    private SettingsFieldTag settingsFieldTag;
    public SettingsFieldTag getSettingsFieldTag() {
        return settingsFieldTag;
    }


    public final ObservableField<CharSequence> textCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> visibilityClearImageCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> titleColorCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> visibilityChangeInfoLayout = new ObservableField<>();
    public final ObservableField<String> titleChangeBtn = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        titleColorCurrentPassword.set(Color.BLACK);
        visibilityClearEmailImage.set(View.GONE);
        visibilityClearPasswordImage.set(View.GONE);
        visibilityClearNameImage.set(View.GONE);
        visibilityClearImageCurrentPassword.set(View.GONE);
        visibilityChangeInfoLayout.set(View.GONE);

        setOnValidateField(this);
    }


    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.settings);
    }

    @Override
    public boolean isLeftBtnVisible() {
        return false;
    }


    public void onClickClearFieldImage(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel_email:
                email.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_password:
                password.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_current_password:
                textCurrentPassword.set(Editable.Factory.getInstance().newEditable(""));
                break;
            case R.id.iv_cancel_name:
                name.set(Editable.Factory.getInstance().newEditable(""));
                break;
        }
    }

    public EditText.OnFocusChangeListener getFocusListener() {
        return (view, hasFocus) -> {
            if (view.isFocused()) {
                settingsFieldTag = ((SettingsFieldTag) view.getTag());
                showingChangeFieldLayout();
            } else {
                hiddingChangeLayout();
            }
        };
    }

    public EditText.OnFocusChangeListener getMainFocusListener() {
        return (view, hasFocus) -> {
            if (hasFocus) {
                ((BaseActivity) context).hideKeyboard(view);
            }
        };
    }

    public void onMainLayoutClick(View view) {
    }


    private void showingChangeFieldLayout() {
        if (settingsFieldTag != SettingsFieldTag.CURRENT_PASSWORD) {
            visibilityChangeInfoLayout.set(View.VISIBLE);
            titleChangeBtn.set(context.getString(settingsFieldTag.nameField));
        }
    }

    public void onClickCancelChangeInfo(View view) {
        hiddingChangeLayout();
    }

    private void hiddingChangeLayout() {
        visibilityChangeInfoLayout.set(View.GONE);
    }

    public void onClickChangeInfo(View view) {
        switch (getSettingsFieldTag()) {
            case NAME:
                visibilityChangeInfoLayout.set(View.GONE);
                Log.d("change", context.getString(settingsFieldTag.nameField));
                break;
            case EMAIL:
                visibilityChangeInfoLayout.set(View.GONE);
                Log.d("change", context.getString(settingsFieldTag.nameField));
                break;
//            case CURRENT_PASSWORD:
//                visibilityChangeInfoLayout.set(View.GONE);
//                Log.d("change", context.getString(settingsFieldTag.nameField));
//                break;
            case NEW_PASSWORD:
                visibilityChangeInfoLayout.set(View.GONE);
                Log.d("change", context.getString(settingsFieldTag.nameField));
                break;
        }
    }

    public void onTextChangedEmail(CharSequence text, int start, int before, int count) {
        onTextChangedEmail(text);
    }

    public void onTextChangedNewPassword(CharSequence text, int start, int before, int count) {
        onTextChangedPassword(text);

    }

    public void onTextChangedName(CharSequence text, int start, int before, int count) {
        onTextChangedName(text);

    }

    public void onTextChangedCurrentPassword(CharSequence text, int start, int before, int count) {
        textCurrentPassword.set(text);
        visibilityClearImageCurrentPassword.set(getVisibility(text.toString()));
//        Observable.just(text.toString())
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .doOnNext(currentText -> {
//                    visibilityClearImageCurrentPassword.set(getVisibility(currentText));
//                })
////                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validatePassword(currentText, MIN_COUNT_CHARACTER_PASSWORD)))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(aBoolean -> {
//                    isValidCurrentPassword = aBoolean;
//                    titleColorCurrentPassword.set(getColor(aBoolean, context));
//                });
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
        isValidNewPassword = isValid;
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

}
