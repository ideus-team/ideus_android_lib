package biz.ideus.ideuslibexample.data.remote;

import android.util.Log;

import java.io.IOException;

import biz.ideus.ideuslib.Utils.NetworkUtil;
import biz.ideus.ideuslib.dialogs.DialogParams;
import biz.ideus.ideuslib.dialogs.DialogParamsBuilder;
import biz.ideus.ideuslib.dialogs.RxBusShowDialog;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.data.DialogStore;
import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 12.12.2016.
 */

public class NetSubscriber <T extends ServerAnswer> extends Subscriber<T> {
    NetSubscriberSettings subscriberSettings;
    String errorBody, errorMessage;
    int errorCode;
   // boolean isSucces;

    public NetSubscriber(NetSubscriberSettings subscriberSettings) {
        super();
        this.subscriberSettings = subscriberSettings;
    }


    @Override
    public void onStart() {
        super.onStart();
     //   isSucces = true;
        showProgress();
    }

    @Override
    public void onCompleted() {
//        if (isSucces)
            hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        hideProgress();
        try {
            Log.d("NetSubscriber", "onError " + e.getMessage());


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
            } else {
                Log.d("NetSubscriber", "DialogParams " );
                DialogParams dialogParams = new DialogParamsBuilder()
                        .setDialogModel(DialogStore.LOGIN_ATTENTION())
                        .setDialogText(e.getMessage())
                        .createDialogParams();
                RxBusShowDialog.instanceOf().setRxBusShowDialog(dialogParams);
//                Utils.toast(SampleApplication.getInstance().getApplicationContext(), e.getMessage());
            }
        } catch (Exception exMain) {
            exMain.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {
        Log.d("NetSubscriber", "onNext");
//        if (!t.message.isEmpty()){
//            onError(new Throwable(t.message));
//            isSucces = false;
//        }

    }

    private void showProgress() {

        switch (subscriberSettings.getProgressType()) {
            case CIRCULAR: {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.PROGRESS_DIALOG());
                break;
            }
            case LINEAR: {
                subscriberSettings.showLinearProgress();
                break;
            }
        }
    }


    private void hideProgress() {
        switch (subscriberSettings.getProgressType()) {
            case CIRCULAR: {
                RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.HIDE_PROGRESS_DIALOG());
                break;
            }
            case LINEAR: {
                subscriberSettings.hideLinearProgress();
                break;
            }
        }
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public enum ProgressType {
        NONE,
        LINEAR,
        CIRCULAR
    }


}
