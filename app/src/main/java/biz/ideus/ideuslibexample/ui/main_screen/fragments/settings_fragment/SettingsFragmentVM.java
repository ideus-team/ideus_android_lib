package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.theartofdev.edmodo.cropper.CropImage;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.BaseValidationVM;
import biz.ideus.ideuslibexample.utils.FileUploadProcessor;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends BaseValidationVM implements OnValidateSignUpScreen, MainActivity.ImageChooserListener {
    private Context context;
    private boolean isValidName = false;
    private boolean isValidEmail = false;
    private boolean isValidCurrentPassword = false;
    private boolean isValidNewPassword = false;
    private SettingsFieldTag settingsFieldTag;
    private FileUploadProcessor fileUploadProcessor;
    private String selectedImagePath;

    public SettingsFieldTag getSettingsFieldTag() {
        return settingsFieldTag;
    }

    public final ObservableField<CharSequence> textCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> visibilityClearImageCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> titleColorCurrentPassword = new ObservableField<>();
    public final ObservableField<Integer> visibilityChangeInfoLayout = new ObservableField<>();
    public final ObservableField<String> titleChangeBtn = new ObservableField<>();
    public final ObservableField<String> fullNameUser = new ObservableField<>();
    public final ObservableField<String> photo = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        fetchUserInfo();
      fileUploadProcessor = new FileUploadProcessor();
//
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
        ((MainActivity)context).setImageChooserListener(this);
    }

    private void fetchUserInfo() {
        requeryApi.getAutorisationInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(autorisationEntity -> {
            displayUserInfo(autorisationEntity);
        });

    }

    private void displayUserInfo(AutorisationEntity userInfo) {
        if (userInfo != null) {
            name.set(userInfo.getFirst_name());
            email.set(userInfo.getEmail());
            photo.set(userInfo.getPhoto());
            fullNameUser.set(userInfo.getFirst_name() + " " + userInfo.getLast_name());
        }
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

    public void onMainLayoutClick(View view) {

    }

    public EditText.OnFocusChangeListener getEditTextFocusListener() {
        return (view, hasFocus) -> {
            if (view.isFocused()) {
                settingsFieldTag = ((SettingsFieldTag) view.getTag());
                showingChangeFieldLayout();
            }
        };
    }

    public EditText.OnFocusChangeListener getMainLayoutFocusListener() {
        return (view, hasFocus) -> {
            if (hasFocus) {
                ((BaseActivity) context).hideKeyboard();
                visibilityChangeInfoLayout.set(View.GONE);
            }
        };
    }


    private void showingChangeFieldLayout() {
        switch (getSettingsFieldTag()) {
            case CURRENT_PASSWORD:
                visibilityChangeInfoLayout.set(View.GONE);
                titleChangeBtn.set(context.getString(settingsFieldTag.nameField));
                break;
            default:
                visibilityChangeInfoLayout.set(View.VISIBLE);
                titleChangeBtn.set(context.getString(settingsFieldTag.nameField));
                break;
        }
    }

    public void onClickCancelChangeInfo(View view) {
        hiddingChangeLayout();
        ((BaseActivity) context).hideKeyboard();
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

    public void selectImage(View view){
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((MainActivity) context);
        ((MainActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    @Override
    public void onChooseImage(Uri imageUri) {
        fileUploadProcessor.addFilePath(imageUri.getPath());
    }
}
