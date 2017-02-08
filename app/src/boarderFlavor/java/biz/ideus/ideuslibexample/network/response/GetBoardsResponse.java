package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;

/**
 * Created by blackmamba on 08.02.17.
 */

public class GetBoardsResponse extends SocketBaseResponse<GetBoardsListData> {
    @Override
    public String getCommand() {
        return "boards_list";
    }
}
