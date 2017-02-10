package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;


/**
 * Created by blackmamba on 08.02.17.
 */

public class BoardData {
    @SerializedName("ident")
    private String ident;
    @SerializedName("name")
    private String name;
    @SerializedName("attributes")
    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }

    public String getIdent() {
        return ident;
    }

    public String getName() {
        return name;
    }


    public class Attributes {
        @SerializedName("cards_deadline_count")
        private String cardsDeadLineCount;

        @SerializedName("cards_count")
        private String cartsCount;

        public String getCardsDeadLineCount() {
            return cardsDeadLineCount;
        }

        public String getCartsCount() {
            return cartsCount;
        }
    }

    public BoardEntity getBoardEntity() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setIdent(ident);
        boardEntity.setName(name);
        boardEntity.setCardsCount(attributes.getCartsCount());
        boardEntity.setCardsDeadlineCount(attributes.getCardsDeadLineCount());
        return boardEntity;
    }
}
