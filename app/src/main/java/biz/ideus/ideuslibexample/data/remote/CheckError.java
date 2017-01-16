package biz.ideus.ideuslibexample.data.remote;

import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import retrofit2.adapter.rxjava.HttpException;
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

                if (t instanceof HttpException) {
                    // We had non-2XX http error
                }
                RetrofitException error = (RetrofitException) t;
               String errorMessage = error.getMessage();

                if(!s.isUnsubscribed()) {
                    s.onError(new Throwable(errorMessage));
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