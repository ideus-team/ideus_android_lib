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

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.adapters.StoriesAdapter;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeReceiver;
import biz.ideus.ideuslibexample.data.remote.network_change.NetworkChangeSubscriber;
import biz.ideus.ideuslibexample.data.remote.socket.SocketListener;
import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.network.request.CreateBoardStoryRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardStoriesRequest;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.board_stories_screen.BoardStoriesVMListener;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Utils;
import rx.Subscription;

import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;

/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesVM extends AbstractViewModelToolbar<BoardStoriesView>
        implements BoardStoriesVMListener, BoardView.BoardListener
        , SocketListener.BoardStory
{

    private List<StoryVM> storyVMList = new ArrayList<>();
    private StoriesAdapter adapter;
    private BoardEntity boardEntity = new BoardEntity();
    private Subscription networkSubscription;

    public ObservableField<Boolean> isVisibleETName = new ObservableField<>();
    public ObservableField<String> storyName = new ObservableField<>();
    public static BoardRequeryApi boardRequeryApi = BoardRequeryApi.getInstance();
    public static WebSocketClient webSocketClient = WebSocketClient.getInstance();


    public BoardStoriesVMListener getBoardStoriesListener() {
        return this;
    }

    public void setAdapter(StoriesAdapter adapter) {
        this.adapter = adapter;
        adapter.setStoryModelList(storyVMList);

    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        boardEntity.setIdent(arguments.getString(BOARD_ID));
        isVisibleETName.set(false);
        startNetworkSubscription();
        initSocketlisteners();
        BoardView boardView ;
        //boardView.addColumnList()

    }

    @Override
    public void board_list_created(StoryData data) {
        Log.d("board_created", data.toString());
    }

    @Override
    public void board_found(BoardStoryData data) {
        Log.d("board_found", data.toString());
    }

    @Override
    public void board_updated(StoryData data) {
        Log.d("board_updated", data.toString());
    }

    public void onFabClick(View v){
        Utils.toast(SampleApplication.getInstance(), "FAP");

        webSocketClient.sendMessage(new CreateBoardStoryRequest("Test1", boardEntity.getIdent()));
    }

    private void initSocketlisteners() {
        webSocketClient.addResponseListener(this);
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<SocketAuthorisedResponse>(SocketAuthorisedResponse.class) {
//            @Override
//            public void onGotResponseData(SocketAuthorisedResponse data) {
//                getBoardStories(boardEntity.getIdent());
//                //
//            }
//        });
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<CreateBoardStoryResponse>(CreateBoardStoryResponse.class) {
//            @Override
//            public void onGotResponseData(CreateBoardStoryResponse data) {
//            //    adapter.setNewStoryModel(data.getData().getStoryVM());
//                makeCreateStoryBtnDefault();
//            }
//        });
//
//        webSocketClient.addResponseListener(this, new SocketResponseListener<GetBoardStoriesResponse>(GetBoardStoriesResponse.class) {
//            @Override
//            public void onGotResponseData(GetBoardStoriesResponse data) {
//                storyVMList = data.getData().getBoardModel().getStoryVMList();
//               // adapter.setStoryModelList(storyVMList);
//                refreshBoardViewData(storyVMList);
//            }
//        });
    }


    private void refreshBoardViewData(List<StoryVM> storyVMList) {
        getViewOptional().getBoardView().clearBoard();
        for (StoryVM storyModel : storyVMList) {
            //getView().getBoardView().addColumnList(new BoardStoryAdapter(storyModel.), null, false );
            //ItemAdapter(mItemArray, R.layout.grid_item, R.id.item_layout, true);
        }
    }

    private BoardEntity getBoardFormDb(String boardId) {
       return boardRequeryApi.getBoardById(boardId);
    }

    public void onClickAddList(View view) {
        isVisibleETName.set(true);
    }

    public void onTextChangedNameStory(CharSequence text, int start, int before, int count) {
        storyName.set(text.toString());
    }

    @Override
    public void onBindView(@NonNull BoardStoriesView view) {
        super.onBindView(view);
        if(boardEntity.getIdent() == null ) {
            //boardEntity = getBoardFormDb(((BoardStoriesActivity) context).getBoardID());
            getBoardStories(boardEntity.getIdent());
        }
    }


    @Override
    public void onBackPressed() {
        if (!isVisibleETName.get()) {
          //  ((BoardStoriesActivity) context).finish();
        } else {
            makeCreateStoryBtnDefault();
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

    private void makeCreateStoryBtnDefault() {
        isVisibleETName.set(false);
        storyName.set("");
       // ((BaseActivity) context).hideKeyboard();
    }

    @Override
    public void onClickDone() {
        createNewBoardStory();
    }

    private void createNewBoardStory() {
        webSocketClient.sendMessage(new CreateBoardStoryRequest(storyName.get(), boardEntity.getIdent()));
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

}
