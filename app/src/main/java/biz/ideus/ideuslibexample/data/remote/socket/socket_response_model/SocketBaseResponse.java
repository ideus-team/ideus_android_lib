package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 24.01.17.
 */

public abstract class SocketBaseResponse<T> {

    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    };

    @SerializedName("command")
    private String commandFromServer;

    // if commandFromServer as 'getCommand()' from decelerated by 'user'
    public boolean hasValidCommand() {
        if (commandFromServer.contentEquals(getCommand())) {
            return true;
        }
        return false;
    }

    abstract public String getCommand();
}

