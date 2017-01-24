package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.databinding.ActivityTutorialBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivity extends BaseActivity<ChatView, ChatActivityVM, ActivityTutorialBinding>
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



