package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 14.12.16.
 */

public class SignUpModel extends BaseRequestModel {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public SignUpModel(String email, String password, String name) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

}
