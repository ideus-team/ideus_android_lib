package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.network.response.model.BoardModel;

/**
 * Created by blackmamba on 15.02.17.
 */

public class BoardStoryData {
    @SerializedName("board")
    private BoardModel boardModel;

    public BoardModel getBoardModel() {
        return boardModel;
    }
}

