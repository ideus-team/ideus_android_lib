package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SocketMessageData {
    @SerializedName("message")
    private String message;
    @SerializedName("kind")
    private String kind;
    @SerializedName("id_to_user")
    private String userId;

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

    @SerializedName("ident")
    private String ident;
}
