package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 11.01.17.
 */

public class UpdateProfileRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("old_password")
    private String oldPassword;

    @SerializedName("new_password")
    private String newPassword;

    @SerializedName("name")
    private String name;

    @SerializedName("photo")
    private String photoUrl;

    public UpdateProfileRequest setEmail(String email) {
        this.email = email;
       return this;
    }

    public UpdateProfileRequest setPassword(String oldPassword, String newPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        return this;
    }

    public UpdateProfileRequest setName(String name) {
        this.name = name;
        return this;
    }

    public UpdateProfileRequest setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

}
