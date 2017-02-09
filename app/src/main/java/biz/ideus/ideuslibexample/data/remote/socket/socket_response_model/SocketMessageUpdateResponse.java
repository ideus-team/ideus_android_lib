package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;

/**
 * Created by blackmamba on 09.02.17.
 */

public class SocketMessageUpdateResponse extends SocketBaseResponse<SocketMessageData> {

    @Override
    public String getCommand() {
        return "message_sent";
    }
}
