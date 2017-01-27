package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;
import biz.ideus.ideuslibexample.databinding.ItemChatFriendBinding;
import biz.ideus.ideuslibexample.databinding.ItemChatMyBinding;
import biz.ideus.ideuslibexample.ui.chat_screen.activity.ChatActivity;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MY_ITEM = 0;
    private static final int FRIEND_ITEM = 1;
    private String tempDate = null;
    private PeopleEntity friendForChat = new PeopleEntity();

    public void setFriendForChat(PeopleEntity friendForChat) {
        this.friendForChat = friendForChat;
    }

    private ChatActivity activity;
    private ScrollToBottomListener scrollToBottomListener;

    public void setScrollToBottomListener(ScrollToBottomListener scrollToBottomListener) {
        this.scrollToBottomListener = scrollToBottomListener;
    }

    public List<SocketMessageResponse> messageList = new ArrayList<>();
    //  private OnChatClickListener onPeopleClickListener;


//    public void setOnPeopleClickListener(OnChatClickListener onPeopleClickListener) {
//        this.onPeopleClickListener = onPeopleClickListener;
//    }

    public ChatAdapter(ChatActivity activity) {
        this.activity = activity;
    }

    public void setMessageList(List<SocketMessageResponse> messageList) {
        this.messageList = new ArrayList<>(messageList);
        notifyDataSetChanged();
    }

    public void setMessageToList(SocketMessageResponse message) {
        this.messageList.add(message);
        ChatAdapter.this.notifyItemInserted(messageList.size() - 1);
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
        SocketMessageData messageData = messageList.get(position).data;
        if (holder instanceof MyMessageItemHolder) {
            ((MyMessageItemHolder) holder).binding.setViewModel(messageData);
            ((MyMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageData.getDateMessage()));
            ((MyMessageItemHolder) holder).binding.tvMessage.setVisibility((messageData.getKind().equals("text") ? View.VISIBLE : View.GONE));
            ((MyMessageItemHolder) holder).binding.llImageAttachContainer.setVisibility((messageData.getKind().equals("image") ? View.VISIBLE : View.GONE));
        } else {
            ((FriendMessageItemHolder) holder).binding.setViewModel(messageData);
            ((FriendMessageItemHolder) holder).binding.setIsShowDate(isVisibleDate(messageData.getDateMessage()));
            ((FriendMessageItemHolder) holder).binding.ivUserPhoto.loadImage(friendForChat.getPhoto());
            ((FriendMessageItemHolder) holder).binding.tvMessage.setVisibility((messageData.getKind().equals("text") ? View.VISIBLE : View.GONE));
            ((FriendMessageItemHolder) holder).binding.llImageAttachContainer.setVisibility((messageData.getKind().equals("image") ? View.VISIBLE : View.GONE));
            ((FriendMessageItemHolder) holder).binding.ivUserPhoto.loadImage(friendForChat.getPhoto());
        }
    }


    private boolean isVisibleDate(String date) {
        if (tempDate == null) {
            tempDate = date;
            return true;
        } else return !tempDate.equals(date);

    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).data.isOwner()) {
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

//    public interface OnChatClickListener {
//        void onClickItem(int position, PeopleEntity peopleEntity);
//    }

    public interface ScrollToBottomListener {
        void onBottomScroll(int position);
    }

}
