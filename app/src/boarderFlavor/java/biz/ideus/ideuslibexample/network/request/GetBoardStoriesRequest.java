package biz.ideus.ideuslibexample.network.request;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 16.02.17.
 */

public class GetBoardStoriesRequest extends RequestSocketParams {
    @SerializedName("board_id")
    String boardId;

    @Override
    public String getCommand() {
        return "get_board";
    }

    public GetBoardStoriesRequest(String boardId){
        this.boardId = boardId;
    }
}
