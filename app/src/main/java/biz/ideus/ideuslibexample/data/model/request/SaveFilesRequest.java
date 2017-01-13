package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by blackmamba on 21.12.16.
 */

public class SaveFilesRequest extends RequestWithToken {

    @SerializedName("file_ids")
    private ArrayList<Integer> filesIdsList;


    public SaveFilesRequest(ArrayList<Integer> filesIdsList) {
        super();
        this.filesIdsList = filesIdsList;

    }
}
