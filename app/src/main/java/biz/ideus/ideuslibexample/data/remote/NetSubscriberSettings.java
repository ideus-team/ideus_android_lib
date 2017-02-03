package biz.ideus.ideuslibexample.data.remote;

import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;

/**
 * Created by user on 12.12.2016.
 */

public class NetSubscriberSettings {
    private AbstractViewModelToolbar toolBar;

    public AbstractViewModelToolbar getToolbar() {
        return toolBar;
    }

    private NetSubscriber.ProgressType progressType = NetSubscriber.ProgressType.NONE;

    public NetSubscriberSettings(NetSubscriber.ProgressType progressType) {
        this.progressType = progressType;
    }


    public NetSubscriberSettings(AbstractViewModelToolbar toolBar, NetSubscriber.ProgressType progressType) {
        this.toolBar = toolBar;
        this.progressType = progressType;
    }

    public NetSubscriber.ProgressType getProgressType() {
        return progressType;
    }

    public void setProgressType(NetSubscriber.ProgressType progressType) {
        this.progressType = progressType;
    }


    public void showLinearProgress() {
        if (toolBar != null)
            toolBar.setVisibilityLinearProgress(true);
    }

    public void hideLinearProgress() {
        if (toolBar != null)
            toolBar.setVisibilityLinearProgress(false);

    }
}
