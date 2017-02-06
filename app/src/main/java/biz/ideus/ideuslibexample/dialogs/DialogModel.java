package biz.ideus.ideuslibexample.dialogs;

import android.graphics.Color;
import android.view.View;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 01.12.16.
 */

public  enum DialogModel {

    LOGIN_ATTENTION(R.string.error,R.string.invalidate_login_text, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    UPDATE_PROFILE_ATTENTION(R.string.error,R.string.invalidate_update_text, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    UPDATE_PROFILE_PASSWORD_ATTENTION(R.string.error,R.string.invalidate_update_passwords, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    TERMS_OF_SERVICE_ATTENTION(R.string.error,R.string.check_terms_of_service, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    SIGN_UP_ATTENTION(R.string.error,R.string.invalidate_sign_in_text,  R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    CHANGE_PASSWORD_SUCCESS(R.string.password_reset,R.string.password_reset_title,  R.layout.dialog_info, View.GONE, Color.BLACK,R.string.ok),
    EDIT_TEXT_DIALOG(R.string.message,R.string.message, R.layout.edit_text_dialog), NO_INTERNET_CONNECTION(R.string.no_internet_connection),
    PROGRESS_DIALOG(R.layout.loading_dialog), HIDE_PROGRESS_DIALOG, ERROR_DIALOG,
    NEW_VERSION_MUST_HAVE_DIALOG(R.string.message,0, R.layout.new_version_must_have_dialog),
    NEW_VERSION_RECOMENDED_DIALOG(R.string.message,0, R.layout.new_version_recomended_dialog),
    SOCKET_UNFORTUNATELY_DIALOG(R.string.no_socket_connection, 0, R.layout.dialog_info),
    NO_INTERNET_CONNECTION(R.string.error,R.string.no_internet_connection_dialog,  R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.ok);;


    public int resDialogName = R.string.message;
    public int resAboutDialogText = R.string.message;
    public int layoutId;
    public int colorTitle;
    public int resBtnName = R.string.message;
    public int visibilityIcon;

    DialogModel(){}
    DialogModel(int layoutId){
        this.layoutId = layoutId;
    }

    DialogModel(int resDialogName, int resAboutDialogText, int layoutId,int visibilityIcon,int colorTitle,int resBtnName){
        this.resDialogName = resDialogName;
        this.resAboutDialogText = resAboutDialogText;
        this.layoutId = layoutId;
        this.visibilityIcon = visibilityIcon;
        this.colorTitle = colorTitle;
        this.resBtnName = resBtnName;
    }

    DialogModel(int resDialogName, int resAboutDialogText, int layoutId){
        this.resDialogName = resDialogName;
        this.resAboutDialogText = resAboutDialogText;
        this.layoutId = layoutId;

    }

    public static DialogModel getDialogModelByValue(int state){
        for(DialogModel dm : DialogModel.values()) {
            if (dm.resDialogName == state) return dm;
        }
        return null;
    }
}
