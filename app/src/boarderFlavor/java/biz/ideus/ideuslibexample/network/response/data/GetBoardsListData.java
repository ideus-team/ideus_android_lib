package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardsEntity;

/**
 * Created by blackmamba on 08.02.17.
 */

public class GetBoardsListData {
    @SerializedName("boards")
    private List<BoardsData> boardsDataList;
    private List<BoardsEntity> boardsEntityList;

    public List<BoardsData> getBoardsDataList() {
        return boardsDataList;
    }

    public List<BoardsEntity> getBoardsEntitysList() {
        return createMessageEntityList(boardsDataList);
    }

    private List<BoardsEntity> createMessageEntityList(List<BoardsData> boardsDataList) {
        boardsEntityList = new ArrayList<>();
        for (int i = 0; i < boardsDataList.size(); i++) {
            boardsEntityList.add(boardsDataList.get(i).getBoardsEntity());
        }
        return boardsEntityList;
    }


}
