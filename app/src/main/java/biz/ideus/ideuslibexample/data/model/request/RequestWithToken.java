package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;
import com.orhanobut.hawk.Hawk;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;

/**
 * Created by blackmamba on 21.12.16.
 */

public class RequestWithToken extends LanguageModel {

    @SerializedName("api_token")
    private String api_token;

    public RequestWithToken(){
        this.api_token = Hawk.get(USER_TOKEN);
    }
}
