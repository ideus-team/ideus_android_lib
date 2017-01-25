package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.LinkedHashSet;
import java.util.Set;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.PeopleAdapter;
import biz.ideus.ideuslibexample.data.model.request.AddAndDeleteFavoriteRequest;
import biz.ideus.ideuslibexample.data.model.request.GetPeopleRequest;
import biz.ideus.ideuslibexample.data.model.response.PeopleAnswer;
import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.rx_buses.RxBusFetchPeopleListEvent;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.user_details_fragment.UserDetailsFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;
import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment.PeopleFragmentVM.FetchPeopleMode.DEFAULT_MODE;
import static biz.ideus.ideuslibexample.utils.Constants.PEOPLE_ID;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragmentVM extends BaseSearchVM implements PeopleAdapter.OnPeopleClickListener {
    private PeopleAdapter adapter;

    public static final int LOAD_LIMIT = 10;
    public int currentOffset = 0;
    private Set<PeopleEntity> linkedPeopleEntities = new LinkedHashSet<>();
    public ObservableField<Boolean> isShowLinearProgress = new ObservableField<>();
    private String searchText = "";


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
        currentOffset = linkedPeopleEntities.size();
        RxBusFetchPeopleListEvent.getInstance().setRxBusFetchPeopleListEvent(linkedPeopleEntities);
        fetchPeopleRequest(DEFAULT_MODE, currentOffset);

    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.people);
    }


    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        searchText = text.toString();
        currentOffset = 0;
        linkedPeopleEntities.clear();
        RxBusFetchPeopleListEvent.getInstance().setRxBusFetchPeopleListEvent(linkedPeopleEntities);
        fetchPeopleRequest(DEFAULT_MODE, currentOffset);
    }

    @Override
    public void onCancelClick(View view) {
        ((BaseActivity) context).hideKeyboard();
        visibilitySearch.set(View.GONE);
        isFocus.set(false);
    }

    @Override
    public String getSearchText() {
        return searchText;
    }

    @Override
    public void onClickItem(int position, PeopleEntity peopleEntity) {
        ((BaseActivity) context).hideKeyboard();
        Bundle bundle = new Bundle();
        bundle.putString(PEOPLE_ID, peopleEntity.getIdent());
        ((BaseActivity) context).addFragmentToBackStack(new UserDetailsFragment(), bundle, true, null);
    }

    @Override
    public void onClickFavourite(int position, PeopleEntity peopleEntity) {
        if(peopleEntity.isFavorite()){
            addOrDeleteFavouritesRequest(netApi.deleteFavorite(new AddAndDeleteFavoriteRequest(peopleEntity.getIdent())), position);
        } else {
            addOrDeleteFavouritesRequest(netApi.addFavorite(new AddAndDeleteFavoriteRequest(peopleEntity.getIdent())), position);
        }
    }

    private void addOrDeleteFavouritesRequest(Observable<ServerAnswer> request, int positionInAdapter){
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<ServerAnswer>(new NetSubscriberSettings(this, NetSubscriber.ProgressType.NONE)){
                    @Override
                    public void onNext(ServerAnswer serverAnswer) {
                        adapter.changeFavouriteStatus(positionInAdapter);
                    }
                });
    }


    public void fetchPeopleRequest(FetchPeopleMode fetchMode, int offset) {
        currentOffset = offset;
        GetPeopleRequest getPeopleRequest = new GetPeopleRequest(searchText).setLimit(LOAD_LIMIT).setOffset(currentOffset);
        netApi.getPeople(getPeopleRequest)
                .lift(new CheckError<>())
                .map(peopleAnswer -> {
                    requeryApi.storePeopleListPagination(peopleAnswer.data.getPeopleEntities());
                    return peopleAnswer;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<PeopleAnswer>(new NetSubscriberSettings(this, NetSubscriber.ProgressType.LINEAR)) {

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
                        switch (fetchMode) {
                            case DEFAULT_MODE:
                                if (!answer.data.getPeopleEntities().isEmpty()) {
                                    refreshPeopleList(answer);
                                }
                                break;
                            case FETCH_MORE_MODE:
                                refreshPeopleList(answer);
                                break;
                        }
                    }
                });
    }


    private void refreshPeopleList(PeopleAnswer answer) {
        linkedPeopleEntities.addAll(answer.data.getPeopleEntities());
        currentOffset = linkedPeopleEntities.size();
        RxBusFetchPeopleListEvent.getInstance().setRxBusFetchPeopleListEvent(linkedPeopleEntities);
    }


    @Override
    public void setVisibilityLinearProgress(boolean isShowProgress) {
        isShowLinearProgress.set(isShowProgress);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //test
        requeryApi.deletePeopleList();

    }

    public enum FetchPeopleMode {
        DEFAULT_MODE, FETCH_MORE_MODE;
    }
}

