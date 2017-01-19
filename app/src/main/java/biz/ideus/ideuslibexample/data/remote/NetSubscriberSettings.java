package biz.ideus.ideuslibexample.data.remote;

import biz.ideus.ideuslibexample.ui.common.toolbar.IToolBar;

/**
 * Created by user on 12.12.2016.
 */

public class NetSubscriberSettings {
    private IToolBar toolBar;

    public IToolBar getToolbar() {
        return toolBar;
    }

    private NetSubscriber.ProgressType progressType = NetSubscriber.ProgressType.NONE;

    public NetSubscriberSettings(NetSubscriber.ProgressType progressType) {
        this.progressType = progressType;
    }


    public NetSubscriberSettings(IToolBar toolBar, NetSubscriber.ProgressType progressType) {
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
