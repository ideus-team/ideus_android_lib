package biz.ideus.ideuslibexample.ui.chat_screen.activity;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.SendMessageRequest;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;
import biz.ideus.ideuslibexample.rx_buses.RxChatMessageEvent;
import biz.ideus.ideuslibexample.ui.chat_screen.ChatView;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.data.remote.socket.SocketCommand.MESSAGE_SENT;
import static biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM.webSocketClient;

/**
 * Created by blackmamba on 23.01.17.
 */

public class ChatActivityVM extends AbstractViewModelToolbar<ChatView> {
    private Context context;
    private String chatUserId = "";
public ObservableField<String> message = new ObservableField<>();
    private Subscription chatEventSubscription;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if(arguments != null){
            chatUserId = arguments.getString(Constants.CHAT_PEOPLE_ID);
        }
        chatEventSubscription = getChatEventSubscription();
    }

    private void sendMessage(String message){
        webSocketClient.sendMessage(SocketMessageResponse.class, new SendMessageRequest(chatUserId, message));
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
        webSocketClient.closeWebSocket();
        if (chatEventSubscription != null && !chatEventSubscription.isUnsubscribed())
            chatEventSubscription.unsubscribe();
    }

    @Override
    public void onBindView(@NonNull ChatView view) {
        super.onBindView(view);
        context = getView().getViewModelBindingConfig().getContext();
    }

    private Subscription getChatEventSubscription(){
       return RxChatMessageEvent.instanceOf().getEvents()
              .filter(event -> event.getSocketCommand().equals(MESSAGE_SENT))
               .map(event -> (SocketMessageResponse) event.getResponse())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(socketMessageResponse -> {
                    Log.d("message", socketMessageResponse.data.getMessage());
                });
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.chat);
    }
}

