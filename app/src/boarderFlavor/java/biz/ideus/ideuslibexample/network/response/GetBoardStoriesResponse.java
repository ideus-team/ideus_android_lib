package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;

/**
 * Created by blackmamba on 16.02.17.
 */

public class GetBoardStoriesResponse extends SocketBaseResponse<GetBoardsListData> {
    @Override
    public String getCommand() {
        return "get_board";
    }
}
