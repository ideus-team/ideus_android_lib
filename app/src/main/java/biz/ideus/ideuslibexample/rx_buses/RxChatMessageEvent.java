package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.data.remote.socket_chat.SocketMessageWrapper;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by blackmamba on 16.12.16.
 */

public class RxChatMessageEvent {
    private static RxChatMessageEvent instance;

    private BehaviorSubject<SocketMessageWrapper> subject = BehaviorSubject.create();

    public static RxChatMessageEvent instanceOf() {
        if (instance == null) {
            instance = new RxChatMessageEvent();
        }
        return instance;
    }

    public void setRxChatMessageEvent(SocketMessageWrapper response) {
        subject.onNext(response);
    }

    public Observable<SocketMessageWrapper> getEvents() {
        return subject;
    }
}
