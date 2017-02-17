package biz.ideus.ideuslibexample.network.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import biz.ideus.ideuslibexample.ui.board_stories_screen.activity.StoryVM;

/**
 * Created by blackmamba on 16.02.17.
 */

public class StoryModel {
    @SerializedName("ident")
    private String ident;

    @SerializedName("name")

    private String name;

    @SerializedName("cards")
    private List<CardModel> cardModelList;

    public List<CardModel> getCardModelList() {
        return cardModelList;
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

    public StoryVM getStoryVM(){
        return new StoryVM(this);
    }

}
