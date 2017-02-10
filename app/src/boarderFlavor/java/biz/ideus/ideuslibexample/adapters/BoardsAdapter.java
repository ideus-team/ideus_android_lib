package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemBoardBinding;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;


/**
 * Created by blackmamba on 07.02.17.
 */

public class BoardsAdapter extends RecyclerView.Adapter<BoardsAdapter.BoardItemHolder> {


    private List<BoardEntity> boardEntities = new ArrayList<>();
    private OnSelectClickListener onSelectClickListener;

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    public void setBoardEntities(List<BoardEntity> boardEntities){
        this.boardEntities = boardEntities;
        notifyDataSetChanged();
    }

    public void setNewBoardToList(BoardEntity boardEntity) {
        this.boardEntities.add(boardEntity);
        notifyItemInserted(boardEntities.size() - 1);
    }

    public void updateBoardInList(BoardEntity boardEntity) {
        Stream.of(boardEntities).filter(item ->
                item.getIdent().equals(boardEntity.getIdent())).findFirst().map(item ->
        {
            boardEntities.set(boardEntities.indexOf(item), boardEntity);
            notifyItemInserted(boardEntities.indexOf(item));
            return item;
        });
    }



    @Override
    public BoardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BoardItemHolder(DataBindingUtil.inflate(inflater,
               R.layout.item_board, parent, false).getRoot());

    }

    @Override
    public void onBindViewHolder(BoardItemHolder holder, int position) {
        BoardEntity boardEntity = boardEntities.get(position);
        holder.binding.setViewModel(boardEntity);
        holder.binding.getRoot().setOnClickListener(v -> onSelectClickListener.onClickPosition(boardEntity));

        holder.binding.getRoot().setOnLongClickListener(v -> {
            onSelectClickListener.onLongClickPosition(boardEntity);
            return true;
        });


    }


    @Override
    public int getItemCount() {
        return boardEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class BoardItemHolder extends RecyclerView.ViewHolder {
        ItemBoardBinding binding;

        public BoardItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

    }

    public interface OnSelectClickListener{
        void onClickPosition(BoardEntity boardEntity);
        void onLongClickPosition(BoardEntity boardEntity);
    }

}
