package biz.ideus.ideuslibexample.ui.board_stories_screen.adapters;

import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.boardview.DragItemAdapter;

/**
 * Created by user on 20.02.2017.
 */

public class BoardStoryAdapter extends DragItemAdapter<Pair<Long, String>, BoardStoryAdapter.ViewHolder>{

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class ViewHolder extends DragItemAdapter.ViewHolder {

        public TextView mText;

        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            mText = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}

