package biz.ideus.ideuslibexample.dialogs;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.utils.RxBusShowDialog;

/**
 * Created by blackmamba on 01.12.16.
 */

public class SuccessChangePasswordDialog extends CustomAttentionDialog {

    public SuccessChangePasswordDialog(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getColorTitle() {
        return Color.BLACK;
    }

    @Override
    public String getBtnName() {
        return activity.getString(R.string.ok);
    }

    @Override
    public int getVisbilityStatusImage() {
        return View.GONE;
    }

    @Override
    public String getAboutActionText() {
        return activity.getString(DialogModel.CHANGE_PASSWORD_SUCCESS.resDialogName);
    }

    @Override
    public String getTitle() {
        return activity.getString(R.string.password_reset);
    }

    @Override
    public View.OnClickListener getOnBtnDialogClickListener() {
        return view -> {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(null);
            hide();
        };
    }

    @Override
    public View.OnClickListener getOnCloseClickListener() {
        return view -> {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(null);
            hide();
        };
    }

    @Override
    public DialogInterface.OnDismissListener getOnDissmissListener() {
        return dialogInterface -> {
            RxBusShowDialog.instanceOf().setRxBusShowDialog(null);
            hide();
        };
    }
}
