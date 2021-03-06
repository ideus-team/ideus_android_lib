package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentHomeBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragment extends BaseFragment<StartView, HomeFragmentVM, FragmentHomeBinding>
implements StartView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
//        getBinding().rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        getBinding().rv.setAdapter(getViewModel().getAdapter());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
        RecyclerView rv = getBinding().rv;
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        getBinding().rv.setAdapter(getViewModel().getAdapter());
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_home, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<HomeFragmentVM> getViewModelClass() {
        return HomeFragmentVM.class;
    }

}

