package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.databinding.ActivityChatBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivity extends BaseActivity<ChatView, ChatActivityVM, ActivityChatBinding>
        implements ChatView{

    @Inject
    RequeryApi requeryApi;

    @Inject
    NetApi netApi;

    private ChatAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
        adapter = new ChatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getBinding().rViewChat.setAdapter(adapter);
        getBinding().rViewChat.setHasFixedSize(true);
        getBinding().rViewChat.setLayoutManager(linearLayoutManager);
        adapter.setScrollToBottomListener(position -> getBinding().rViewChat.smoothScrollToPosition(position));
        getViewModel().setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public Class<ChatActivityVM> getViewModelClass() {
        return ChatActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_chat, BR.viewModel, this);
    }

}



