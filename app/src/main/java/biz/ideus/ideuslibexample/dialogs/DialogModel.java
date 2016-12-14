package biz.ideus.ideuslibexample.dialogs;

import android.graphics.Color;
import android.view.View;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 01.12.16.
 */

public enum DialogModel {

    LOGIN_ATTENTION(R.string.error,R.string.invalidate_login_text, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    SIGN_IN_ATTENTION(R.string.error,R.string.invalidate_sign_in_text,  R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
    CHANGE_PASSWORD_SUCCESS(R.string.password_reset,R.string.password_reset_title,  R.layout.dialog_info, View.GONE, Color.BLACK,R.string.ok),
    EDIT_TEXT_DIALOG(R.string.message,0, R.layout.edit_text_dialog), NO_INTERNET_CONNECTION(R.string.no_internet_connection),
    SHOW_LOADING_DIALOG(0,0, R.layout.loading_dialog), HIDE_LOADING_DIALOG;
  //  ,PROGRESS_WAVE(R.string.message, R.string.message, R.layout.dialog_progress_wave, View.VISIBLE, Color.RED,R.string.try_again);

    public int resDialogName;
    public int resAboutDialogText;
    public int layoutId;
    public int colorTitle;
    public int resBtnName;
    public int visibilityIcon;

    DialogModel(){}

    DialogModel(int resDialogName){
        this.resDialogName = resDialogName;
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
