package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 15.02.17.
 */

public class BoardStoryData {
    @SerializedName("ident")
    private String ident;

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
}
