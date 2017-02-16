package biz.ideus.ideuslibexample.network.response.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 16.02.17.
 */

public class StoryModel {
    @SerializedName("ident")
    private String ident;

    @SerializedName("name")
    private String name;

    @SerializedName("cards")
    private CardModel cardModel;

    public CardModel getCardModel() {
        return cardModel;
    }

    public String getName() {
        return name;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
}
