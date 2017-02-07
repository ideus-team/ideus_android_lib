package biz.ideus.ideuslibexample.data.remote.socket.socket_request_model;

import com.google.gson.annotations.SerializedName;
import com.orhanobut.hawk.Hawk;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;

/**
 * Created by blackmamba on 24.01.17.
 */

public class AuthorizeChatRequestSocket extends RequestSocketParams {

    @SerializedName("api_token")
    private String api_token;

   @Override
   public String getCommand(){
       return "authorize";
   }

    public AuthorizeChatRequestSocket(){
        this.api_token = Hawk.get(USER_TOKEN);
    }


}
