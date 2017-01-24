package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketBaseResponse<T> {
        @SerializedName("data")
        public T data;

        @SerializedName("command")
        public String message;

        public SocketBaseResponse(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

