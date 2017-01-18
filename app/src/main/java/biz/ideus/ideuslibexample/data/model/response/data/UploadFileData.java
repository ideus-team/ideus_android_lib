package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 21.12.16.
 */

public class UploadFileData {
    @SerializedName("ident")
    private String id;

    @SerializedName("file")
    private String file;

    @SerializedName("hash")
    private String hash;

    public String getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
    public String getHash() {
        return hash;
    }
}
