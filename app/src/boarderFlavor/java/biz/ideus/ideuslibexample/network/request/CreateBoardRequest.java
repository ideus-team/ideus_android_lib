package biz.ideus.ideuslibexample.network.request;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.remote.socket.socket_request_model.RequestSocketParams;

/**
 * Created by blackmamba on 08.02.17.
 */

public class CreateBoardRequest extends RequestSocketParams {
    @SerializedName("name")
    String boardName;

    @Override
    public String getCommand() {
        return "create_board";
    }

    public CreateBoardRequest(String boardName) {
        this.boardName = boardName;
    }
}
