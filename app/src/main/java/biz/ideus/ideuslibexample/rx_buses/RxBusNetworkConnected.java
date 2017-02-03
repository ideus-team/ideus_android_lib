package biz.ideus.ideuslibexample.rx_buses;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 30.01.17.
 */

public class RxBusNetworkConnected {
    private static RxBusNetworkConnected instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBusNetworkConnected getInstance() {
        if (instance == null) {
            instance = new RxBusNetworkConnected();
        }
        return instance;
    }


    public void setNetworkConnected() {
        subject.onNext(new Object());
    }


    public Observable<Object> getEvents() {
        return subject;
    }
}


