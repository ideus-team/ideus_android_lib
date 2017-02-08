package biz.ideus.ideuslibexample.network.response.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 08.02.17.
 */

public class BoardModel {
    @SerializedName("ident")
    private String ident;
    @SerializedName("name")
    private String name;

    public String getIdent() {
        return ident;
    }

    public String getName() {
        return name;
    }
}
