package biz.ideus.ideuslibexample.ui.board_stories_screen.item_helper;

/**
 * Created by blackmamba on 15.02.17.
 */

public interface ItemTouchHelperAdapter {


    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}