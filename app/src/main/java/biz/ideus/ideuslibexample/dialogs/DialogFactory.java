package biz.ideus.ideuslibexample.dialogs;

import biz.ideus.ideuslibexample.ui.base.BaseActivity;

/**
 * Created by blackmamba on 01.12.16.
 */

public class DialogFactory {
    private BaseActivity activity;
    private CustomAttentionDialog dialog;

    public DialogFactory(BaseActivity activity){
        this.activity = activity;
    }

    public CustomAttentionDialog getDialog(DialogModel dialogModel){

        switch (DialogModel.getDialogModelByValue(dialogModel.resDialogName)){
            case LOGIN_ATTENTION:
                dialog = new LoginAttentionDialog(activity);
                break;
            case SIGN_IN_ATTENTION:
                dialog = new SignInAttentionDialog(activity);
                break;
            case CHANGE_PASSWORD_SUCCESS:
                dialog = new SuccessChangePasswordDialog(activity);
                break;
        }
        return dialog;
    }
}
