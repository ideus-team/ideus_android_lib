package biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SendMessageRequest extends RequestSocketParams {

    @SerializedName("id_to_user")
    private String userId;

    @SerializedName("message")
    private String message;

    @SerializedName("kind")
    private String kind;

    public SendMessageRequest(String userId, String message, String kind){
        this.message = message;
        this.kind = kind;
        this.userId = userId;
    }


    @Override
    public String getCommand() {
        return "message";
    }
}
