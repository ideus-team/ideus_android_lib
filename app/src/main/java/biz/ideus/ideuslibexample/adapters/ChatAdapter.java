package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.databinding.ItemChatFriendBinding;
import biz.ideus.ideuslibexample.databinding.ItemChatMyBinding;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private static final int MY_ITEM = 0;
private static final int FRIEND_ITEM = 1;

    private List<PeopleEntity> messageEntity = new ArrayList<>();
  //  private OnChatClickListener onPeopleClickListener;


//    public void setOnPeopleClickListener(OnChatClickListener onPeopleClickListener) {
//        this.onPeopleClickListener = onPeopleClickListener;
//    }

    public ChatAdapter() {

    }

    public void setMessageEntity(Set<PeopleEntity> messageEntity) {
        this.messageEntity = new ArrayList<>(messageEntity);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == MY_ITEM){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            holder = new MyMessageItemHolder(DataBindingUtil.inflate(inflater,
                    R.layout.item_chat_my, parent, false).getRoot());
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            holder = new FriendMessageItemHolder(DataBindingUtil.inflate(inflater,
                    R.layout.item_chat_friend, parent, false).getRoot());
        }
       return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return messageEntity.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class MyMessageItemHolder extends RecyclerView.ViewHolder {
        public ItemChatMyBinding binding;

        public MyMessageItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

    public class FriendMessageItemHolder extends RecyclerView.ViewHolder {
        public ItemChatFriendBinding binding;

        public FriendMessageItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

//    public interface OnChatClickListener {
//        void onClickItem(int position, PeopleEntity peopleEntity);
//    }

}
