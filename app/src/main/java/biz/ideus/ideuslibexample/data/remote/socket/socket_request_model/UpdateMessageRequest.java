package biz.ideus.ideuslibexample.data.remote.socket.socket_request_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 01.02.17.
 */

public class UpdateMessageRequest extends RequestSocketParams {

    @SerializedName("message")
    private String message;

    @SerializedName("message_id")
    private String messageId;

    public UpdateMessageRequest(String message, String messageId){
        this.message = message;
        this.messageId = messageId;
    }

    @Override
    public String getCommand() {
        return "update_message";
    }
}
