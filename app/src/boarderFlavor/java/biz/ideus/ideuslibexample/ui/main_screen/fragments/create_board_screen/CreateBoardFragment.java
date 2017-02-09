package biz.ideus.ideuslibexample.ui.main_screen.fragments.create_board_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentCreateBoardBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;

/**
 * Created by blackmamba on 09.02.17.
 */

public class CreateBoardFragment extends BaseFragment<BoardMainView, CreateBoardVM, FragmentCreateBoardBinding>
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

    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_create_board, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<CreateBoardVM> getViewModelClass() {
        return CreateBoardVM.class;
    }
}
