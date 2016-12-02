package biz.ideus.ideuslibexample.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.DialogInfoBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;

/**
 * Created by blackmamba on 18.11.16.
 */

public abstract class CustomAttentionDialog {
    protected BaseActivity activity;
    private DialogInfoBinding binding;
    Dialog dialog;

    public CustomAttentionDialog(BaseActivity activity) {
        this.activity = activity;
        dialog = new Dialog(activity, R.style.customDialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(dialog.getContext()), R.layout.dialog_info, null, false);
        dialog.setContentView(binding.getRoot());

        binding.tvTitle.setTextColor(getColorTitle());
        binding.tvBtnDialog.setText(getBtnName());
        binding.ivStatusDialog.setVisibility(getVisbilityStatusImage());
        binding.tvAboutAction.setText(getAboutActionText());
        binding.tvTitle.setText(getTitle());
        binding.tvBtnDialog.setOnClickListener(getOnBtnDialogClickListener());
        binding.ivClose.setOnClickListener(getOnCloseClickListener());
        dialog.setOnDismissListener(getOnDissmissListener());
        show();
    }


    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public abstract int getColorTitle();

    public abstract String getBtnName();

    public abstract int getVisbilityStatusImage();

    public abstract String getAboutActionText();

    public abstract String getTitle();

    public abstract View.OnClickListener getOnBtnDialogClickListener();

    public abstract View.OnClickListener getOnCloseClickListener();

    public abstract DialogInterface.OnDismissListener getOnDissmissListener();


}

