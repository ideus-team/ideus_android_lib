package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.network.response.data.BoardData;

/**
 * Created by blackmamba on 08.02.17.
 */

public class CreateBoardResponse extends SocketBaseResponse<BoardData> {
    @Override
    public String getCommand() {
        return "board_created";
    }
}