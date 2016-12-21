package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 14.12.16.
 */

public class SocialsAutorisationModel extends BaseRequestModel {
    @SerializedName("token")
    private String token;

    @SerializedName("social_alias")
    private String socialAlias;

    @SerializedName("username")
    private String twitterUsername;

    @SerializedName("is_agree")
    private boolean isAgree;

    public void setIsAgree(boolean isAgree) {
        this.isAgree = isAgree;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public SocialsAutorisationModel(String token, String socialAlias) {
        this.token = token;
        this.socialAlias = socialAlias;
    }

}

