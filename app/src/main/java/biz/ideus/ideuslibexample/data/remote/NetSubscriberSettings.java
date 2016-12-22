package biz.ideus.ideuslibexample.data.remote;

/**
 * Created by user on 12.12.2016.
 */

public class NetSubscriberSettings {
    private NetSubscriber.ProgressType progressType = NetSubscriber.ProgressType.NONE;

    public NetSubscriberSettings(NetSubscriber.ProgressType progressType) {
        this.progressType = progressType;
    }

    public NetSubscriberSettings() {
    }

    public NetSubscriber.ProgressType getProgressType() {
        return progressType;
    }

    public void setProgressType(NetSubscriber.ProgressType progressType) {
        this.progressType = progressType;
    }
}
