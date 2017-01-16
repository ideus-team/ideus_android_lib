package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.PeopleAdapter;
import biz.ideus.ideuslibexample.data.model.request.GetPeopleRequest;
import biz.ideus.ideuslibexample.data.model.response.PeopleAnswer;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragmentVM extends BaseSearchVM implements PeopleAdapter.OnPeopleClickListener{
    private PeopleAdapter adapter;
    private List<PeopleEntity> peopleEntities = new ArrayList<>();

    public void setAdapter(PeopleAdapter adapter) {
        this.adapter = adapter;

    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        netApi.getPeople(new GetPeopleRequest())
                .lift(new CheckError<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<PeopleAnswer>(new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR)) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.CHANGE_PASSWORD_SUCCESS);
                    }
                    @Override
                    public void onNext(PeopleAnswer answer) {
                        peopleEntities = answer.data.getPeopleEntities();
                     //   adapter.setPeopleEntities(peopleEntities);
                    }
                });
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
      //  adapter.setOnPeopleClickListener(this);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.people);
    }



    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        Log.d("CharSequence", text.toString());
    }

    @Override
    public void onClickItem(int position, PeopleEntity peopleEntity) {

    }
}

