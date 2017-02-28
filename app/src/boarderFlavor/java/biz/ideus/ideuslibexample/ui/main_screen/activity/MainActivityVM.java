package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeReceiver;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeSubscriber;
import biz.ideus.ideuslibexample.data.remote.socket.SocketPlay;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;
import biz.ideus.ideuslibexample.enums.BoardClickActionTag;
import biz.ideus.ideuslibexample.network.request.CreateBoardRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardListRequest;
import biz.ideus.ideuslibexample.network.request.UpdateBoardRequest;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_stories_screen.activity.BoardStoriesActivity;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.CreateBoardFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments.UpdateBoardFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;


/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM
        implements BoardsAdapter.OnSelectClickListener
        , SocketPlay.MainScreen {

    private List<BoardEntity> boardsEntityList = new ArrayList<>();
    private BoardsAdapter adapter;
    private Subscription boardCommandEventSubscription;
    private Subscription networkSubscription;


    public static BoardRequeryApi boardRequeryApi;

    @Override
    public void gotData(BoardStoryData data) {
        Log.d("got data", "GetBoardStoriesResponse");
    }

    @Override
    public void gotData(SocketAutorisedData data) {
        Log.d("got data", "SocketAuthorisedResponse");
        getBoards();
    }

    @Override
    public void gotData(StoryData data) {
        Log.d("got data", "CreateBoardStoryResponse");
    }

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
            initSocketlisteners();
            startNetworkSubscription();
            getBoardListFromDB();
        }

    }


    private void initSocketlisteners() {
        webSocketClient.addResponseListener(this);

//        webSocketClient.addResponseListener(this, new SocketResponseListener<SocketAuthorisedResponse>(SocketAuthorisedResponse.class) {
//            @Override
//            public void onGotResponseData(SocketAuthorisedResponse data) {
//                getBoards();
//            }
//        });
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<GetBoardsResponse>(GetBoardsResponse.class) {
//            @Override
//            public void onGotResponseData(GetBoardsResponse data) {
//                boardRequeryApi.storeBoardList(data.getData().getBoardsEntitysList())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(boardEntities -> {
//                            boardsEntityList = (ArrayList<BoardEntity>) boardEntities;
//                            adapter.setBoardEntities(boardsEntityList);
//                        });
//            }
//        });
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<UpdateBoardResponse>(UpdateBoardResponse.class) {
//            @Override
//            public void onGotResponseData(UpdateBoardResponse data) {
//                boardRequeryApi.storeBoard(data.getData().getBoardEntity())
//                        .subscribe(boardEntities -> {
//                            ((MainActivity) context).onBackPressed();
//                            Utils.toast(context, context.getString(R.string.board_updated));
//                            adapter.updateBoardInList(boardEntities);
//                        });
//            }
//        });
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<CreateBoardResponse>(CreateBoardResponse.class) {
//            @Override
//            public void onGotResponseData(CreateBoardResponse data) {
//                boardRequeryApi.storeBoard(data.getData().getBoardEntity())
//                        .subscribe(boardEntity -> {
//                            ((MainActivity) context).onBackPressed();
//                            Utils.toast(context, context.getString(R.string.board_created));
//                            adapter.setNewBoardToList(boardEntity);
//                        });
//            }
//        });
    }

    private void getBoardListFromDB() {
        Observable.just(boardRequeryApi.getBoardList())
                .subscribeOn(Schedulers.newThread())
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
                .subscribe(new Subscriber<BoardCommandWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("eeee", e.getMessage());
                    }

                    @Override
                    public void onNext(BoardCommandWrapper boardCommand) {
                        switch (boardCommand.getBoardCommand()) {
                            case NEW_BOARD:
                                createBoard(boardCommand.getBoardName());
                                break;
                            case UPDATE_BOARD:
                                updateBoard(boardCommand.getBoardName(), boardCommand.getIdent());
                                break;
                        }
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

    private void updateBoard(String boardName, String boardId) {
        webSocketClient.sendMessage(new UpdateBoardRequest(boardName, boardId));
    }

    private void createBoard(String boardName) {
        webSocketClient.sendMessage(new CreateBoardRequest(boardName));
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

        if (boardCommandEventSubscription != null && !boardCommandEventSubscription.isUnsubscribed()) {
            boardCommandEventSubscription.unsubscribe();
        }
    }

    @Override
    public void onClickPosition(BoardClickActionTag tag, BoardEntity boardEntity) {
        switch (tag) {
            case CLICK_BOARD:
                goToBoardDetails(boardEntity.getIdent());
                break;
            case CLICK_EDIT_BOARD:
                ((BaseActivity) context).addFragmentToBackStack(new UpdateBoardFragment().setBoardId(boardEntity.getIdent()), null, true, null);
                break;
        }
    }

    public void startNetworkSubscription() {
        NetworkChangeReceiver.unsubscribe(networkSubscription);
        networkSubscription = RxBusNetworkConnected.getInstance().getEvents()
                .subscribe(new NetworkChangeSubscriber<Object>() {
                    @Override
                    public void complete() {
                        webSocketClient.connectHttpClient();
                        Log.d("complete", "complete");
                    }
                });
    }

    private void goToBoardDetails(String boardID) {
        Intent intent = new Intent(context, BoardStoriesActivity.class);
        intent.putExtra(BOARD_ID, boardID);
        context.startActivity(intent);
    }
}

