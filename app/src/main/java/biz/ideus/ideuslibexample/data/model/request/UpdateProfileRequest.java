package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 11.01.17.
 */

public class UpdateProfileRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("photo")
    private String photoUrl;

    public UpdateProfileRequest(String email, String password
            , String firstName, String lastName, String photoUrl){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;

    }

    public UpdateProfileRequest(String photoUrl){
        this.photoUrl = photoUrl;

    }

}
