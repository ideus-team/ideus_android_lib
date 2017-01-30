package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.data.model.response.CheckUpdateAnswer;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.dialogs.DialogParams;
import biz.ideus.ideuslibexample.dialogs.DialogParamsBuilder;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;


/**
 * Created by blackmamba on 24.11.16.
 */
//@PerActivity
public class MainActivityVM extends AbstractViewModel<StartView> {

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        CheckVersion();
    }

    private void CheckVersion() {
        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.NONE);

        netApi.checkUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<CheckUpdateAnswer>(netSubscriberSettings) {
                    @Override
                    public void onNext(CheckUpdateAnswer checkUpdateAnswer) {

                        DialogParams dialogParams = new DialogParamsBuilder()
                                .setDialogModel(DialogModel.NEW_VERSION_MUST_HAVE_DIALOG)
                                .setDialogHeader(checkUpdateAnswer.data.getRelease().getVersion())
                                .setDialogText(checkUpdateAnswer.data.getRelease().getDescription())
                                .createDialogParams();
                        RxBusShowDialog.instanceOf().setRxBusShowDialog(dialogParams);
                    }
                });
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
    }

}

