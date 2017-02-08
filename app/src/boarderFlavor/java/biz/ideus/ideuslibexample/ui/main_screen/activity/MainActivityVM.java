package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;
import biz.ideus.ideuslibexample.network.request.GetBoardListRequest;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardsEntity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import rx.Subscription;


/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM {

    private Subscription rxBusSocketMessageSubscription;
    private List<BoardsEntity> boardsEntityList;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        //rxBusSocketMessageSubscription = getSocketMessageSubscription();
        webSocketClient.addResponseListener(GetBoardsListData.class, new AbsWebSocketClient.SocketResponseListener<GetBoardsListData>() {
            @Override
            public void onGotResponseData(GetBoardsListData data) {
                Log.d("data Board", data.getBoardsDataList().size() + "");
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

    /*private Subscription getSocketMessageSubscription() {
        return RxBusSocketMessageEvent.getInstance().getEvents()
                .filter(socketMessageWrapper -> socketMessageWrapper.getSocketCommand().equals(BOARD_LIST))
                .map(socketMessageWrapper -> {
                        boardsEntityList = ((GetBoardsResponse) socketMessageWrapper.getResponse()).data.getBoardsEntitysList();
                    return socketMessageWrapper;

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketMessageWrapper -> {
                    Log.d("list_count", boardsEntityList.size() + "");
                });

    }*/

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxBusSocketMessageSubscription != null && !rxBusSocketMessageSubscription.isUnsubscribed()) {
            rxBusSocketMessageSubscription.unsubscribe();
        }
        webSocketClient.removeResponseListener(GetBoardsListData.class);
    }
}
