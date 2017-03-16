package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeReceiver;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeSubscriber;
import biz.ideus.ideuslibexample.network.SocketListener;
import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.network.request.CreateBoardStoryRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardStoriesRequest;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardStories;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.board_stories_screen.BoardStoriesVMListener;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;

/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesVM extends AbstractViewModelToolbar<BoardStoriesView>
        implements BoardStoriesVMListener, BoardView.BoardListener, SocketListener.BoardStory
{

    private BoardEntity boardEntity = new BoardEntity();
    private Subscription networkSubscription;
    private ObservableField<Boolean> isVisibleETName = new ObservableField<>();
    private ObservableField<String> storyName = new ObservableField<>();
    private BoardRequeryApi boardRequeryApi = BoardRequeryApi.getInstance();
    private WebSocketClient webSocketClient = WebSocketClient.getInstance();
    private BoardStories mBoardStories = new BoardStories();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (arguments != null) boardEntity.setIdent(arguments.getString(BOARD_ID));
        isVisibleETName.set(false);
        startNetworkSubscription();
        webSocketClient.addResponseListener(this);
        boardRequeryApi.getBoardStories(Integer.valueOf(boardEntity.getIdent()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshBoardView);
        pullBoardStories(boardEntity.getIdent());
    }

    @Override
    public void board_list_created(StoryData data) {
        Log.d("board_created", "asdf");
    }

    @Override
    public void board_found(BoardStoryData data) {
        boardRequeryApi
                .storeBoardStories(data.getBoardStories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshBoardView);
    }

    private void refreshBoardView(BoardStories boardStories) {
        Log.d("refresh",  boardStories.getName());
        mBoardStories = boardStories;
        if (getView() != null) {
            getView().rebuildBoardView(mBoardStories);
        } else {
            Utils.toast(SampleApplication.getInstance(), "getView null");
        }
    }

    @Override
    public void board_updated(StoryData data) {
        Log.d("board_updated", data.toString());
    }

    public void onFabClick(View v){
        //getViewOptional().rebuildBoardView(mBoardStories);
        pullBoardStories(boardEntity.getIdent());
        //Utils.toast(SampleApplication.getInstance(), "FAB");
        //webSocketClient.sendMessage(new CreateBoardStoryRequest("Test1", boardEntity.getIdent()));
    }



    @Override
    public void onBindView(@NonNull BoardStoriesView view) {
        super.onBindView(view);
        getView().rebuildBoardView(mBoardStories);
    }


    @Override
    public void onClickDone() {
        createNewBoardStory();
    }

    private void createNewBoardStory() {
        webSocketClient.sendMessage(new CreateBoardStoryRequest(storyName.get(), boardEntity.getIdent()));
    }

    private void pullBoardStories(String boardId) {
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
                    }
                });
    }

    @Override
    public String getToolbarTitle() {
        return boardEntity.getName();
    }

    @Override
    public void onItemDragStarted(int column, int row) {
        Utils.toast(SampleApplication.getInstance(), "onItemDragStarted: Start - column: " + column + " row: " + row);
        //Toast.makeText(mBoardView.getContext(), "Start - column: " + column + " row: " + row, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemChangedColumn(int oldColumn, int newColumn) {
        Utils.toast(SampleApplication.getInstance(), "onItemChangedColumn: oldColumn: " + oldColumn + " newColumn: " + newColumn);
    }

    @Override
    public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
        Utils.toast(SampleApplication.getInstance(), "onItemDragEnded: fromColumn: " + fromColumn + " fromRow: " + fromRow  + " toColumn: " + toColumn  + " toRow: " + toRow);
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

    @Override
    public void onBackPressed() {
    }
}
