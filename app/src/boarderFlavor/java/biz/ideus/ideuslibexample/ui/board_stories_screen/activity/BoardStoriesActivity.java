package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityBoardStoriesBinding;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardStories;
import biz.ideus.ideuslibexample.network.response.entity_model.Story;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_stories_screen.adapters.BoardStoryAdapter;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;

import static biz.ideus.ideuslibexample.R.id.item_layout;


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
        getBoardView().setBoardListener(getViewModel());
//        getBoardView().setBackgroundColor(getResources().getColor(R.color.black));
    }

    @Override
    public void rebuildBoardView(BoardStories data) {
//        Utils.toast(this, "rebuildBoardView " + data.getLists().get(0).getCards().size());
        BoardView boardView = getBoardView();
        boardView.clearBoard();
        for (Story story : data.getLists()) {
            BoardStoryAdapter boardStoryAdapter = new BoardStoryAdapter(story.getCards(), R.layout.item_board_record, item_layout, true);

            final View header = View.inflate(this, R.layout.column_header, null);
            ((TextView) header.findViewById(R.id.text)).setText(story.getName());
            ((TextView) header.findViewById(R.id.item_count)).setText("" + story.getCards().size());

            boardView.addColumnList(boardStoryAdapter, header, false);
            boardStoryAdapter.notifyDataSetChanged();
        }
        //boardView.invalidate();
        boardView.addColumnList()
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



