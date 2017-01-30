package biz.ideus.ideuslibexample.data.remote.socket_chat.socket_response_model.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.utils.Utils;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SocketMessageData {
    @SerializedName("message")
    private String message;
    @SerializedName("kind")
    private String kind;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("ident")
    private String ident;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("is_updated")
    private boolean isUpdated;
    @SerializedName("is_owner")
    private boolean isOwner;


    public MessageEntity getMessageEntity() {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setIdent(ident);
        messageEntity.setDateMessage(Utils.convertDate(createdAt));
        messageEntity.setTimeMessage(Utils.convertTimeChat(createdAt));
        messageEntity.setKind(kind);
        messageEntity.setOwner(isOwner);
        messageEntity.setMessage(message);
        messageEntity.setUpdated(isUpdated);
        messageEntity.setUserId(userId);
        return messageEntity;
    }

}
