package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.network.response.data.StoryData;

/**
 * Created by blackmamba on 15.02.17.
 */

public class CreateBoardStoryResponse extends SocketBaseResponse<StoryData> {
    @Override
    public String getCommand() {
        return "board_list_created";
    }
}