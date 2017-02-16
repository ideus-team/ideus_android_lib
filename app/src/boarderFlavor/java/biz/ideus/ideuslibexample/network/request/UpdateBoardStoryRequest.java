package biz.ideus.ideuslibexample.network.request;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 15.02.17.
 */

public class UpdateBoardStoryRequest extends RequestSocketParams {
    @SerializedName("name")
    String boardName;

    @SerializedName("list_id")
    String listId;

    @Override
    public String getCommand() {
        return "update_board_list";
    }

    public UpdateBoardStoryRequest(String boardName, String listId) {
        this.boardName = boardName;
        this.listId = listId;
    }
}
