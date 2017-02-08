package biz.ideus.ideuslibexample.network;

import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;

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

    /*@Override
    public HashMap<String, Class> getSocketResponseCommand() {

        HashMap<String, Class> socketResponseCommand = new HashMap<>();
        socketResponseCommand.put("authorize", SocketAutorisedData.class);
        socketResponseCommand.put("receive_message", SocketMessageResponse.class);
        socketResponseCommand.put("message_sent", SocketMessageResponse.class);


        return socketResponseCommand;
    }*/
}
