package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket.WebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAutorisedResponse;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Constants;
import okhttp3.Response;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> {
    private Context context;
    private String chatUserId = "";
    private WebSocketClient webSocketClient;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if(arguments != null){
            chatUserId = arguments.getString(Constants.CHAT_PEOPLE_ID);
        }
        webSocketClient = new WebSocketClient(new SocketMessageListener() {
            @Override
            public void onMessage(Object response) {
                if(response instanceof SocketAutorisedResponse){
                   SocketAutorisedResponse autorisedResponse = (SocketAutorisedResponse) response;
                    System.out.println("message" + autorisedResponse.data.isAutorise());
                }

            }

            @Override
            public void onFail(Response failResponse) {

            }
        });

    }

    private void sendMessage(String Message){
      //  webSocketClient.sendMessage(SocketAutorisedResponse.class , new AuthorizeChatRequestSocket());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketClient.closeWebSocket();
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

