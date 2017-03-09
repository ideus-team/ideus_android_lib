package biz.ideus.ideuslibexample.network.response.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blackmamba on 16.02.17.
 */

public class BoardModel {
    @SerializedName("ident")
    private String ident;

    @SerializedName("name")
    private String name;

    @SerializedName("lists")
    private List<StoryModel> stories;

    public List<StoryModel> getStories() {
        return stories;
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

//    public List<StoryVM> getStoryVMList(){
//        List<StoryVM> storyVMlist = new ArrayList<>();
//        for (int i = 0; i < stories.size() ; i++) {
//            storyVMlist.add(stories.get(i).getStoryVM());
//        }
//        return storyVMlist;
//    }
}


