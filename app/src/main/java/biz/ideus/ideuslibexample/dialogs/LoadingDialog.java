package biz.ideus.ideuslibexample.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.LoadingDialogBinding;
import rx.Subscription;

/**
 * Created by blackmamba on 13.12.16.
 */

public class LoadingDialog extends DialogFragment {
    private LoadingDialogBinding binding;
    private LoadingDialog loadingDialog;
    private Subscription rxLoadingDialogSubscription;
    private DialogModel dialogModel;

    public DialogModel getDialogModel() {
        return dialogModel;
    }

    public static LoadingDialog instance(DialogModel dialogModel) {
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setCancelable(false);
        loadingDialog.dialogModel = dialogModel;
        return loadingDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        //   rxLoadingDialogSubscription = startRxBusLoadingDialogSubscription();
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, R.layout.loading_dialog, container, true);
        return binding.getRoot();
    }

//    public Subscription startRxBusLoadingDialogSubscription() {
//        return RxBusLoadingDialog.instanceOf().getEvents()
//                .subscribe(dialogComand -> {
//                    switch (dialogComand){
//                        case HIDE_LOADING:
//                            this.dismiss();
//                            break;
//                    }
//                });
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (rxLoadingDialogSubscription != null && !rxLoadingDialogSubscription.isUnsubscribed())
//            rxLoadingDialogSubscription.unsubscribe();
    }
}
