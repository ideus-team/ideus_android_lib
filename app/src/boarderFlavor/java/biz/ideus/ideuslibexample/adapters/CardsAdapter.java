package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemBoardCardBinding;
import biz.ideus.ideuslibexample.enums.BoardClickActionTag;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;


/**
 * Created by blackmamba on 14.02.17.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardItemHolder> {


    private List<String> cardEntities = new ArrayList<>();

    public List<String> getCardEntities() {
        return cardEntities;
    }

    private OnSelectClickListener onSelectClickListener;


    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    public void setCardEntities(List<String> cardEntities) {
        this.cardEntities = cardEntities;
        notifyDataSetChanged();
    }


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
    }


    @Override
    public int getItemCount() {
        return cardEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class CardItemHolder extends RecyclerView.ViewHolder {
        ItemBoardCardBinding binding;

        public CardItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

    }

    public interface OnSelectClickListener {
        void onClickPosition(BoardClickActionTag tag, BoardEntity boardEntity);
    }
}
