package biz.ideus.ideuslibexample.rx_buses;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 16.01.17.
 */

public class RxBusFetchPeopleListEvent {
    private static RxBusFetchPeopleListEvent instance;

    private PublishSubject<List<PeopleEntity>> subject = PublishSubject.create();

    public static RxBusFetchPeopleListEvent instanceOf() {
        if (instance == null) {
            instance = new RxBusFetchPeopleListEvent();
        }
        return instance;
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

