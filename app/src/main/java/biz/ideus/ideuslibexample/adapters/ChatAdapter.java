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
    private static final int DATE_ITEM = 2;
    private String tempDate = "";
    private PeopleEntity friendForChat = new PeopleEntity();
    public List<MessageViewModel> messageList = new ArrayList<>();
    private ChatActivity activity;
    private ScrollToBottomListener scrollToBottomListener;
    private OnItemChatClickListener onItemChatClickListener;


    public void notifyChatAdapter(List<MessageViewModel> newMessageList, PeopleEntity friendForChat) {
        this.messageList = newMessageList;
        this.friendForChat = friendForChat;
        setVisibilityDateInList(messageList);
        notifyDataSetChanged();
        scrollToBottom();

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
        setVisibilityDateInMessage(message);
        this.messageList.add(message);
        notifyItemInserted(messageList.size() - 1);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (scrollToBottomListener != null) {
            scrollToBottomListener.onBottomScroll(messageList.size());
        }
    }

    public void updateMessage(MessageViewModel message) {

        Stream.of(messageList).filter(item ->
                item.getIdent().equals(message.getIdent())).findFirst().map(item ->
        {
            item.setUpdated(message.isUpdated());
            item.setMessage(message.getMessage());
            return item;
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
            ((MyMessageItemHolder) holder).binding.tvMessage.setOnLongClickListener(v -> {
                onItemChatClickListener.onClickItem(messageViewModel, (ItemChatTag) v.getTag());
                return false;
            });
            ((MyMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel, (ItemChatTag) v.getTag()));
        } else {
            ((FriendMessageItemHolder) holder).binding.setViewModel(messageViewModel);
            ((FriendMessageItemHolder) holder).binding.tvMessage.setOnLongClickListener(v -> {
                onItemChatClickListener.onClickItem(messageViewModel, (ItemChatTag) v.getTag());
                return false;
            });
            ((FriendMessageItemHolder) holder).binding.ivImageAttach.setOnClickListener(v ->
                    onItemChatClickListener.onClickItem(messageViewModel, (ItemChatTag) v.getTag()));
        }
    }


    private void setVisibilityDateInList(List<MessageViewModel> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 || !list.get(i).getDateMessage().equals(list.get(i - 1).getDateMessage()))
                list.get(i).setVisibleDate(true);
        }
    }

    private void setVisibilityDateInMessage(MessageViewModel message) {
        if (messageList.isEmpty()) {
            message.setVisibleDate(true);
        } else if (!message.getDateMessage().equals(messageList.get(messageList.size() - 1).getDateMessage())) {
            message.setVisibleDate(true);
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
