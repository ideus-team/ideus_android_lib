package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

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
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.user_details_fragment.UserDetailsFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;
import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragmentVM extends BaseSearchVM implements PeopleAdapter.OnPeopleClickListener {
    private PeopleAdapter adapter;
    public static final int LOAD_LIMIT = 10;
    public int currentOffset = 0;
    private List<PeopleEntity> peopleEntities = new ArrayList<>();
    private String term;


    public void setAdapter(PeopleAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnPeopleClickListener(this);


    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        currentOffset = peopleEntities.size();
        adapter.setPeopleEntities(peopleEntities);
        fetchPeopleRequest();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.people);
    }


    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        term = text.toString();
        currentOffset = 0;
        peopleEntities.clear();
        fetchPeopleRequest();
    }

    @Override
    public void onCancelClick(View view) {
        ((BaseActivity) context).hideKeyboard();
        visibilitySearch.set(View.GONE);
        isFocus.set(false);
    }

    @Override
    public void onClickItem(int position, PeopleEntity peopleEntity) {
        ((BaseActivity) context).hideKeyboard();
        ((BaseActivity) context)
                .addFragmentToBackStack(new UserDetailsFragment().setPeopleId(peopleEntity.getIdent()), null, true, null);
    }


    public void fetchPeopleRequest() {
        GetPeopleRequest getPeopleRequest = new GetPeopleRequest(term).setLimit(LOAD_LIMIT).setOffset(currentOffset);
        netApi.getPeople(getPeopleRequest)
                .lift(new CheckError<>())
                .map(peopleAnswer -> {
                    requeryApi.storePeopleListPagination(peopleAnswer.data.getPeopleEntities());
                    return peopleAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<PeopleAnswer>(new NetSubscriberSettings(NetSubscriber.ProgressType.NONE)) {

                    @Override
                    public void onNext(PeopleAnswer answer) {
                        peopleEntities.addAll(answer.data.getPeopleEntities());
                        currentOffset = peopleEntities.size();
                        adapter.setPeopleEntities(peopleEntities);
                    }
                });
    }

    public void fetchMorePeopleRequest(int page) {
        currentOffset = page;
        GetPeopleRequest getPeopleRequest = new GetPeopleRequest(term).setLimit(LOAD_LIMIT).setOffset(currentOffset);
        netApi.getPeople(getPeopleRequest)
                .lift(new CheckError<>())
                .map(peopleAnswer -> {
                    requeryApi.storePeopleListPagination(peopleAnswer.data.getPeopleEntities());
                    return peopleAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<PeopleAnswer>(new NetSubscriberSettings(NetSubscriber.ProgressType.NONE)) {

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        EndlessRecyclerViewScrollListener.setLoaded(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        EndlessRecyclerViewScrollListener.setLoaded(true);
                    }

                    @Override
                    public void onNext(PeopleAnswer answer) {
                        if (!answer.data.getPeopleEntities().isEmpty()) {
                            peopleEntities.addAll(answer.data.getPeopleEntities());
                            currentOffset = peopleEntities.size();
                            adapter.setPeopleEntities(peopleEntities);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requeryApi.deletePeopleList();

    }
}

