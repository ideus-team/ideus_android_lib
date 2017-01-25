package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.data.remote.socket.SocketMessageListener;
import biz.ideus.ideuslibexample.data.remote.socket.WebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAutorisedResponse;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import okhttp3.Response;


/**
 * Created by blackmamba on 24.11.16.
 */
//@PerActivity
public class MainActivityVM extends AbstractViewModel<StartView> {

    public static WebSocketClient webSocketClient = null;


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        webSocketClient = WebSocketClient.getInstance();
        webSocketClient.setMessageListener(new SocketMessageListener() {
            @Override
            public void onMessage(Object response) {
                if (response instanceof SocketAutorisedResponse) {
                    SocketAutorisedResponse autorisedResponse = (SocketAutorisedResponse) response;
                    System.out.println("message" + autorisedResponse.data.isAutorise());
                }
            }

            @Override
            public void onFail(Response failResponse) {

            }
        });

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketClient.closeWebSocket();
    }
}


