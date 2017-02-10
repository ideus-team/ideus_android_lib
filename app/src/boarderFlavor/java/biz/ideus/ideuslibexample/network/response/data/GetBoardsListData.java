package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;


/**
 * Created by blackmamba on 08.02.17.
 */

public class GetBoardsListData {
    @SerializedName("boards")
    private List<BoardData> boardsDataList;
    private List<BoardEntity> boardsEntityList;

    public List<BoardData> getBoardsDataList() {
        return boardsDataList;
    }

    public List<BoardEntity> getBoardsEntitysList() {
        return createMessageEntityList(boardsDataList);
    }

    private List<BoardEntity> createMessageEntityList(List<BoardData> boardsDataList) {
        boardsEntityList = new ArrayList<>();
        for (int i = 0; i < boardsDataList.size(); i++) {
            boardsEntityList.add(boardsDataList.get(i).getBoardEntity());
        }
        return boardsEntityList;
    }


}
