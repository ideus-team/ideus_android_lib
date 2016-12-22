package biz.ideus.ideuslibexample.dialogs;

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
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.rx_buses.RxBusActionEditDialogBtn;


/**
 * Created by blackmamba on 18.11.16.
 */

public class CustomDialog extends DialogFragment{
//    public WaveDrawable mWaveDrawable = new WaveDrawable(SampleApplication.getInstance().getApplicationContext(), R.drawable.earth);
    private ViewDataBinding binding;
    public static String LAYOUT_KEY = "layout";
    public static String DIALOG_MODEL_KEY = "DialogModel";
    public static int RESOURCE_EMPTY = 0;

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

    public static CustomDialog instance(DialogParams dialogParams) {
        CustomDialog customDialog = null;
        if(dialogParams.getDialogModel().layoutId != RESOURCE_EMPTY) {
            customDialog = new CustomDialog();
            //customDialog.layout = dialogParams.getDialogModel().layoutId;
            customDialog.dialogParams = dialogParams;
        }
        return customDialog;
    }



//    public void setLayout(int layout) {        this.layout = layout;    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        if (savedInstanceState != null) {
            dialogParams = Parcels.unwrap(savedInstanceState.getParcelable(DIALOG_MODEL_KEY));
           // layout = savedInstanceState.getInt(LAYOUT_KEY);
           // dialogParams.setDialogModel((DialogModel) savedInstanceState.getSerializable(DIALOG_MODEL_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, dialogParams.getDialogModel().layoutId, container, true);
        binding.setVariable(BR.customVM, this);
        setDialogParameters();
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable wrapped = Parcels.wrap(dialogParams);
        outState.putParcelable(DIALOG_MODEL_KEY, wrapped);

//        outState.putInt(LAYOUT_KEY, layout);
//        outState.putSerializable(DIALOG_MODEL_KEY, dialogParams);
    }

    private void setDialogParameters() {
        String header = dialogParams.isHasHeader() ? dialogParams.getDialogHeader() : getString(dialogParams.getDialogModel().resDialogName);
        headerText.set(header);

        String text = dialogParams.isHasText() ? dialogParams.getDialogText() : getString(dialogParams.getDialogModel().resAboutDialogText);
        messageText.set(text);

        colorTitle.set(dialogParams.getDialogModel().colorTitle);
        visibilityAttentionIcon.set(dialogParams.getDialogModel().visibilityIcon);
        btnName.set(getString(dialogParams.getDialogModel().resBtnName));
        this.setCancelable(false);
    }



//    @BindingAdapter("bind:waveImage")
//    @DebugLog
//    public static void waveImage(ImageView view, Drawable drawable) {
//        view.setImageDrawable(drawable);
//        ((WaveDrawable)drawable).setWaveAmplitude(100);
//        ((WaveDrawable)drawable).setWaveLength(270);
//        ((WaveDrawable)drawable).setWaveSpeed(8);
//        ((WaveDrawable)drawable).setLevel(5000);
//        ((WaveDrawable)drawable).invalidateSelf();
//        drawable.invalidateSelf();
//    }


    public void onClick(View view) {
        DialogCommandModel dialogCommandModel = (DialogCommandModel) view.getTag();
        if (dialogCommandModel != null) {
            RxBusActionEditDialogBtn.instanceOf().setDialogCommand(new DialogCommand(dialogCommandModel, dialogParams.getDialogIntent()));
        }
        dismiss();
    }

}



