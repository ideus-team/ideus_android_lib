package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardStories;
import biz.ideus.ideuslibexample.ui.boardview.BoardView;

/**
 * Created by blackmamba on 14.02.17.
 */

public interface BoardStoriesView extends IView {
    BoardView getBoardView();
    void rebuildBoardView(BoardStories data);
}
