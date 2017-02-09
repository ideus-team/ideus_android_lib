package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentBoardBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model.MainBoardVM;

/**
 * Created by blackmamba on 09.02.17.
 */

public abstract class AbstractBoardFragment extends BaseFragment<BoardMainView, MainBoardVM, FragmentBoardBinding>
        implements BoardMainView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // fragmentComponent().inject(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
        getBinding().setViewModel(getCurrentViewModel());

    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_board, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<MainBoardVM> getViewModelClass() {
        return MainBoardVM.class;
    }

    public abstract BoardMainView getCurrentViewModel();
}
