package biz.ideus.ideuslibexample.ui.board_details_screen.activity;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.adapters.CardsAdapter;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeReceiver;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeSubscriber;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.network.request.CreateBoardStoryRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardStoriesRequest;
import biz.ideus.ideuslibexample.network.response.CreateBoardStoryResponse;
import biz.ideus.ideuslibexample.network.response.GetBoardStoriesResponse;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardDetailsVMListener;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardStoriesView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import rx.Subscription;

/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesVM extends AbstractViewModelToolbar<BoardStoriesView> implements BoardDetailsVMListener {

    private List<String> cardsEntitiesList = new ArrayList<>();
    private CardsAdapter adapter;
    private BoardEntity boardEntity;
    private String boardId;
    private Subscription networkSubscription;

    public ObservableField<Boolean> isVisibleETName = new ObservableField<>();
    public ObservableField<String> listName = new ObservableField<>();
    public static BoardRequeryApi boardRequeryApi = BoardRequeryApi.getInstance();
    public static WebSocketClient webSocketClient = WebSocketClient.getInstance();

    public void setBoardId(String boardId) {
        this.boardId = boardId;
        getBoardFormDb(boardId);
    }

    public BoardDetailsVMListener getBoardDetailsListener() {
        return this;
    }

    public void setAdapter(CardsAdapter adapter) {
        this.adapter = adapter;
        adapter.setCardEntities(cardsEntitiesList);

    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        isVisibleETName.set(false);
        startNetworkSubscription();
        initSocketlisteners();

    }

    private void initSocketlisteners() {

        webSocketClient.addResponseListener(this, new SocketResponseListener<CreateBoardStoryResponse>(CreateBoardStoryResponse.class) {
            @Override
            public void onGotResponseData(CreateBoardStoryResponse data) {
                makeCreateListBtnDefault();
            }
        });

        webSocketClient.addResponseListener(this, new SocketResponseListener<GetBoardStoriesResponse>(GetBoardStoriesResponse.class) {
            @Override
            public void onGotResponseData(GetBoardStoriesResponse data) {
                setToolbarTitle(data.getData().getBoardModel().getName());
            }
        });
    }

    private void getBoardFormDb(String boardId) {
        boardRequeryApi.getBoardById(boardId).subscribe(boardEntity1 -> {
            boardEntity = boardEntity1;
            setToolbarTitle(boardEntity.getName());
            getBoardStories(boardEntity.getIdent());
        });

    }

    public void onClickAddList(View view) {
        isVisibleETName.set(true);
    }

    public void onTextChangedNameList(CharSequence text, int start, int before, int count) {
        listName.set(text.toString());
    }

    @Override
    public void onBindView(@NonNull BoardStoriesView view) {
        super.onBindView(view);
    }


    @Override
    public void onBackPressed() {
        if (!isVisibleETName.get()) {
            ((BoardStoriesActivity) context).finish();
        } else {
            makeCreateListBtnDefault();
        }
    }

    @BindingAdapter("isFocus")
    public static void setFocus(EditText editText, boolean isFocus) {
        if (isFocus) {
            editText.post(() -> {
                editText.requestFocus();
                editText.onKeyUp(KeyEvent.KEYCODE_DPAD_CENTER, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
            });
        }
    }

    private void makeCreateListBtnDefault() {
        isVisibleETName.set(false);
        listName.set("");
        ((BaseActivity) context).hideKeyboard();
    }

    @Override
    public void onClickDone() {
        createNewBoardStory();
    }

    private void createNewBoardStory() {
        webSocketClient.sendMessage(new CreateBoardStoryRequest(listName.get(), boardId));
    }

    private void getBoardStories(String boardId) {
        webSocketClient.sendMessage(new GetBoardStoriesRequest(boardId));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null)
            webSocketClient.removeResponseListener(this);
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

}
