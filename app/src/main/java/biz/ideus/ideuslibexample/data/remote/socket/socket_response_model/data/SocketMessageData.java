package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data;

import com.google.gson.annotations.SerializedName;

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


    private String timeMessage;
    private String dateMessage;

    public boolean isOwner() {
        return isOwner;
    }

    public String getDateMessage() {
        return Utils.convertDate(createdAt);
    }

    public String getTimeMessage(){
        return Utils.convertTimeChat(createdAt);

    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public String getIdent() {
        return ident;
    }

    public String getMessage() {
        return message;
    }

    public String getKind() {
        return kind;
    }

    public String getUserId() {
        return userId;
    }

}
