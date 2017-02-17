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
import biz.ideus.ideuslibexample.ui.board_stories_screen.activity.StoryVM;


/**
 * Created by blackmamba on 14.02.17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.CardItemHolder> {


    private List<StoryVM> storiesVMList = new ArrayList<>();
    private ScrollToBottomListener scrollToBottomListener;


    public List<StoryVM> getStoriesVMList() {
        return storiesVMList;
    }

    public void setScrollToBottomListener(ScrollToBottomListener scrollToBottomListener) {
        this.scrollToBottomListener = scrollToBottomListener;
    }

    public void setStoryModelList(List<StoryVM> storiesList) {
        this.storiesVMList = storiesList;
        notifyDataSetChanged();
    }

    public void setNewStoryModel(StoryVM story) {
        this.storiesVMList.add(story);
        notifyItemInserted(storiesVMList.size() - 1);
       scrollToBottomListener.onBottomScroll(storiesVMList.size() - 1);
    }


    @Override
    public CardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StoriesAdapter.CardItemHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_board_card, parent, false).getRoot());

    }

    @Override
    public void onBindViewHolder(CardItemHolder holder, int position) {
        StoryVM storyVM = storiesVMList.get(position);
        holder.binding.setViewModel(storyVM);
    }


    @Override
    public int getItemCount() {
        return storiesVMList.size();
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

    public interface ScrollToBottomListener {
        void onBottomScroll(int position);
    }

}
