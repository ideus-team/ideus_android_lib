package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.network.request.UpdateBoardRequest;
import biz.ideus.ideuslibexample.network.response.UpdateBoardResponse;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


        webSocketClient.addResponseListener(this, new SocketResponseListener<UpdateBoardResponse>(UpdateBoardResponse.class) {
            @Override
            public void onGotResponseData(UpdateBoardResponse data) {
                boardRequeryApi.storeBoard(data.getData().getBoardEntity())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(boardEntities -> {
                    RxBoardCommandEvent.instanceOf().setRxBoardCommandEvent(new BoardCommandWrapper(UPDATE_BOARD, data.getData().getBoardEntity()));
                    ((MainActivity)context).onBackPressed();
                    Utils.toast(context, context.getString(R.string.board_updated));
                });


            }
        });
    }

    private void getBoardForUpdate(String boardId){
        boardRequeryApi.getBoardById(boardId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BoardEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("erroree", e.getMessage());
                    }

                    @Override
                    public void onNext(BoardEntity boardEntity) {
                        updateBoardEntity = boardEntity;
                        boardName.set(updateBoardEntity.getName());
                    }
                });
    }

    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
        actionButtonText.set(context.getString(R.string.update_board));
    }


    private void updateBoard() {
        if (!boardName.get().isEmpty()) {
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
