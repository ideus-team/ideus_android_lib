package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.dialogs.DialogCommandModel;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 13.12.16.
 */

public class RxBusLoadingDialog {
    private static RxBusLoadingDialog instance;

    private PublishSubject<DialogCommandModel> subject = PublishSubject.create();

    public static RxBusLoadingDialog instanceOf() {
        if (instance == null) {
            instance = new RxBusLoadingDialog();
        }
        return instance;
    }

    public void setRxBusLoadingDialog(DialogCommandModel comand) {
        subject.onNext(comand);
    }


    public Observable<DialogCommandModel> getEvents() {
        return subject;
    }
}
