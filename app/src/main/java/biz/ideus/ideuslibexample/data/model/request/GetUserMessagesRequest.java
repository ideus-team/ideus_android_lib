package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 30.01.17.
 */

public class GetUserMessagesRequest {

    @SerializedName("offset")
    private int offset;

    @SerializedName("limit")
    private int limit;

    @SerializedName("id_from")
    private String idFrom;


    public GetUserMessagesRequest setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public GetUserMessagesRequest setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public GetUserMessagesRequest setIdFrom(String idFrom) {
        this.idFrom = idFrom;
        return this;
    }

}
