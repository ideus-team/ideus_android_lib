package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.data.remote.socket.SocketMessageWrapper;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 16.12.16.
 */

public class RxBusSocketMessageEvent {
    private static RxBusSocketMessageEvent instance;

    private PublishSubject<SocketMessageWrapper> subject = PublishSubject.create();

    public static RxBusSocketMessageEvent getInstance() {
        if (instance == null) {
            instance = new RxBusSocketMessageEvent();
        }
        return instance;
    }

    public void setRxSocketMessageEvent(SocketMessageWrapper response) {
        subject.onNext(response);
    }

    public Observable<SocketMessageWrapper> getEvents() {
        return subject;
    }
}
