package biz.ideus.ideuslibexample.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.race604.drawable.wave.WaveDrawable;

import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.rx_buses.RxBusActionEditDialogBtn;

import static biz.ideus.ideuslibexample.dialogs.DialogModel.EDIT_TEXT_DIALOG;

/**
 * Created by blackmamba on 18.11.16.
 */

public class CustomAttentionDialog extends DialogFragment{
    public Drawable mWaveDrawable = new WaveDrawable(this.getActivity().getResources().getDrawable(R.drawable.logo_circle));
    private ViewDataBinding binding;
    public static String LAYOUT_KEY = "layout";
    public static String DIALOG_MODEL_KEY = "DialogModel";

    private int layout;

    public void setLayout(int layout) {
        this.layout = layout;
    }

    private Object dialogIntent;
    private DialogModel dialogModel;

    public DialogModel getDialogModel() {
        return dialogModel;
    }

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> aboutDialogTitle = new ObservableField<>();
    public final ObservableField<String> btnName = new ObservableField<>();
    public final ObservableField<Integer> colorTitle = new ObservableField<>();
    public final ObservableField<Integer> visibilityAttentionIcon = new ObservableField<>();

    public static CustomAttentionDialog instance(DialogModel dialogModel, @Nullable Object dialogIntent) {
        CustomAttentionDialog customAttentionDialog = new CustomAttentionDialog();
        customAttentionDialog.layout = dialogModel.layoutId;
        customAttentionDialog.dialogIntent = dialogIntent;
        customAttentionDialog.dialogModel = dialogModel;
        return customAttentionDialog;
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
        title.set(getString(getDialogModel().resDialogName));

        if (!getDialogModel().equals(EDIT_TEXT_DIALOG)) {
            colorTitle.set(getDialogModel().colorTitle);
            visibilityAttentionIcon.set(getDialogModel().visibilityIcon);
            aboutDialogTitle.set(getString(getDialogModel().resAboutDialogText));
            btnName.set(getString(getDialogModel().resBtnName));
        }


    }

//    @BindingAdapter("android:src")
//    public static void setImageDrawable(ImageView view, Drawable drawable) {
//        view.setImageDrawable(drawable);
//    }


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



