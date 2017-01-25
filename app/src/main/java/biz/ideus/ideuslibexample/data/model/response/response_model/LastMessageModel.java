package biz.ideus.ideuslibexample.data.model.response.response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 17.01.17.
 */

public class LastMessageModel {
    @SerializedName("ident")
    String ident;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("message")
    String message;
    @SerializedName("is_mine")
    boolean isMine;

    public String getIdent() {
        return ident;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMine() {
        return isMine;
    }

}



