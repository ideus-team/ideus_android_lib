package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.ChatAdapter;
import biz.ideus.ideuslibexample.data.remote.socket.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.SendMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Constants;
import okhttp3.Response;

import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.webSocketClient;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> {
    private Context context;
    private String chatUserId = "";
    public List<SocketMessageResponse> messageList = new ArrayList<>();
public ObservableField<String> message = new ObservableField<>();
    private ChatAdapter adapter;

    public void setAdapter(ChatAdapter adapter) {
        this.adapter = adapter;
        adapter.setMessageList(messageList);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if(arguments != null){
            chatUserId = arguments.getString(Constants.CHAT_PEOPLE_ID);
        }
        webSocketClient.createHttpClient();
        webSocketClient.setMessageListener(new SocketMessageListener() {
            @Override
            public void onMessage(SocketMessageResponse response) {
                super.onMessage(response);
                messageList.add(response);
                adapter.setMessageToList(response);
                message.set("");
            }

            @Override
            public void onFail(Response failResponse) {
                    System.out.println("closeclosecloseclose" );
                webSocketClient.createHttpClient();
            }
        });
    }

    private void sendMessage(String message){
        webSocketClient.sendMessage(new SendMessageRequest(chatUserId, message));
    }

    public void onTextChangedMessage(CharSequence text, int start, int before, int count) {
        message.set(text.toString());
    }

    public void onSendClick(View view){
        if(!message.get().isEmpty()) {
            sendMessage(message.get());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBindView(@NonNull ChatView view) {
        super.onBindView(view);
        context = getView().getViewModelBindingConfig().getContext();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.chat);
    }
}

