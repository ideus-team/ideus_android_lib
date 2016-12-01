package biz.ideus.ideuslibexample.dialogs;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 01.12.16.
 */

public enum DialogModel {

    LOGIN_ATTENTION(R.string.invalidate_login_text),
    SIGN_IN_ATTENTION(R.string.invalidate_sign_in_text),
    CHANGE_PASSWORD_SUCCESS(R.string.password_reset_title);

    public int resDialogName;
    DialogModel(int resDialogName){
        this.resDialogName = resDialogName;
    }

    public static DialogModel getDialogModelByValue(int state){
        for(DialogModel dm : DialogModel.values()) {
            if (dm.resDialogName == state) return dm;
        }
        return null;
    }
}
