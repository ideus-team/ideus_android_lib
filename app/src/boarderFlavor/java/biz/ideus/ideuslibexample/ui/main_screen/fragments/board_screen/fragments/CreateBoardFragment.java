package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model.CreateBoardVM;

/**
 * Created by blackmamba on 09.02.17.
 */

public class CreateBoardFragment extends AbstractBoardFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public BoardMainView getCurrentViewModel() {
        return new CreateBoardVM();
    }


}
