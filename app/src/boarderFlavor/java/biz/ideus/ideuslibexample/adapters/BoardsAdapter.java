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
import biz.ideus.ideuslibexample.enums.BoardClickActionTag;
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
            int indexItemForUpdate = boardEntities.indexOf(item);
            boardEntities.set(indexItemForUpdate, boardEntity);
            notifyItemChanged(indexItemForUpdate);
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
        holder.binding.rlBoard.setOnClickListener(v -> onSelectClickListener.onClickPosition((BoardClickActionTag)v.getTag(), boardEntity));
        holder.binding.ivEdit.setOnClickListener(v -> onSelectClickListener.onClickPosition((BoardClickActionTag)v.getTag(), boardEntity));

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
        void onClickPosition(BoardClickActionTag tag, BoardEntity boardEntity);
    }

}
