package biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketRequest {

    @SerializedName("command")
    public String command;

    @SerializedName("data")
    public RequestSocketParams requestSocketParams;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setRequestSocketParams(RequestSocketParams requestSocketParams) {
        this.requestSocketParams = requestSocketParams;
    }

}
