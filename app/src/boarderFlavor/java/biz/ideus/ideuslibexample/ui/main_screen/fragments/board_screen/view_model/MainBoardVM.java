package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;

/**
 * Created by blackmamba on 09.02.17.
 */

public class MainBoardVM extends AbstractViewModelToolbar<BoardMainView>  {

    private OnClickActionBtnListener onClickActionBtnListener;

    public void setOnClickActionBtnListener(OnClickActionBtnListener onClickActionBtnListener) {
        this.onClickActionBtnListener = onClickActionBtnListener;
    }

    public ObservableField<String> boardName = new ObservableField<>();
    public ObservableField<String> actionButtonText = new ObservableField<>();
    protected WebSocketClient webSocketClient = WebSocketClient.getInstance();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

    }


    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);

    }

    public void onTextChangedBoardName(CharSequence text, int start, int before, int count) {
        boardName.set(text.toString());
    }

    public void onBoardButtonClick(View view){
        onClickActionBtnListener.onClickActionBtn();
    }



    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.create_new_board);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketClient.removeResponseListener(this);
    }

    public interface OnClickActionBtnListener{
        void onClickActionBtn();
    }


}

