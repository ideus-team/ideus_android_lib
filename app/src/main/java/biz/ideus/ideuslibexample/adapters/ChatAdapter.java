package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.databinding.ItemChatFriendBinding;
import biz.ideus.ideuslibexample.databinding.ItemChatMyBinding;
import biz.ideus.ideuslibexample.ui.chat_screen.MessageViewModel;
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
    public List<MessageViewModel> messageList = new ArrayList<>();
    private ChatActivity activity;
    private ScrollToBottomListener scrollToBottomListener;
    private OnItemChatClickListener onItemChatClickListener;


    public void notifyInsertedItemsAdapter(List<MessageViewModel> newMessageList, PeopleEntity friendForChat) {
        int notifyCounter = newMessageList.size() - messageList.size();
        this.messageList = newMessageList;
        this.friendForChat = friendForChat;
        if (!newMessageList.isEmpty()) {
           notifyItemRangeInserted(messageList.size() - 1, notifyCounter);
            scrollToBottom();
        } else {
            notifyDataSetChanged();
        }
    }

    public void notifyAdapter(List<MessageViewModel> newMessageList) {
        this.messageList = newMessageList;
           notifyDataSetChanged();
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



    public void setMessageToList(MessageViewModel message) {
        this.messageList.add(message);
       notifyItemInserted(messageList.size() - 1);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (scrollToBottomListener != null) {
            scrollToBottomListener.onBottomScroll(messageList.size());
        }
    }

    public void updateMessage(MessageViewModel message){
        Stream.of(messageList).filter(new Predicate<MessageViewModel>() {
            @Override
            public boolean test(MessageViewModel item) {
                return item.getMessage().equals(message.getDateMessage());
            }
        }).map(new Function<MessageViewModel, MessageViewModel>() {
            @Override
            public MessageViewModel apply(MessageViewModel item) {
                messageList.set(messageList.indexOf(item), message);
                ChatAdapter.this.notifyItemChanged(messageList.indexOf(item));
                return item;
            }
        });

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
        MessageViewModel messageViewModel = messageList.get(position).setFriendPhoto(friendForChat.getPhoto());
        if (holder instanceof MyMessageItemHolder) {
            ((MyMessageItemHolder) holder).binding.setViewModel(messageViewModel);
          //  ((MyMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageEntity.getDateMessage()));
            ((MyMessageItemHolder) holder).binding.tvMessage.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel,(ItemChatTag)v.getTag()));
            ((MyMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel,(ItemChatTag)v.getTag()));
        } else {
            ((FriendMessageItemHolder) holder).binding.setViewModel(messageViewModel);
         //   ((FriendMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageEntity.getDateMessage()));
            ((FriendMessageItemHolder) holder).binding.tvMessage.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel,(ItemChatTag)v.getTag()));
            ((FriendMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel,(ItemChatTag)v.getTag()));
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
        void onClickItem(MessageViewModel messageViewModel, ItemChatTag tag);
    }

    public interface ScrollToBottomListener {
        void onBottomScroll(int position);
    }

}
