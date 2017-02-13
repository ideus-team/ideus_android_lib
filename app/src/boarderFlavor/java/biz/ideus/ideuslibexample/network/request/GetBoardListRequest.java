package biz.ideus.ideuslibexample.network.request;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 08.02.17.
 */

public class GetBoardListRequest  extends RequestSocketParams {

    @Override
    public String getCommand() {
        return "get_boards";
    }
}

