package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12.12.2016.
 */

public class LoginModelRequest extends BaseRequestModel {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginModelRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
