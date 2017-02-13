package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.network.request.CreateBoardRequest;
import biz.ideus.ideuslibexample.network.response.CreateBoardResponse;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.enums.BoardCommands.NEW_BOARD;
import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.boardRequeryApi;

/**
 * Created by blackmamba on 09.02.17.
 */

public class CreateBoardVM extends MainBoardVM implements MainBoardVM.OnClickActionBtnListener {


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        setOnClickActionBtnListener(this);


        webSocketClient.addResponseListener(this, new SocketResponseListener<CreateBoardResponse>(CreateBoardResponse.class) {
            @Override
            public void onGotResponseData(CreateBoardResponse data) {
                boardRequeryApi.storeBoard(data.getData().getBoardEntity())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(boardEntities -> {
                    RxBoardCommandEvent.instanceOf().setRxBoardCommandEvent(new BoardCommandWrapper(NEW_BOARD, data.getData().getBoardEntity()));
                    ((MainActivity)context).onBackPressed();
                    Utils.toast(context, context.getString(R.string.board_created));
                });

            }
        });
    }

    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
        boardName.set("");
       actionButtonText.set(context.getString(R.string.create_board));
    }

    private void createBoard() {
        if (!boardName.get().isEmpty()) {
            webSocketClient.sendMessage(new CreateBoardRequest(boardName.get()));
        } else {
            Utils.toast(context, context.getString(R.string.name_is_empty));
        }
    }

    @Override
    public void onClickActionBtn() {
        createBoard();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.create_board);
    }

}
