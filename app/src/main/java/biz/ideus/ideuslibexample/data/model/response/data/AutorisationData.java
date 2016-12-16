package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 16.12.16.
 */

public class AutorisationData {

    @SerializedName("api_token")
    private String userToken;

    @SerializedName("id")
    private String userId;

    public String getUserToken() {
        return userToken;
    }

    public String getUserId() {
        return userId;
    }

}
