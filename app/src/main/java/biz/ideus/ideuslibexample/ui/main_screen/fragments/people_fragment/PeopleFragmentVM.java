package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.request.BaseRequestModel;
import biz.ideus.ideuslibexample.data.model.request.ResetPasswordRequest;
import biz.ideus.ideuslibexample.data.model.response.ResetPasswordAnswer;
import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragmentVM extends BaseSearchVM {

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        netApi.getContacts(new BaseRequestModel())
                .lift(new CheckError<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<ServerAnswer>(new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR)) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.CHANGE_PASSWORD_SUCCESS);
                    }
                    @Override
                    public void onNext(ServerAnswer answer) {

                    }
                });
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.people);
    }



    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        Log.d("CharSequence", text.toString());
    }
}

