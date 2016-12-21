package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.UserFileEntity;

/**
 * Created by blackmamba on 21.12.16.
 */

public class UserFileData {
    @SerializedName("files")
    private List<UserFileEntity> userFilesEntities;

    public List<UserFileEntity> getUserFilesEntities() {
        return userFilesEntities;
    }

    public void setUserFilesEntities(List<UserFileEntity> userFilesEntities) {
        this.userFilesEntities = userFilesEntities;
    }
}
