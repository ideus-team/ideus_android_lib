package biz.ideus.ideuslibexample.network.response.data;

import com.google.gson.annotations.SerializedName;

import biz.ideus.ideuslibexample.network.response.model.StoryModel;
import biz.ideus.ideuslibexample.ui.board_stories_screen.activity.StoryVM;

/**
 * Created by blackmamba on 17.02.17.
 */

public class StoryData  {
    @SerializedName("board_list")
    private StoryModel storyModel;

   public StoryVM getStoryVM(){
       return new StoryVM(storyModel);
   }
}
