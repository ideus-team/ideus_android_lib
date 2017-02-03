package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 14.12.16.
 */

public class SignUpRequest extends BaseRequestModel {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public SignUpRequest(String email, String password, String firstName) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;

    }

}
