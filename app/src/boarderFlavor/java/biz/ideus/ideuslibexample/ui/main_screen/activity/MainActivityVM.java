package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;
import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;
import biz.ideus.ideuslibexample.network.request.CreateBoardRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardListRequest;
import biz.ideus.ideuslibexample.network.request.UpdateBoardRequest;
import biz.ideus.ideuslibexample.network.response.CreateBoardResponse;
import biz.ideus.ideuslibexample.network.response.GetBoardsResponse;
import biz.ideus.ideuslibexample.network.response.UpdateBoardResponse;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;


/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM implements BoardsAdapter.OnSelectClickListener{

    private List<BoardEntity> boardsEntityList = new ArrayList<>();
    private BoardsAdapter adapter;


    public void setAdapter(BoardsAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnSelectClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        webSocketClient.addResponseListener(GetBoardsResponse.class, new AbsWebSocketClient.SocketResponseListener<GetBoardsResponse>() {
            @Override
            public void onGotResponseData(GetBoardsResponse data) {
                boardsEntityList =  data.getData().getBoardsEntitysList();
                adapter.setBoardEntities(boardsEntityList);
            }
        });

        webSocketClient.addResponseListener(CreateBoardResponse.class, new AbsWebSocketClient.SocketResponseListener<CreateBoardResponse>() {
            @Override
            public void onGotResponseData(CreateBoardResponse data) {
                boardsEntityList.add(data.getData().getBoardEntity());
                adapter.setBoardEntities(boardsEntityList);
            }
        });

        webSocketClient.addResponseListener(UpdateBoardResponse.class, new AbsWebSocketClient.SocketResponseListener<UpdateBoardResponse>() {
            @Override
            public void onGotResponseData(UpdateBoardResponse data) {
                getBoards();
            }
        });

        getBoards();
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
    }

    private void getBoards(){
        webSocketClient.sendMessage(new GetBoardListRequest());
    }

    private void createBoard(){
        webSocketClient.sendMessage(new CreateBoardRequest("board name test"));
    }
    private void updateBoard(){
        webSocketClient.sendMessage(new UpdateBoardRequest("board name test update", "bordId"));
    }

    public void onAddBoardClick(View view){
        createBoard();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        webSocketClient.removeResponseListener(GetBoardsListData.class);
    }

    @Override
    public void onClickPosition(BoardEntity boardEntity) {

    }
}
