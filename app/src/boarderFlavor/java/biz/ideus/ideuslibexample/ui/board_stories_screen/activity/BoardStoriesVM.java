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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import biz.ideus.ideuslibexample.Models;
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
import biz.ideus.ideuslibexample.network.response.entity_model.BoardStoriesEntity;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import biz.ideus.ideuslibexample.ui.board_stories_screen.BoardStoriesVMListener;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Utils;
import io.requery.Persistable;
import io.requery.jackson.EntityMapper;
import io.requery.rx.SingleEntityStore;
import rx.Subscription;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.utils.BoardAppConstants.BOARD_ID;

/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardStoriesVM extends AbstractViewModelToolbar<BoardStoriesView>
        implements BoardStoriesVMListener, BoardView.BoardListener, SocketListener.BoardStory
{

   // private List<StoryVM> storyVMList = new ArrayList<>();
    private BoardEntity boardEntity = new BoardEntity();
    private Subscription networkSubscription;
    private BoardStoryData boardStoryData;
    private ObservableField<Boolean> isVisibleETName = new ObservableField<>();
    private ObservableField<String> storyName = new ObservableField<>();
    private BoardRequeryApi boardRequeryApi = BoardRequeryApi.getInstance();
    private WebSocketClient webSocketClient = WebSocketClient.getInstance();


    public BoardStoriesVMListener getBoardStoriesListener() {
        return this;
    }


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (arguments != null) boardEntity.setIdent(arguments.getString(BOARD_ID));
        isVisibleETName.set(false);
        startNetworkSubscription();
        initSocketlisteners();
        getBoardStories(boardEntity.getIdent());
        //boardView.addColumnList()
    }

    @Override
    public void board_list_created(StoryData data) {
        Log.d("board_created", "asdf");
    }

    @Override
    public void board_found(BoardStoryData data) {

        String s = "{\"lists\": [{\"cards\": [{\"files\": [{\"ident\": \"1\", \"file\": \"http://46.101.254.89/static/uploads/7ffc2f2e-2809-41f9-99b7-77b8d288d23e.jpeg\"}, {\"ident\": \"2\", \"file\": \"http://46.101.254.89/static/uploads/f38aea53-0891-4e1c-978e-b2e2ccba79ee.jpeg\"}], \"color\": \"CCCCCC\", \"ident\": \"11\", \"name\": \"card 1\"}, {\"files\": [], \"color\": \"FFFFFF\", \"ident\": \"13\", \"name\": \"card 3\"}], \"ident\": \"52\", \"name\": \"two\"}, {\"cards\": [{\"files\": [], \"color\": \"CCCCCC\", \"ident\": \"12\", \"name\": \"card 2\"}, {\"files\": [], \"color\": \"CCCCCC\", \"ident\": \"14\", \"name\": \"card 4\"}], \"ident\": \"51\", \"name\": \"one\"}, {\"cards\": [], \"ident\": \"53\", \"name\": \"three\"}], \"ident\": \"1\", \"name\": \"new board name\"}";
        SingleEntityStore<Persistable> data1 = requeryApi.getData();

        BoardStoriesEntity bb;
        bb = data1.select(BoardStoriesEntity.class).get().first();

        ObjectMapper mapper = new EntityMapper(Models.DEFAULT, data1);
        //Event read = mapper.readValue(value, Event.class);
        BoardStoriesEntity en;
        try {
             en = mapper.readValue(s, BoardStoriesEntity.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data1.insert(en).toObservable().subscribe();


        Log.d("board_found", data.toString());
    }

    @Override
    public void board_updated(StoryData data) {
        Log.d("board_updated", data.toString());
    }

    public void onFabClick(View v){
        Utils.toast(SampleApplication.getInstance(), "FAB");

        webSocketClient.sendMessage(new CreateBoardStoryRequest("Test1", boardEntity.getIdent()));
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

    }


    @Override
    public void onBackPressed() {
//        if (!isVisibleETName.get()) {
//          //  ((BoardStoriesActivity) context).finish();
//        } else {
//            makeCreateStoryBtnDefault();
//        }
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

//    private void makeCreateStoryBtnDefault() {
//        isVisibleETName.set(false);
//        storyName.set("");
//       // ((BaseActivity) context).hideKeyboard();
//    }

}
