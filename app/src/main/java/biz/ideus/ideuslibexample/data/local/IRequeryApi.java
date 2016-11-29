package biz.ideus.ideuslibexample.data.local;

import rx.Observable;

/**
 * Created by user on 21.11.2016.
 */

public interface IRequeryApi {
    Observable<String> getFavoriteChangeObservable();
}
