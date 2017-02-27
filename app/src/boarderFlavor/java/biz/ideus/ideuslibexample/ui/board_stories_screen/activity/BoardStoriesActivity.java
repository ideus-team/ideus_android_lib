package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityBoardStoriesBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_stories_screen.BoardStoriesView;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;

import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;


/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesActivity extends BaseActivity<BoardStoriesView, BoardStoriesVM, ActivityBoardStoriesBinding>
        implements BoardStoriesView {

 //   private StoriesAdapter adapter;
    private String boardID;
 //   private BoardStoriesVMListener boardStoriesListener;

    public String getBoardID() {
        return boardID;
    }

    @Override
    public BoardView getBoardView() {
        return binding.boardView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardID = getBoardID(savedInstanceState, getIntent());
        setModelView(this);


//        adapter = new StoriesAdapter();
//        RecyclerView recyclerView = getBinding().rViewBoardsDetail;
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(adapter);
//        getViewModel().setAdapter(adapter);
//        adapter.setScrollToBottomListener(position -> recyclerView.postDelayed(() -> recyclerView.smoothScrollToPosition(position), 400));
//        boardStoriesListener = getViewModel().getBoardStoriesListener();
//
//
//        getBinding().etNameList.setOnEditorActionListener((v, actionId, event) -> {
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                boardStoriesListener.onClickDone();
//                return true;
//            }
//            return false;
//        });
    }

//    @Override
//    public void onBackPressed() {
//        boardStoriesListener.onBackPressed();
//    }


    private String getBoardID(Bundle savedInstanceState, Intent intent) {
        String boardId = "";
        if (savedInstanceState != null) {
            boardId = savedInstanceState.getString(BOARD_ID);
        }
        if (intent != null) {
            boardId = intent.getStringExtra(BOARD_ID);
        }
        return boardId;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BOARD_ID, boardID);
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



