package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SocketMessageResponse extends SocketBaseResponse<SocketMessageData> {
    public SocketMessageResponse(SocketMessageData data) {
        super(data);
    }
}
