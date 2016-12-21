package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by blackmamba on 21.12.16.
 */

public class SaveFilesModel extends BaseRequestModelWithToken {

    @SerializedName("file_ids")
    private ArrayList<Integer> filesIdsList;


    public SaveFilesModel(String api_token, ArrayList<Integer> filesIdsList) {
        super(api_token);
        this.filesIdsList = filesIdsList;

    }
}
