package biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.fragments;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.board_screen.view_model.UpdateBoardVM;

/**
 * Created by blackmamba on 09.02.17.
 */

public class UpdateBoardFragment extends AbstractBoardFragment {

    private BoardEntity boardEntity;

    public UpdateBoardFragment setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
        return this;
    }


    @Override
    public BoardMainView getCurrentViewModel() {
        return new UpdateBoardVM().setUpdateBoardEntity(boardEntity);
    }
}
