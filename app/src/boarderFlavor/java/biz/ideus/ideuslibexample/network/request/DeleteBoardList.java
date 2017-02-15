package biz.ideus.ideuslibexample.network.request;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 15.02.17.
 */

public class DeleteBoardList extends RequestSocketParams {

    @SerializedName("list_id")
    String listId;

    @Override
    public String getCommand() {
        return "delete_board_list";
    }

    public DeleteBoardList(String listId) {
        this.listId = listId;
    }
}

