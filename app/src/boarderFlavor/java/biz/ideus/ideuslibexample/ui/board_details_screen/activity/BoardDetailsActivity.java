package biz.ideus.ideuslibexample.ui.board_details_screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.inputmethod.EditorInfo;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.CardsAdapter;
import biz.ideus.ideuslibexample.databinding.ActivityBoardDetailsBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardDetailsVMListener;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardDetailsView;
import biz.ideus.ideuslibexample.ui.board_details_screen.item_helper.OnStartDragListener;

import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;


/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardDetailsActivity extends BaseActivity<BoardDetailsView, BoardDetailsVM, ActivityBoardDetailsBinding>
        implements BoardDetailsView , OnStartDragListener{

    private CardsAdapter adapter;
    private String boardID;
    private ItemTouchHelper itemTouchHelper;
    private BoardDetailsVMListener boardDetailsListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModelView(this);
        boardID = getBoardID(savedInstanceState, getIntent());


        adapter = new CardsAdapter(this);
        RecyclerView recyclerView = getBinding().rViewBoardsDetail;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        getViewModel().setAdapter(adapter);
        getViewModel().setBoardId(boardID);
        boardDetailsListener = getViewModel().getBoardDetailsListener();
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//        itemTouchHelper = new ItemTouchHelper(callback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);

        getBinding().etNameList.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                boardDetailsListener.onClickDone();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        boardDetailsListener.onBackPressed();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
       // itemTouchHelper.startDrag(viewHolder);
    }


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
    public Class<BoardDetailsVM> getViewModelClass() {
        return BoardDetailsVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_board_details, BR.viewModel, this);
    }
}



