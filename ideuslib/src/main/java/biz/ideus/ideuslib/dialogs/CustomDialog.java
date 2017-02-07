package biz.ideus.ideuslib.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.parceler.Parcels;

import biz.ideus.ideuslib.R;


//import biz.ideus.ideuslib.BR;


/**
 * Created by blackmamba on 18.11.16.
 */

public class CustomDialog extends DialogFragment{
    ViewDataBinding binding;
    public static String DIALOG_MODEL_KEY = "DialogModel";
    public static String DIALOG_VM_KEY = "DialogViewModel";
    public static int RESOURCE_EMPTY = 0;
    private int BRClass;
//    private int layout;

//    private Object dialogIntent;
//    private DialogModel dialogModel;
    private DialogParams dialogParams;
//    public DialogModel getDialogModel() {        return dialogModel;    }

    public final ObservableField<String> headerText = new ObservableField<>();
    public final ObservableField<String> messageText = new ObservableField<>();
    public final ObservableField<String> btnName = new ObservableField<>();
    public final ObservableField<Integer> colorTitle = new ObservableField<>();
    public final ObservableField<Integer> visibilityAttentionIcon = new ObservableField<>();



//    public static CustomDialog instance(DialogModel dialogModel) {
//        if(dialogModel.layoutId != RESOURCE_EMPTY) {
//            CustomDialog customDialog = new CustomDialog();
//            customDialog.layout = dialogModel.layoutId;
//            customDialog.dialogModel = dialogModel;
//            return customDialog;
//        } else {
//            return null;
//        }
//    }

    public static CustomDialog instance(DialogParams dialogParams, int BRClass) {
        CustomDialog customDialog = null;
        if(dialogParams.getDialogModel().layoutId != RESOURCE_EMPTY) {
            customDialog = new CustomDialog();
            //customDialog.layout = dialogParams.getDialogModel().layoutId;
            customDialog.dialogParams = dialogParams;
            customDialog.BRClass = BRClass;
        }
        return customDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);

        if (savedInstanceState != null) {
            dialogParams = Parcels.unwrap(savedInstanceState.getParcelable(DIALOG_MODEL_KEY));
            BRClass = savedInstanceState.getInt(DIALOG_VM_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, dialogParams.getDialogModel().layoutId, container, true);
        binding.setVariable(BRClass, this);
        setDialogParameters();
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable wrapped = Parcels.wrap(dialogParams);
        outState.putParcelable(DIALOG_MODEL_KEY, wrapped);
        outState.putInt(DIALOG_VM_KEY, BRClass);
    }

    private void setDialogParameters() {
        String header = dialogParams.isHasHeader() ? dialogParams.getDialogHeader() : getString(dialogParams.getDialogModel().resDialogHeader);
        headerText.set(header);

        String text = dialogParams.isHasText() ? dialogParams.getDialogText() : getString(dialogParams.getDialogModel().resDialogText);
        messageText.set(text);

        colorTitle.set(dialogParams.getDialogModel().colorTitle);
       // visibilityAttentionIcon.set(dialogParams.getDialogModel().visibilityIcon);
        if (dialogParams.getDialogModel().resBtnText > 0) btnName.set(getString(dialogParams.getDialogModel().resBtnText));
        this.setCancelable(false);
    }

    public void onClick(View view) {
        Integer dialogCommandModel = (Integer) view.getTag();
        if (dialogCommandModel != null) {
            RxBusCustomAction.instanceOf().setDialogCommand(new DialogCommand(dialogCommandModel, dialogParams.getDialogIntent()));
        }
    }

    public void onClickWithClose(View view) {
        onClick(view);
        dismiss();
    }
}



