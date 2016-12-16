package biz.ideus.ideuslibexample.rx_buses;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by blackmamba on 16.12.16.
 */

public class RxChatMessageEvent {
    private static RxChatMessageEvent instance;

    private BehaviorSubject<String> subject = BehaviorSubject.create();

    public static RxChatMessageEvent instanceOf() {
        if (instance == null) {
            instance = new RxChatMessageEvent();
        }
        return instance;
    }

    public void setRxChatMessageEvent(String message) {
        subject.onNext(message);
    }

    public Observable<String> getEvents() {
        return subject;
    }
}
