package biz.ideus.ideuslibexample;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBaseSampleBindingActivity;
import biz.ideus.ideuslibexample.databinding.DialogInfoBinding;

/**
 * Created by blackmamba on 18.11.16.
 */

public class CustomAttentionDialog {
    private ViewModelBaseSampleBindingActivity activity;
    private DialogInfoBinding binding;

    View.OnClickListener onBtnDialogClickListener, onCloseListener;
    Dialog dialog;


    public CustomAttentionDialog(ViewModelBaseSampleBindingActivity activity) {
        this.activity = activity;
        dialog = new Dialog(activity, R.style.customDialog);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(dialog.getContext()),R.layout.dialog_info, null, false);
        dialog.setContentView(binding.getRoot());

    }

    public CustomAttentionDialog setColorTitle(int color) {
        binding.tvTitle.setTextColor(color);
        return this;
    }

    public CustomAttentionDialog setBtnName(String nameBtn) {
        binding.tvBtnDialog.setText(nameBtn);
        return this;
    }

    public CustomAttentionDialog setVisbilityStatusImage(int visibility) {
        binding.ivStatusDialog.setVisibility(visibility);
        return this;
    }

    public CustomAttentionDialog setAboutActionText(String text) {
        binding.tvAboutAction.setText(text);
        return this;
    }
    public CustomAttentionDialog setTitle(String title) {
        binding.tvTitle.setText(title);
        return this;
    }

    public CustomAttentionDialog setOnBtnDialogClickListener(View.OnClickListener onBtnDialogClickListener) {
        this.onBtnDialogClickListener = onBtnDialogClickListener;
        return this;
    }

    public CustomAttentionDialog setOnCloseClickListener(View.OnClickListener onCloseListener) {
        this.onCloseListener = onCloseListener;
        return this;
    }

    private void setListeners() {
        binding.tvBtnDialog.setOnClickListener(onBtnDialogClickListener);
        binding.ivClose.setOnClickListener(onCloseListener);
    }

    public void show() {
        setListeners();
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}

