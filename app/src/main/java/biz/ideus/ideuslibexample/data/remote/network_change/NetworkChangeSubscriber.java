package biz.ideus.ideuslibexample.data.remote.network_change;

import rx.Subscriber;

/**
 * Created by blackmamba on 30.01.17.
 */

public abstract class NetworkChangeSubscriber<T extends Object> extends Subscriber<T>{
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T o) {
        complete();
    }

    public abstract void complete();
}
