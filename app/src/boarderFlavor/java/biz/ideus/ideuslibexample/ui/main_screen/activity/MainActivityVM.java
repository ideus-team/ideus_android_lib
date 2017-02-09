package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAuthorisedResponse;
import biz.ideus.ideuslibexample.network.request.GetBoardListRequest;
import biz.ideus.ideuslibexample.network.response.GetBoardsResponse;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.CreateBoardFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.UpdateBoardFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM implements BoardsAdapter.OnSelectClickListener{

    private List<BoardEntity> boardsEntityList = new ArrayList<>();
    private BoardsAdapter adapter;
    private Subscription boardCommandEventSubscription;


    public void setAdapter(BoardsAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnSelectClickListener(this);
        adapter.setBoardEntities(boardsEntityList);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        boardCommandEventSubscription = getBoardCommandEventSubscription();

        webSocketClient.addResponseListener(this, new SocketResponseListener<SocketAuthorisedResponse>(SocketAuthorisedResponse.class) {
            @Override
            public void onGotResponseData(SocketAuthorisedResponse data) {
                getBoards();
            }
        });

        webSocketClient.addResponseListener(this, new SocketResponseListener<GetBoardsResponse>(GetBoardsResponse.class) {
            @Override
            public void onGotResponseData(GetBoardsResponse data) {
                boardsEntityList = data.getData().getBoardsEntitysList();
                adapter.setBoardEntities(boardsEntityList);
            }
        });

    }

    private Subscription getBoardCommandEventSubscription(){
        return RxBoardCommandEvent.instanceOf().getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BoardCommandWrapper>() {
            @Override
            public void call(BoardCommandWrapper boardCommandWrapper) {
                switch (boardCommandWrapper.getBoardCommand()){
                    case NEW_BOARD:
                        adapter.setNewBoardToList(boardCommandWrapper.getBoardEntity());
                        break;
                    case UPDATE_BOARD:
                        getBoards();
                        break;
                }

            }
        });
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
    }

    private void getBoards(){
        webSocketClient.sendMessage(new GetBoardListRequest());
    }



    public void onAddBoardClick(View view){
        ((BaseActivity)context).addFragmentToBackStack(new CreateBoardFragment(), null, true, null);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketClient.removeResponseListener(this);
    }

    @Override
    public void onClickPosition(BoardEntity boardEntity) {
        ((BaseActivity)context).addFragmentToBackStack(new UpdateBoardFragment().setBoardEntity(boardEntity), null, true, null);
    }
}
