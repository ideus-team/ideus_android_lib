package biz.ideus.ideuslibexample.ui.board_details_screen.activity;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.CardsAdapter;
import biz.ideus.ideuslibexample.data.local.BoardRequeryApi;
import biz.ideus.ideuslibexample.data.remote.socket.SocketResponseListener;
import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.network.request.CreateBoardStoryRequest;
import biz.ideus.ideuslibexample.network.request.GetBoardStoriesRequest;
import biz.ideus.ideuslibexample.network.response.CreateBoardStoryResponse;
import biz.ideus.ideuslibexample.network.response.GetBoardStoriesResponse;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardDetailsVMListener;
import biz.ideus.ideuslibexample.ui.board_details_screen.BoardDetailsView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;

/**
 * Created by blackmamba on 14.02.17.
 */

public class BoardDetailsVM extends AbstractViewModelToolbar<BoardDetailsView> implements BoardDetailsVMListener {

    private List<String> cardsEntitiesList = new ArrayList<>();
    private CardsAdapter adapter;
    private String boardId;

    public ObservableField<Boolean> isVisibleETName = new ObservableField<>();
    public ObservableField<String> listName = new ObservableField<>();
    public static BoardRequeryApi boardRequeryApi = BoardRequeryApi.getInstance();
    public static WebSocketClient webSocketClient = WebSocketClient.getInstance();

    public void setBoardId(String boardId) {
        this.boardId = boardId;
        getBoardStories(boardId);
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
                makeCreateListBtnDefault();
            }
        });
    }

    public void onClickAddList(View view) {
        isVisibleETName.set(true);
    }

    public void onTextChangedNameList(CharSequence text, int start, int before, int count) {
        listName.set(text.toString());
    }

    @Override
    public void onBindView(@NonNull BoardDetailsView view) {
        super.onBindView(view);
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.board_details);
    }

    @Override
    public void onBackPressed() {
        if (!isVisibleETName.get()) {
            ((BoardDetailsActivity) context).finish();
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
}
