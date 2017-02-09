package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SocketMessageResponse extends SocketBaseResponse<SocketMessageData> {

    @Override
    public String getCommand() {
        return "receive_message";
    }
}
//socketResponseCommand.put("authorize", SocketAuthorisedResponse.class);
//        socketResponseCommand.put("receive_message", SocketMessageResponse.class);
//        socketResponseCommand.put("message_sent", SocketMessageResponse.class);