package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardsEntity;
import biz.ideus.ideuslibexample.network.response.model.BoardModel;

/**
 * Created by blackmamba on 08.02.17.
 */

public class BoardsData {

    @SerializedName("board")
    private BoardModel boardModel;
    @SerializedName("cards_deadline_count")
    private String cardsDeadLineCount;
    @SerializedName("cards_count")
    private String cartsCount;


    public BoardsEntity getBoardsEntity() {
        BoardsEntity boardsEntity = new BoardsEntity();
        boardsEntity.setIdent(boardModel.getIdent());
        boardsEntity.setName(boardModel.getName());
        boardsEntity.setCardsCount(cartsCount);
        boardsEntity.setCardsDeadlineCount(cardsDeadLineCount);
        return boardsEntity;
    }
}
