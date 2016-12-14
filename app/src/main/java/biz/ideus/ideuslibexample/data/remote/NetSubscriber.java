package biz.ideus.ideuslibexample.data.remote;

import android.util.Log;

import java.io.IOException;

import biz.ideus.ideuslib.Utils.NetworkUtil;
import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

import static biz.ideus.ideuslibexample.dialogs.DialogModel.HIDE_LOADING_DIALOG;
import static biz.ideus.ideuslibexample.dialogs.DialogModel.SHOW_LOADING_DIALOG;

/**
 * Created by user on 12.12.2016.
 */

public class NetSubscriber <T> extends Subscriber<T> {
    NetSubscriberSettings subscriberSettings;
    String errorBody, errorMessage;
    int errorCode;

    public NetSubscriber(NetSubscriberSettings subscriberSettings) {
        super();
        this.subscriberSettings = subscriberSettings;
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgress();
    }

    @Override
    public void onCompleted() {
        hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        Log.d("CustomSubscriber",e.getMessage() + "");
        try {
            Log.d("CustomSubscriber", "onError " + e.getMessage());
            hideProgress();

            String errorBody;
            int errorCode = 0;
//            ErrorAnswer errorAnswer = null;

            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                if (httpException.response() != null) {
                    errorCode = httpException.response().code();
                    if (httpException.response().errorBody() != null && errorCode != 500) {
                        try {
                            errorBody = httpException.response().errorBody().string();
//                            errorAnswer = ServiceGenerator.gson.fromJson(errorBody, ErrorAnswer.class);
                        } catch (IOException e1) {
                            Log.d("JSON", "бодяга пришла");
                        }
                    }
                }

//                if (errorCode == 418 && errorAnswer != null) {
//                    Utils.toast(errorAnswer.message);
//                }
//                if (NetworkUtil.isNetworkConnected(subscriberSettings.baseActivity)) {
//                    return;
//                }
            }


            if (!NetworkUtil.isNetworkConnected(SampleApplication.getInstance().getApplicationContext())) {

//                if (!PreferencesUtil.isHasShowedAlert(PreferencesUtil.NO_INTERNET_CONNECTION)) {
//
//                    subscriberSettings.baseActivity.runOnUiThread(()
//                            -> new ServerErrorsDialog(subscriberSettings.baseActivity, "Error"
//                            , subscriberSettings.baseActivity.getString(R.string.disable_enternet_connection)
//                            , (dialog, which) -> {
//                        dialog.cancel();
//                        if (subscriberSettings.errorDialogClickListener != null) {
//                            subscriberSettings.errorDialogClickListener.onClick(dialog, 0);
//                        }
//                    }).show());
//
//
//                }
//                PreferencesUtil.alertWasShowed(PreferencesUtil.NO_INTERNET_CONNECTION, true);
            } else {
                Utils.toast(SampleApplication.getInstance().getApplicationContext(), e.getMessage());
            }
        } catch (Exception exMain) {
            exMain.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {
        Log.d("CustomSubscriber", "onNext");
    }

    private void showProgress() {

        switch (subscriberSettings.getProgressType()) {
            case CIRCULAR: {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(SHOW_LOADING_DIALOG);
                break;
            }
//            case LINEAR: {
//                subscriberSettings.showLinearProgress();
//                break;
//            }
        }
    }


    private void hideProgress() {
        switch (subscriberSettings.getProgressType()) {
            case CIRCULAR: {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(HIDE_LOADING_DIALOG);
                break;
            }
//            case LINEAR: {
//                subscriberSettings.hideLinearProgress();
//                break;
//            }
        }
    }

    public enum ProgressType {
        NONE,
        LINEAR,
        CIRCULAR
    }
}
