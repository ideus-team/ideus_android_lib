package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketAuthorisedResponse extends SocketBaseResponse<SocketAutorisedData> {
    @Override
    public String getCommand() {
        return "authorize";
    }
}
