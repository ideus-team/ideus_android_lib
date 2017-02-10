package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentUpdateBoardBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model.UpdateBoardVM;
import biz.ideus.ideuslibexample.utils.BoardAppConstants;

/**
 * Created by blackmamba on 09.02.17.
 */

public class UpdateBoardFragment extends BaseFragment<BoardMainView, UpdateBoardVM, FragmentUpdateBoardBinding>
        implements BoardMainView {

    private String boardId;

    public UpdateBoardFragment setBoardId(String boardId) {
        this.boardId = boardId;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            boardId = savedInstanceState.getString(BoardAppConstants.BOARD_ID);
        }
        getViewModel().setUpdateBoardID(boardId);
        setModelView(this);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BoardAppConstants.BOARD_ID, boardId);
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_update_board, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<UpdateBoardVM> getViewModelClass() {
        return UpdateBoardVM.class;
    }
}
