package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.PeopleAdapter;
import biz.ideus.ideuslibexample.databinding.FragmentPeopleBinding;
import biz.ideus.ideuslibexample.rx_buses.RxBusFetchPeopleListEvent;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragment extends BaseFragment<StartView, PeopleFragmentVM, FragmentPeopleBinding> implements StartView {

    private PeopleAdapter adapter;
    private Subscription fetchPeopleEventSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        fetchPeopleEventSubscription = getFetchPeopleEventSubscription();
        adapter = new PeopleAdapter();
        getBinding().rViewPeople.setAdapter(adapter);
        getBinding().rViewPeople.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        getViewModel().setAdapter(adapter);

        requeryApi.getPeopleEntity()
                .subscribe(peopleEntities -> {
                    adapter.setPeopleEntities(peopleEntities.toList());
                });
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);

    }


    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_people, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<PeopleFragmentVM> getViewModelClass() {
        return PeopleFragmentVM.class;
    }

private Subscription getFetchPeopleEventSubscription(){
        return RxBusFetchPeopleListEvent.instanceOf()
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(peopleEntities -> {
                    adapter.setPeopleEntities(peopleEntities);
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fetchPeopleEventSubscription != null && !fetchPeopleEventSubscription.isUnsubscribed())
            fetchPeopleEventSubscription.unsubscribe();
    }
}
