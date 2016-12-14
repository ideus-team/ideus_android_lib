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

    public SocialsAutorisationModel(String token, String socialAlias) {
        this.token = token;
        this.socialAlias = socialAlias;
    }

}

