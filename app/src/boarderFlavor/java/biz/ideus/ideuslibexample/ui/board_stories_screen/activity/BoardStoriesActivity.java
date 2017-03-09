package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityBoardStoriesBinding;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;


/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesActivity extends BaseActivity<BoardStoriesView, BoardStoriesVM, ActivityBoardStoriesBinding>
        implements BoardStoriesView {


    @Override
    public BoardView getBoardView() {
        return binding.boardView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModelView(this);
    }

    @Override
    public void rebuildBoardView(BoardStoryData data) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public Class<BoardStoriesVM> getViewModelClass() {
        return BoardStoriesVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_board_stories, BR.viewModel, this);
    }
}



