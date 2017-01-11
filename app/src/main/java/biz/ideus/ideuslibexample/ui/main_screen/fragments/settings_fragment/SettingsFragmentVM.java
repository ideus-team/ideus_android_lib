package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import biz.ideus.ideuslibexample.data.model.request.UpdateProfileRequest;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.model.response.data.UploadFileData;
import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
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

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends BaseValidationVM implements OnValidateSignUpScreen, MainActivity.ImageChooserListener, FileUploadProcessor.SuccessUploadListener {
    private Context context;
    private boolean isValidName = true;
    private boolean isValidEmail = true;
    private boolean isValidCurrentPassword = false;
    private boolean isValidNewPassword = false;
    private boolean updateOnlyPhoto = false;
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
    public final ObservableField<String> cunnectionsUsers = new ObservableField<>();
    public final ObservableField<Drawable> photoDrawable = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        fileUploadProcessor = new FileUploadProcessor();
        fileUploadProcessor.setSuccessUploadListener(this);

        titleColorCurrentPassword.set(Color.BLACK);
        visibilityClearEmailImage.set(View.GONE);
        visibilityClearPasswordImage.set(View.GONE);
        visibilityClearNameImage.set(View.GONE);
        visibilityClearImageCurrentPassword.set(View.GONE);
        visibilityChangeInfoLayout.set(View.GONE);

        setOnValidateField(this);

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
            cunnectionsUsers.set((userInfo.getFriendsCount() != 0) ? "+ " + userInfo.getFriendsCount() + " " + context.getString(R.string.connections) : "");
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


    private void updateProfile(boolean updatePhotoOnly) {
        if (updatePhotoOnly) {
            requestUpdateProfile(new UpdateProfileRequest(uploadedUrl));

        } else if (isValideFields()) {
            requestUpdateProfile(new UpdateProfileRequest(email.get().toString()
                    , "", name.get().toString(), "", uploadedUrl));
        } else {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.UPDATE_PROFILE_ATTENTION);
        }
    }

    private void requestUpdateProfile(UpdateProfileRequest updateProfileRequest) {
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
        netApi.updateProfile(updateProfileRequest)
                .lift(new CheckError<>())
                .map(autorisationAnswer -> {
                    requeryApi.updateAutorisationInfo(autorisationAnswer.data);
                    return autorisationAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<AutorisationAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(AutorisationAnswer loginAnswer) {
                        Utils.toast(context, context.getString(R.string.update_user_profile));
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
                Log.d("change", context.getString(settingsFieldTag.nameField));
                updateProfile(false);
                break;
            case EMAIL:
                Log.d("change", context.getString(settingsFieldTag.nameField));
                updateProfile(false);
                break;
//            case CURRENT_PASSWORD:
//                visibilityChangeInfoLayout.set(View.GONE);
//                Log.d("change", context.getString(settingsFieldTag.nameField));
//                break;
            case NEW_PASSWORD:
                Log.d("change", context.getString(settingsFieldTag.nameField));
                updateProfile(false);
                break;
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

    public void selectImage(View view) {
        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((MainActivity) context);
        ((MainActivity) context).startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    @Override
    public void onChooseImage(String imagePath) {
        photoDrawable.set(Utils.convertBitmapToDrawable(context, imagePath));
        fileUploadProcessor.addFilePath(imagePath);
    }

    private boolean isValideFields() {
        return isValidName && isValidEmail;
    }

    @Override
    public void setUploadFileAnswer(UploadFileData uploadData) {
        uploadedUrl = uploadData.getFile();
        updateProfile(true);
    }
}
