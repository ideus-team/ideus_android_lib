package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.databinding.ItemChatFriendBinding;
import biz.ideus.ideuslibexample.databinding.ItemChatMyBinding;
import biz.ideus.ideuslibexample.ui.chat_screen.activity.ChatActivity;
import biz.ideus.ideuslibexample.ui.chat_screen.activity.ItemChatTag;


/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MY_ITEM = 0;
    private static final int FRIEND_ITEM = 1;
    private String tempDate = "";
    private PeopleEntity friendForChat = new PeopleEntity();
    public List<MessageEntity> messageList = new ArrayList<>();
    private ChatActivity activity;
    private ScrollToBottomListener scrollToBottomListener;
    private OnItemChatClickListener onItemChatClickListener;


    public void notifyAdapter(List<MessageEntity> newMessageList, PeopleEntity friendForChat){
        int notifyCounter = newMessageList.size() - messageList.size();
        this.messageList = newMessageList;
        this.friendForChat = friendForChat;
        if(!newMessageList.isEmpty()){
            notifyItemRangeInserted(messageList.size()-1, notifyCounter);
            scrollToBottom();
        }else{
            notifyDataSetChanged();
        }

    }

    public void setScrollToBottomListener(ScrollToBottomListener scrollToBottomListener) {
        this.scrollToBottomListener = scrollToBottomListener;
    }

    public void setOnItemChatClickListener(OnItemChatClickListener onItemChatClickListener) {
        this.onItemChatClickListener = onItemChatClickListener;
    }

    public ChatAdapter(ChatActivity activity) {
        this.activity = activity;
    }



    public void setMessageToList(MessageEntity message) {
        this.messageList.add(message);
        ChatAdapter.this.notifyItemInserted(messageList.size() - 1);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (scrollToBottomListener != null) {
            scrollToBottomListener.onBottomScroll(messageList.size());
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == MY_ITEM) {
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
        MessageEntity messageEntity = messageList.get(position);
        if (holder instanceof MyMessageItemHolder) {
            ((MyMessageItemHolder) holder).binding.setViewModel(messageEntity);
            ((MyMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageEntity.getDateMessage()));
            ((MyMessageItemHolder) holder).binding.tvMessage.setVisibility((messageEntity.getKind().equals("text") ? View.VISIBLE : View.GONE));
            ((MyMessageItemHolder) holder).binding.llImageAttachContainer.setVisibility((messageEntity.getKind().equals("image") ? View.VISIBLE : View.GONE));
            ((MyMessageItemHolder) holder).binding.tvMessage.setOnClickListener(v -> onItemChatClickListener.onClickItem(position,messageEntity,(ItemChatTag)v.getTag()));
            ((MyMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v -> onItemChatClickListener.onClickItem(position,messageEntity,(ItemChatTag)v.getTag()));
        } else {
            ((FriendMessageItemHolder) holder).binding.setViewModel(messageEntity);
            ((FriendMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageEntity.getDateMessage()));
            ((FriendMessageItemHolder) holder).binding.ivUserPhoto.loadImage(friendForChat.getPhoto());
            ((FriendMessageItemHolder) holder).binding.tvMessage.setVisibility((messageEntity.getKind().equals("text") ? View.VISIBLE : View.GONE));
            ((FriendMessageItemHolder) holder).binding.llImageAttachContainer.setVisibility((messageEntity.getKind().equals("image") ? View.VISIBLE : View.GONE));
            ((FriendMessageItemHolder) holder).binding.tvMessage.setOnClickListener(v -> onItemChatClickListener.onClickItem(position,messageEntity,(ItemChatTag)v.getTag()));
            ((FriendMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v -> onItemChatClickListener.onClickItem(position,messageEntity,(ItemChatTag)v.getTag()));
        }
    }


    private boolean isVisibleDate(String date) {
        Log.d("tempDate", tempDate);
        if (tempDate.equals("")) {
            tempDate = date;
            return true;
        } else if(!tempDate.equals(date)){
            tempDate = date;
            return true;
        } else {
            return false;
        }

    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).isOwner()) {
            return MY_ITEM;
        } else {
            return FRIEND_ITEM;
        }
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

    public interface OnItemChatClickListener {
        void onClickItem(int position, MessageEntity messageEntity, ItemChatTag tag);
    }

    public interface ScrollToBottomListener {
        void onBottomScroll(int position);
    }

}
