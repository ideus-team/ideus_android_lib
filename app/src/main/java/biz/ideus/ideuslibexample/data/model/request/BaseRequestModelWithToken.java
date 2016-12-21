package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 21.12.16.
 */

public class BaseRequestModelWithToken {

    @SerializedName("api_token")
    private String api_token;

    public BaseRequestModelWithToken(String api_token){
        this.api_token = api_token;
    }
}
