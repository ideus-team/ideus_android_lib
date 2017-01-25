package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 16.01.17.
 */

public class GetPeopleRequest {
    @SerializedName("offset")
    private int offset;

    @SerializedName("limit")
    private int limit;


    @SerializedName("term")
    private String term;

    public GetPeopleRequest(String term){
        this.term = term;
    }

    public GetPeopleRequest(){}

    public GetPeopleRequest setTerm(String term) {
        this.term = term;
        return this;
    }

    public GetPeopleRequest setOffset(int offset) {
        this.offset = offset;
        return this;
    }
    public GetPeopleRequest setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
