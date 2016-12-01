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

public class SignInAttentionDialog extends CustomAttentionDialog {

    public SignInAttentionDialog(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getColorTitle() {
        return Color.RED;
    }

    @Override
    public String getBtnName() {
        return activity.getString(R.string.try_again);
    }

    @Override
    public int getVisbilityStatusImage() {
        return View.VISIBLE;
    }

    @Override
    public String getAboutActionText() {
        return activity.getString(DialogModel.SIGN_IN_ATTENTION.resDialogName);
    }

    @Override
    public String getTitle() {
        return activity.getString(R.string.error);
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
