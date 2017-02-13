package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAuthorisedResponse;
import biz.ideus.ideuslibexample.enums.BoardClickActionTag;
import biz.ideus.ideuslibexample.network.request.GetBoardListRequest;
import biz.ideus.ideuslibexample.network.response.GetBoardsResponse;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.CreateBoardFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.UpdateBoardFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM implements BoardsAdapter.OnSelectClickListener {

    private List<BoardEntity> boardsEntityList = new ArrayList<>();
    private BoardsAdapter adapter;
    private Subscription boardCommandEventSubscription;


    public static BoardRequeryApi boardRequeryApi;


    public void setAdapter(BoardsAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnSelectClickListener(this);
        adapter.setBoardEntities(boardsEntityList);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (Hawk.contains(Constants.USER_TOKEN)) {
            boardRequeryApi = BoardRequeryApi.getInstance();
            boardCommandEventSubscription = getBoardCommandEventSubscription();
            getBoardListFromDB();
            initSocketlisteners();
        }
    }


    private void initSocketlisteners() {
        webSocketClient.addResponseListener(this, new SocketResponseListener<SocketAuthorisedResponse>(SocketAuthorisedResponse.class) {
            @Override
            public void onGotResponseData(SocketAuthorisedResponse data) {
                getBoards();
            }
        });

        webSocketClient.addResponseListener(this, new SocketResponseListener<GetBoardsResponse>(GetBoardsResponse.class) {
            @Override
            public void onGotResponseData(GetBoardsResponse data) {
                boardRequeryApi.storeBoardList(data.getData().getBoardsEntitysList())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(boardEntities -> {
                    boardsEntityList = (List<BoardEntity>) boardEntities;
                    adapter.setBoardEntities(boardsEntityList);
                });
            }
        });
    }

    private void getBoardListFromDB() {
        Observable.just(boardRequeryApi.getBoardList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boardEntities -> {
                    boardsEntityList = boardEntities;
                    adapter.setBoardEntities(boardsEntityList);
                });
    }

    private Subscription getBoardCommandEventSubscription() {
        return RxBoardCommandEvent.instanceOf().getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boardCommandWrapper -> {
                    switch (boardCommandWrapper.getBoardCommand()) {
                        case NEW_BOARD:
                            adapter.setNewBoardToList(boardCommandWrapper.getBoardEntity());
                            break;
                        case UPDATE_BOARD:
                            adapter.updateBoardInList(boardCommandWrapper.getBoardEntity());
                            break;
                    }
                });
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
    }

    private void getBoards() {
        webSocketClient.sendMessage(new GetBoardListRequest());
    }


    public void onAddBoardClick(View view) {
        ((BaseActivity) context).addFragmentToBackStack(new CreateBoardFragment(), null, true, null);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null)
            webSocketClient.removeResponseListener(this);
    }

    @Override
    public void onClickPosition(BoardClickActionTag tag, BoardEntity boardEntity) {
        switch (tag) {
            case CLICK_BOARD:
                break;
            case CLICK_EDIT_BOARD:
                ((BaseActivity) context).addFragmentToBackStack(new UpdateBoardFragment().setBoardId(boardEntity.getIdent()), null, true, null);
                break;
        }
    }
}

