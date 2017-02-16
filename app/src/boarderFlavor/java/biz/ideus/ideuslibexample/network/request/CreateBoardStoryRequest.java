package biz.ideus.ideuslibexample.network.request;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 15.02.17.
 */

public class CreateBoardStoryRequest extends RequestSocketParams {
    @SerializedName("name")
    String boardName;

    @SerializedName("board_id")
    String boardId;

    @Override
    public String getCommand() {
        return "create_board_list";
    }

    public CreateBoardStoryRequest(String boardName, String boardId) {
        this.boardName = boardName;
        this.boardId = boardId;
    }
}

