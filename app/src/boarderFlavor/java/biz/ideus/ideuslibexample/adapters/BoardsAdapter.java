package biz.ideus.ideuslibexample.adapters;

/**
 * Created by blackmamba on 07.02.17.
 */

public class BoardsAdapter {}
       // extends RecyclerView.Adapter<BoardsAdapter.BoardItemHolder> {
//
//
//    private List<BoardEntity> boardEntities = new ArrayList<>();
//    private OnSelectClickListener onSelectClickListener;
//
//    public BoardsAdapter(OnSelectClickListener onSelectClickListener, List<BoardEntity> boardEntities) {
//        this.boardEntities = boardEntities;
//        this.onSelectClickListener = onSelectClickListener;
//    }
//
//    public void setBoardEntities(List<BoardEntity> boardEntities){
//        this.boardEntities = boardEntities;
//        notifyDataSetChanged();
//    }
//
//
//    @Override
//    public BoardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        return new BoardItemHolder(DataBindingUtil.inflate(inflater,
//               R.layout.item_board, parent, false).getRoot());
//
//    }
//
//    @Override
//    public void onBindViewHolder(BoardItemHolder holder, int position) {
//        BoardEntity boardEntity = boardEntities.get(position);
//        holder.binding.setViewModel(boardEntity);
//        holder.binding.getRoot().setOnClickListener(v -> onSelectClickListener.onClickPosition(boardEntity));
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return boardEntities.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//
//    public static class BoardItemHolder extends RecyclerView.ViewHolder {
//        ItemBoardBinding binding;
//
//        public BoardItemHolder(View view) {
//            super(view);
//            binding = DataBindingUtil.bind(view);
//        }
//    }
//
//    public interface OnSelectClickListener{
//        void onClickPosition(BoardEntity boardEntity);
//    }
//
//}
