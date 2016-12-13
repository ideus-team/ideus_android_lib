package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.dialogs.DialogModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by blackmamba on 01.12.16.
 */

public class RxBusShowDialog {
    private static RxBusShowDialog instance;

    private BehaviorSubject<DialogModel> subject = BehaviorSubject.create();

    public static RxBusShowDialog instanceOf() {
        if (instance == null) {
            instance = new RxBusShowDialog();
        }
        return instance;
    }

    public void setRxBusShowDialog(DialogModel dialogModel) {
        subject.onNext(dialogModel);
    }


    public Observable<DialogModel> getEvents() {
        return subject;
    }
}