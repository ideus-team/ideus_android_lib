package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;

/**
 * Created by blackmamba on 15.02.17.
 */

public class DeleteBoardListResponse extends SocketBaseResponse {
    @Override
    public String getCommand() {
        return "board_list_deleted";
    }
}
