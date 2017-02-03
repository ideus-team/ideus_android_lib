package biz.ideus.ideuslibexample.rx_buses;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 16.01.17.
 */

public class RxBusFetchPeopleListEvent {
    private static RxBusFetchPeopleListEvent fetchEvent;

    private PublishSubject<List<PeopleEntity>> subject = PublishSubject.create();

    public static RxBusFetchPeopleListEvent getInstance() {
        if (fetchEvent == null) {
            fetchEvent = new RxBusFetchPeopleListEvent();
        }
        return fetchEvent;
    }

    public void setRxBusFetchPeopleListEvent(List<PeopleEntity> peopleEntities) {
        subject.onNext(peopleEntities);
    }
    public void onCompleted() {
        subject.onCompleted();
    }


    public Observable<List<PeopleEntity>> getEvents() {
        return subject;
    }
}

