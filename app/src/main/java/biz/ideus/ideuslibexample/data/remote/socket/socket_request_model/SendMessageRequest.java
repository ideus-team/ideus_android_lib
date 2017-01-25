package biz.ideus.ideuslibexample.data.remote.socket.socket_request_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SendMessageRequest extends RequestSocketParams {

    @SerializedName("id_to_user")
    private String userId;

    @SerializedName("message")
    private String message;

    public SendMessageRequest(String userId, String message){
        this.message = message;
        this.userId = userId;
    }


    @Override
    public String getCommand() {
        return "message";
    }
}
