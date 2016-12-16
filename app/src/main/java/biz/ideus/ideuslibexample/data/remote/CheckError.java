package biz.ideus.ideuslibexample.data.remote;

import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by user on 16.12.2016.
 */

public class CheckError<T extends ServerAnswer> implements Observable.Operator<T, T> {

    public CheckError(){}

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> s) {
        return new Subscriber<T>(s) {
            @Override
            public void onCompleted() {
                if(!s.isUnsubscribed()) {
                    s.onCompleted();
                }
            }

            @Override
            public void onError(Throwable t) {
                if(!s.isUnsubscribed()) {
                    s.onError(t);
                }
            }

            @Override
            public void onNext(T item) {
                if(!s.isUnsubscribed()) {
                    if (!item.message.isEmpty()){
                        onError(new Throwable(item.message));
                    }
                    else {
                        s.onNext(item);
                    }
                }
            }
        };
    }
}