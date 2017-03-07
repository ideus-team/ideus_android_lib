package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.rx_buses.RxBoardCommandEvent;
import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.utils.Utils;

import static biz.ideus.ideuslibexample.enums.BoardCommands.NEW_BOARD;

/**
 * Created by blackmamba on 09.02.17.
 */

public class CreateBoardVM extends MainBoardVM implements MainBoardVM.OnClickActionBtnListener {

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        setOnClickActionBtnListener(this);
        boardName.set("");

    }

    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
        actionButtonText.set(SampleApplication.getInstance().getString(R.string.create_board));
    }

    private void createBoard() {
        if (!boardName.get().isEmpty()) {
            RxBoardCommandEvent.instanceOf().setRxBoardCommandEvent(new BoardCommandWrapper(NEW_BOARD, boardName.get()));
        } else {
            Utils.toast(SampleApplication.getInstance(), SampleApplication.getInstance().getString(R.string.name_is_empty));
        }
    }

    @Override
    public void onClickActionBtn() {
        createBoard();
    }

    @Override
    public String getToolbarTitle() {
        return SampleApplication.getInstance().getString(R.string.create_board);
    }

}
