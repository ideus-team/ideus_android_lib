package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.dialogs.DialogCommand;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 02.12.16.
 */

public class RxBusCustomAction {

    private static RxBusCustomAction instance;

    private PublishSubject<DialogCommand> subject = PublishSubject.create();


    public static RxBusCustomAction instanceOf() {
        if (instance == null) {
            instance = new RxBusCustomAction();
        }
        return instance;
    }

    public void setDialogCommand(DialogCommand dialogCommand) {
        subject.onNext(dialogCommand);
    }

    public void setRxBusCommit() {
        subject.onNext(null);
    }

    public Observable<DialogCommand> getEvents() {
        return subject;
    }
}