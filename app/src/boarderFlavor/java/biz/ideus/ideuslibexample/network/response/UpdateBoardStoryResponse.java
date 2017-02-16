package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;

/**
 * Created by blackmamba on 15.02.17.
 */

public class UpdateBoardStoryResponse extends SocketBaseResponse<BoardStoryData> {
    @Override
    public String getCommand() {
        return "board_list_updated";
    }
}
