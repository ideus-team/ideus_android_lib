package biz.ideus.ideuslibexample.data;

import android.graphics.Color;

import biz.ideus.ideuslib.dialogs.DialogModel;
import biz.ideus.ideuslib.dialogs.DialogModelBuilder;
import biz.ideus.ideuslib.dialogs.DialogType;
import biz.ideus.ideuslibexample.R;


/**
 * Created by user on 07.02.2017.
 */

public class DialogStore {

    public static DialogModel LOGIN_ATTENTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.invalidate_login_text)
                .setColorTitle(Color.RED)
                .setResBtnText(R.string.try_again)
                .createDialogModel();
    }

    public static DialogModel PROGRESS_DIALOG() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.loading_dialog)
                .createDialogModel();
    }

    public static DialogModel UPDATE_PROFILE_ATTENTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.invalidate_update_text)
                .setColorTitle(Color.RED)
                .setResBtnText(R.string.try_again)
                .createDialogModel();
    }

    public static DialogModel UPDATE_PROFILE_PASSWORD_ATTENTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.invalidate_update_passwords)
                .setColorTitle(Color.RED)
                .setResBtnText(R.string.try_again)
                .createDialogModel();
    }

    public static DialogModel TERMS_OF_SERVICE_ATTENTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.check_terms_of_service)
                .setColorTitle(Color.RED)
                .setResBtnText(R.string.try_again)
                .createDialogModel();
    }

    public static DialogModel SIGN_UP_ATTENTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.invalidate_sign_in_text)
                .setColorTitle(Color.RED)
                .setResBtnText(R.string.try_again)
                .createDialogModel();
    }

    public static DialogModel CHANGE_PASSWORD_SUCCESS() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.password_reset)
                .setResDialogText(R.string.password_reset_title)
                .setColorTitle(Color.BLACK)
                .setResBtnText(R.string.ok)
                .createDialogModel();
    }

    public static DialogModel EDIT_TEXT_DIALOG() {
        return new DialogModelBuilder()
                .setResDialogHeader(R.string.message)
                .setLayoutId(R.layout.edit_text_dialog)
                .createDialogModel();
    }


    public static DialogModel NEW_VERSION_MUST_HAVE() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.new_version_must_have_dialog)
                .createDialogModel();
    }

    public static DialogModel NEW_VERSION_RECOMENDED() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.new_version_recomended_dialog)
                .createDialogModel();
    }

    public static DialogModel SOCKET_UNFORTUNATELY_DIALOG() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogText(R.string.no_socket_connection)
                .setDialogType(DialogType.SHOW_SNACKBAR)
                .createDialogModel();
    }

    public static DialogModel NO_INTERNET_CONNECTION() {
        return new DialogModelBuilder()
                .setLayoutId(R.layout.dialog_info)
                .setResDialogHeader(R.string.error)
                .setResDialogText(R.string.no_internet_connection_dialog)
                .setColorTitle(Color.BLACK)
                .setResBtnText(R.string.ok)
                .createDialogModel();
    }

    public static DialogModel HIDE_PROGRESS_DIALOG() {
        return new DialogModelBuilder()
                .setDialogType(DialogType.HIDE_DIALOG)
                .createDialogModel();
    }

    //WTF ???
//    public static DialogModel ERROR_DIALOG() {
//        return new DialogModelBuilder()
//                .createDialogModel();
//    }






//      HIDE_PROGRESS_DIALOG,
//      ERROR_DIALOG,
//      LOGIN_ATTENTION(R.string.error,R.string.invalidate_login_text, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
//      UPDATE_PROFILE_ATTENTION(R.string.error, R.string.invalidate_update_text, R.layout.dialog_info, View.VISIBLE, Color.RED, R.string.try_again),
//      UPDATE_PROFILE_PASSWORD_ATTENTION(R.string.error,R.string.invalidate_update_passwords, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
//      TERMS_OF_SERVICE_ATTENTION(R.string.error,R.string.check_terms_of_service, R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
//      SIGN_UP_ATTENTION(R.string.error,R.string.invalidate_sign_in_text,  R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.try_again),
//      CHANGE_PASSWORD_SUCCESS(R.string.password_reset,R.string.password_reset_title,  R.layout.dialog_info, View.GONE, Color.BLACK,R.string.ok),
//      EDIT_TEXT_DIALOG(R.string.message,R.string.message, R.layout.edit_text_dialog),
//      PROGRESS_DIALOG(R.layout.loading_dialog), HIDE_PROGRESS_DIALOG, ERROR_DIALOG,
//      NEW_VERSION_MUST_HAVE_DIALOG(R.string.message,0, R.layout.new_version_must_have_dialog),
//      NEW_VERSION_RECOMENDED_DIALOG(R.string.message,0, R.layout.new_version_recomended_dialog),
//      SOCKET_UNFORTUNATELY_DIALOG(R.string.no_socket_connection, 0, R.layout.dialog_info),
//      NO_INTERNET_CONNECTION(R.string.error,R.string.no_internet_connection_dialog,  R.layout.dialog_info, View.VISIBLE, Color.RED,R.string.ok);
}
