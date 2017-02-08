package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketBaseResponse<T> {

    @SerializedName("data")
    private T data;

    @SerializedName("command")
    String command;

    public T getData() {
        return data;
    };


}

