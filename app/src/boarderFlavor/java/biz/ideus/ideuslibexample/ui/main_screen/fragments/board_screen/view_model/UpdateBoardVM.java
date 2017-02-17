package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.network.request.UpdateBoardRequest;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.utils.Utils;

import static biz.ideus.ideuslibexample.enums.BoardCommands.UPDATE_BOARD;
import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.boardRequeryApi;

/**
 * Created by blackmamba on 09.02.17.
 */

public class UpdateBoardVM extends MainBoardVM implements MainBoardVM.OnClickActionBtnListener {

    private BoardEntity updateBoardEntity;

    public UpdateBoardVM setUpdateBoardID(String boardID) {
        getBoardForUpdate(boardID);
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        setOnClickActionBtnListener(this);
    }

    private void getBoardForUpdate(String boardId) {
        if(updateBoardEntity == null) {
            updateBoardEntity = boardRequeryApi.getBoardById(boardId);
            boardName.set(updateBoardEntity.getName());
        }
    }

    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
        actionButtonText.set(context.getString(R.string.update_board));
    }


    private void updateBoard() {
        if (!boardName.get().isEmpty()) {
            RxBoardCommandEvent.instanceOf().setRxBoardCommandEvent( new BoardCommandWrapper(UPDATE_BOARD, boardName.get()));
            webSocketClient.sendMessage(new UpdateBoardRequest(boardName.get(), updateBoardEntity.getIdent()));
        } else {
            Utils.toast(context, context.getString(R.string.name_is_empty));
        }
    }

    @Override
    public void onClickActionBtn() {
        updateBoard();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.update_board);
    }
}
