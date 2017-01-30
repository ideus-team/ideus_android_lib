package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.theartofdev.edmodo.cropper.CropImage;

import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.request.UpdateProfileRequest;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;
import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.interfaces.ImageChooserListener;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.BaseValidationVM;
import biz.ideus.ideuslibexample.utils.FileUploadProcessor;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;
import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment.SettingsFieldTag.EMAIL;
import static biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment.SettingsFieldTag.NAME;
import static biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment.SettingsFieldTag.NEW_PASSWORD;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends BaseValidationVM implements OnValidateSignUpScreen, ImageChooserListener, FileUploadProcessor.SuccessUploadListener {
    private Context context;
    private boolean isValidName = true;
    private boolean isValidEmail = true;
    private boolean isValidCurrentPassword = false;
    private boolean isValidNewPassword = false;
    private SettingsFieldTag settingsFieldTag = SettingsFieldTag.EMPTY_TAG;
    private FileUploadProcessor fileUploadProcessor;
    private String uploadedUrl = "";


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
    public final ObservableField<String> connectionsUsers = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        fileUploadProcessor = new FileUploadProcessor();
        fileUploadProcessor.setSuccessUploadListener(this);
        initialStateFields();
        setOnValidateField(this);

    }

    private void initialStateFields() {
        visibilityClearEmailImage.set(View.GONE);
        visibilityClearPasswordImage.set(View.GONE);
        visibilityClearNameImage.set(View.GONE);
        visibilityClearImageCurrentPassword.set(View.GONE);
        visibilityChangeInfoLayout.set(View.GONE);
        textCurrentPassword.set("");
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchUserInfo();
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
        ((MainActivity) context).setImageChooserListener(this);
    }

    private void fetchUserInfo() {
        requeryApi.getAutorisationInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(autorisationEntity -> {
            uploadedUrl = autorisationEntity.getPhoto();
            displayUserInfo(autorisationEntity);
        });
    }

    private void displayUserInfo(AutorisationEntity userInfo) {
        if (userInfo != null) {
            name.set(userInfo.getFirst_name());
            email.set(userInfo.getEmail());
            photo.set(userInfo.getPhoto());
            connectionsUsers.set(!(userInfo.getFriendsCount().equals("0")) ?
                    "+ " + userInfo.getFriendsCount() + " " + context.getString(R.string.connections) : "");
            setFullNameUser(userInfo);
        }
    }

    private void setFullNameUser(AutorisationEntity userInfo) {
        fullNameUser.set(userInfo.getFirst_name() + " " + userInfo.getLast_name());
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

    public EditText.OnFocusChangeListener getEditTextFocusListener() {
        return (view, hasFocus) -> {
            if (hasFocus) {
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
                break;
            case EMPTY_TAG:
                visibilityChangeInfoLayout.set(View.GONE);
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

    public void onMainLayoutClick(View view) {

    }

    private void requestUpdateProfile(UpdateProfileRequest updateProfileRequest) {
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
        netApi.updateProfile(updateProfileRequest)
                .lift(new CheckError<>())
                .map(autorisationAnswer -> {
                    requeryApi.storeAutorisationInfo(autorisationAnswer.data);
                    return autorisationAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(AutorisationAnswer autorisationAnswer) {
                        Utils.toast(context, context.getString(R.string.update_user_profile));
                        setFullNameUser(autorisationAnswer.data);
                        visibilityChangeInfoLayout.set(View.GONE);

                    }
                });
    }

    private void hiddingChangeLayout() {
        visibilityChangeInfoLayout.set(View.GONE);
    }

    public void onClickChangeInfo(View view) {
        switch (getSettingsFieldTag()) {
            case NAME:
                if (isValidCurrentField(NAME))
                    requestUpdateProfile(new UpdateProfileRequest().setName(name.get().toString()));
                break;
            case EMAIL:
                if (isValidCurrentField(EMAIL))
                    requestUpdateProfile(new UpdateProfileRequest().setEmail(email.get().toString()));
                break;
            case NEW_PASSWORD:
                if (isValidCurrentField(NEW_PASSWORD))
                    requestUpdateProfile(new UpdateProfileRequest()
                            .setPassword(textCurrentPassword.get().toString(), password.get().toString()));
                break;
        }
    }

    private boolean isValidCurrentField(SettingsFieldTag tagField) {
        boolean validFlag = false;
        switch (tagField) {
            case NAME:
                validFlag = isValidField(isValidName);
                break;
            case EMAIL:
                validFlag = isValidField(isValidEmail);
                break;
            case NEW_PASSWORD:
                validFlag = isValidField(isValidNewPassword && isValidCurrentPassword);
                break;
        }
        return validFlag;
    }

    private boolean isValidField(boolean isValid) {
        if (isValid) {
            return isValid;
        } else {
            if(settingsFieldTag.equals(NEW_PASSWORD)){
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.UPDATE_PROFILE_PASSWORD_ATTENTION);
                return isValid;
            } else {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.UPDATE_PROFILE_ATTENTION);
                return isValid;
            }
        }
    }

    public void onTextChangedEmail(CharSequence text, int start, int before, int count) {
        onTextChangedEmail(text);
        showingChangeFieldLayout();
    }

    public void onTextChangedNewPassword(CharSequence text, int start, int before, int count) {
        onTextChangedPassword(text);
        showingChangeFieldLayout();

    }

    public void onTextChangedName(CharSequence text, int start, int before, int count) {
        onTextChangedName(text);
        showingChangeFieldLayout();

    }

    public void onTextChangedCurrentPassword(CharSequence text, int start, int before, int count) {
        textCurrentPassword.set(text);
        validateCurrentPassword(text);
        visibilityClearImageCurrentPassword.set(getVisibility(text.toString()));

    }

    private void validateCurrentPassword(CharSequence text) {
        isValidCurrentPassword = text.length() > MIN_COUNT_CHARACTER_PASSWORD;
        titleColorCurrentPassword.set(getColor(text.length() > MIN_COUNT_CHARACTER_PASSWORD, context));
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

    public void selectImage(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((MainActivity) context);
        ((MainActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    @Override
    public void onChooseImage(String imagePath) {
        fileUploadProcessor.addFilePath(imagePath);
    }

    @Override
    public void setUploadFileAnswer(UploadFileData uploadData) {
        uploadedUrl = uploadData.getFile();
        photo.set(uploadedUrl);
        requestUpdateProfile(new UpdateProfileRequest().setPhotoUrl(uploadedUrl));
    }
}
