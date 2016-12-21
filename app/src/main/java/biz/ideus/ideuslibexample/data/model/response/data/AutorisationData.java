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

    @SerializedName("email")
    private String email;

    @SerializedName("friendsCount")
    private String friendsCount;

    @SerializedName("photo")
    private String photo;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    public String getEmail() {
        return email;
    }

    public String getFriendsCount() {
        return friendsCount;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getUserId() {
        return userId;
    }

}


