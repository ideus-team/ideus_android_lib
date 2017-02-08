package biz.ideus.ideuslib.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.parceler.Parcels;

import biz.ideus.ideuslib.R;


//import biz.ideus.ideuslib.BR;


/**
 * example usage :
 * dialog = CustomDialog.instance(dialogParams, BR.customVM);
 * dialog.show(getFragmentManager(), "MyCustomDialog");
 */
public class CustomDialog extends DialogFragment{
    ViewDataBinding binding;
    public static String DIALOG_MODEL_KEY = "DialogModel";
    public static String DIALOG_VM_KEY = "DialogViewModel";
    public static int RESOURCE_EMPTY = 0;
    private int BRClass;
    private DialogParams dialogParams;
    public final ObservableField<String> headerText = new ObservableField<>();
    public final ObservableField<String> messageText = new ObservableField<>();
    public final ObservableField<String> btnName = new ObservableField<>();
    public final ObservableField<Integer> colorTitle = new ObservableField<>();
    public final ObservableField<Integer> visibilityAttentionIcon = new ObservableField<>();



    /**
    @param BRClass id binding variable in layout resource
    @param dialogParams {@link DialogParams}
     **/
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
        String header = "";
        if (dialogParams.isHasHeader()) {
            header = dialogParams.getDialogHeader();
        } else if (dialogParams.getDialogModel().resDialogHeader > 0) {
            header = getString(dialogParams.getDialogModel().resDialogHeader);
        }
        headerText.set(header);


        String text = "";
        if (dialogParams.isHasText()) {
            text = dialogParams.getDialogText();
        } else if (dialogParams.getDialogModel().resDialogText > 0) {
            text = getString(dialogParams.getDialogModel().resDialogText);
        }
        messageText.set(text);

        colorTitle.set(dialogParams.getDialogModel().colorTitle);
        if (dialogParams.getDialogModel().resBtnText > 0) btnName.set(getString(dialogParams.getDialogModel().resBtnText));
        this.setCancelable(false);
    }

    /**
     @param view - View
     process click event, send to {@link RxBusCustomAction} Tag from Sender View
     **/
    public void onClick(View view) {
        Log.d("dialogCommandModel",view.getTag() + "");
        Integer dialogCommandModel = (Integer) view.getTag();
        if (dialogCommandModel != null) {
            RxBusCustomAction.instanceOf().setDialogCommand(new DialogCommand(dialogCommandModel, dialogParams.getDialogIntent()));
            dismiss();
        }
    }
    /**
    @param view - View
    process click event, send to {@link RxBusCustomAction} Tag from Sender View, then close dialog
    **/
    public void onClickWithClose(View view) {
        onClick(view);
        dismiss();
    }
}



