package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 16.01.17.
 */

public class GetPeopleRequest {
    @SerializedName("offset")
    private int offset;

    @SerializedName("term")
    private String term;

    public void setTerm(String term) {
        this.term = term;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
