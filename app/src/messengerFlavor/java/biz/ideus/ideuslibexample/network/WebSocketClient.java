package biz.ideus.ideuslibexample.network;

import java.util.HashMap;

import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;

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
        socketResponseCommand.put("authorize", SocketAutorisedData.class);
        socketResponseCommand.put("receive_message", SocketMessageData.class);
        socketResponseCommand.put("message_sent", SocketMessageData.class);


        return socketResponseCommand;
    }
}
