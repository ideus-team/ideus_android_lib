package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 28.02.2017.
 */

public class SocketCommonResponse {

    @SerializedName("data")
    private Object data;

    @SerializedName("command")
    private String commandFromServer;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCommandFromServer() {
        return commandFromServer;
    }

    public void setCommandFromServer(String commandFromServer) {
        this.commandFromServer = commandFromServer;
    }
}
