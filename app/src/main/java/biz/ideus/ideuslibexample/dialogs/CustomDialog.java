package biz.ideus.ideuslibexample.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.rx_buses.RxBusActionEditDialogBtn;

/**
 * Created by blackmamba on 18.11.16.
 */

public class CustomDialog extends DialogFragment{
    private ViewDataBinding binding;
    public static String LAYOUT_KEY = "layout";
    public static String DIALOG_MODEL_KEY = "DialogModel";

    private int layout;

    public void setLayout(int layout) {
        this.layout = layout;
    }

    private Object dialogIntent;
    private DialogModel dialogModel;
     private static CustomDialog customDialog;
    public DialogModel getDialogModel() {
        return dialogModel;
    }

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> aboutDialogTitle = new ObservableField<>();
    public final ObservableField<String> btnName = new ObservableField<>();
    public final ObservableField<Integer> colorTitle = new ObservableField<>();
    public final ObservableField<Integer> visibilityAttentionIcon = new ObservableField<>();

    public static CustomDialog instance(DialogModel dialogModel, @Nullable Object dialogIntent) {
        customDialog = new CustomDialog();
        customDialog.layout = dialogModel.layoutId;
        customDialog.dialogIntent = dialogIntent;
        customDialog.dialogModel = dialogModel;
        return customDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        if (savedInstanceState != null) {
            layout = savedInstanceState.getInt(LAYOUT_KEY);
            dialogModel = (DialogModel) savedInstanceState.getSerializable(DIALOG_MODEL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, layout, container, true);
        binding.setVariable(BR.customVM, this);
        setDialogParameters();
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAYOUT_KEY, layout);
        outState.putSerializable(DIALOG_MODEL_KEY, dialogModel);
    }

    private void setDialogParameters() {
        switch (getDialogModel()){
            case EDIT_TEXT_DIALOG:
                title.set(getString(getDialogModel().resDialogName));
                break;
            case SHOW_LOADING_DIALOG:
                customDialog.setCancelable(false);
                break;
            default:
                title.set(getString(getDialogModel().resDialogName));
                colorTitle.set(getDialogModel().colorTitle);
                visibilityAttentionIcon.set(getDialogModel().visibilityIcon);
                aboutDialogTitle.set(getString(getDialogModel().resAboutDialogText));
                btnName.set(getString(getDialogModel().resBtnName));
                break;
        }
    }


    public void onClick(View view) {
        DialogCommandModel dialogCommandModel = (DialogCommandModel) view.getTag();
        if (dialogCommandModel != null) {
            RxBusActionEditDialogBtn.instanceOf().setDialogCommand(new DialogCommand(dialogCommandModel, null));
            dismiss();
        } else {
            dismiss();
        }

    }

    public class DialogCommand {
        private DialogCommandModel dialogCommandModel;
        private Object dialogIntent;


        public DialogCommandModel getDialogCommandModel() {
            return dialogCommandModel;
        }

        public DialogCommand(DialogCommandModel dialogCommandModel, Object dialogIntent) {
            this.dialogCommandModel = dialogCommandModel;
            this.dialogIntent = dialogIntent;
        }
    }

}


