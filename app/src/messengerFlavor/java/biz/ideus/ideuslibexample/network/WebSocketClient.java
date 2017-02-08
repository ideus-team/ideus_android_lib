package biz.ideus.ideuslibexample.network;

import java.util.HashMap;

import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAuthorisedResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;

/**
 * Created by blackmamba on 08.02.17.
 */

public class WebSocketClient extends AbsWebSocketClient {

    private static WebSocketClient instance;

    public static WebSocketClient getInstance() {
        if (null == instance) {
            instance = new WebSocketClient();
        }
        return instance;
    }

    @Override
    public HashMap<String, Class> getSocketResponseCommand() {
        HashMap<String, Class> socketResponseCommand = new HashMap<>();

        socketResponseCommand.put("authorize", SocketAuthorisedResponse.class);
        socketResponseCommand.put("receive_message", SocketMessageResponse.class);
        socketResponseCommand.put("message_sent", SocketMessageResponse.class);

        return socketResponseCommand;
    }
}
