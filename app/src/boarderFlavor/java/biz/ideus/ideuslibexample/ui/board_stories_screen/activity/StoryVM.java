package biz.ideus.ideuslibexample.ui.board_stories_screen.activity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.network.response.model.StoryModel;

/**
 * Created by blackmamba on 17.02.17.
 */

public class StoryVM extends BaseObservable {
    private String name;
    private StoryModel storyModel;

    @Bindable
    public String getName() {
        return storyModel.getName();
    }

    public StoryVM(StoryModel storyModel) {
        this.storyModel = storyModel;
        notifyPropertyChanged(BR.name);
    }
}
