package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardStories;

/**
 * Created by blackmamba on 15.02.17.
 */

public class BoardStoryData {
    @SerializedName("board")
    private BoardStories boardStories;

    public BoardStories getBoardStories() {
        return boardStories;
    }
}

