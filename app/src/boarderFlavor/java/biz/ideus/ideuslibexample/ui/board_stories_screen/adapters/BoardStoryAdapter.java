package biz.ideus.ideuslibexample.ui.board_stories_screen.adapters;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import biz.ideus.ideuslibexample.databinding.ItemBoardRecordBinding;
import biz.ideus.ideuslibexample.network.response.entity_model.Card;
import biz.ideus.ideuslibexample.ui.boardview.DragItemAdapter;

/**
 * Created by user on 20.02.2017.
 */

public class BoardStoryAdapter extends DragItemAdapter<Card, BoardStoryAdapter.ViewHolder>{

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;

//ItemAdapter(ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {


    public BoardStoryAdapter(int mLayoutId, int mGrabHandleId, boolean mDragOnLongPress) {
        this.mLayoutId = mLayoutId;
        this.mGrabHandleId = mGrabHandleId;
        this.mDragOnLongPress = mDragOnLongPress;
        setHasStableIds(true);
    }

    public BoardStoryAdapter(List<Card> mList, int mLayoutId, int mGrabHandleId, boolean mDragOnLongPress) {
        this.mItemList = mList;
        this.mLayoutId = mLayoutId;
        this.mGrabHandleId = mGrabHandleId;
        this.mDragOnLongPress = mDragOnLongPress;
        setHasStableIds(true);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        Log.d("onCreateViewHolder", view.toString());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        //holder.binding.setViewModel( (Card)mItemList.get(position) );
        holder.binding.text.setText(mItemList.get(position).getName());
        Log.d("onBindViewHolder", mItemList.get(position).getName());
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getIdent();
    }

    // ViewHolder
    public class ViewHolder extends DragItemAdapter.ViewHolder {
        ItemBoardRecordBinding binding;


        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            binding = DataBindingUtil.bind(itemView);
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

