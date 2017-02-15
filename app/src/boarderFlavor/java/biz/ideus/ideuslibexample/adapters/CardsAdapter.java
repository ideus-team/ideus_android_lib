package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemBoardCardBinding;
import biz.ideus.ideuslibexample.enums.BoardClickActionTag;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import biz.ideus.ideuslibexample.ui.board_details_screen.item_helper.ItemTouchHelperAdapter;
import biz.ideus.ideuslibexample.ui.board_details_screen.item_helper.ItemTouchHelperViewHolder;
import biz.ideus.ideuslibexample.ui.board_details_screen.item_helper.OnStartDragListener;


/**
 * Created by blackmamba on 14.02.17.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardItemHolder> implements ItemTouchHelperAdapter {


    private List<String> cardEntities = new ArrayList<>();
    private final OnStartDragListener mDragStartListener;

    public List<String> getCardEntities() {
        return cardEntities;
    }

    private OnSelectClickListener onSelectClickListener;

    public CardsAdapter(OnStartDragListener mDragStartListener) {
        this.mDragStartListener = mDragStartListener;
    }

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    public void setCardEntities(List<String> cardEntities) {
        this.cardEntities = cardEntities;
        notifyDataSetChanged();
    }

//    public void setNewBoardToList(BoardEntity boardEntity) {
//        this.boardEntities.add(boardEntity);
//        notifyItemInserted(boardEntities.size() - 1);
//    }

//    public void updateBoardInList(BoardEntity boardEntity) {
//        Stream.of(boardEntities).filter(item ->
//                item.getIdent().equals(boardEntity.getIdent())).findFirst().map(item ->
//        {
//            int indexItemForUpdate = boardEntities.indexOf(item);
//            boardEntities.set(indexItemForUpdate, boardEntity);
//            notifyItemChanged(indexItemForUpdate);
//            return item;
//        });
//    }


    @Override
    public CardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CardsAdapter.CardItemHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_board_card, parent, false).getRoot());

    }

    @Override
    public void onBindViewHolder(CardItemHolder holder, int position) {
        String cardEntity = cardEntities.get(position);
        holder.binding.setViewModel(cardEntity);
//        holder.binding.rlCardMine.setOnTouchListener((v, event) -> {
//            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                mDragStartListener.onStartDrag(holder);
//            }
//            return false;
//        });
    }


    @Override
    public int getItemCount() {
        return cardEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onItemDismiss(int position) {
        cardEntities.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cardEntities, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cardEntities, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    public static class CardItemHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        ItemBoardCardBinding binding;

        public CardItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);

        }


        @Override
        public void onItemSelected() {
            binding.rlCardMine.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            binding.rlCardMine.setBackgroundColor(0);
        }
    }

    public interface OnSelectClickListener {
        void onClickPosition(BoardClickActionTag tag, BoardEntity boardEntity);
    }
}
