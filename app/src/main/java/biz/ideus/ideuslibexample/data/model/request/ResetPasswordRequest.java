package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 05.01.17.
 */

public class ResetPasswordRequest extends RequestWithToken {
    @SerializedName("email")
    private String email;

    public ResetPasswordRequest(String email){
        this.email = email;
    }
}
