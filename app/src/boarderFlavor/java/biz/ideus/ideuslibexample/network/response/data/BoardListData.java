package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 15.02.17.
 */

public class BoardListData {
    @SerializedName("ident")
    private String ident;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
}
