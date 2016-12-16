package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.dialogs.DialogParams;
import biz.ideus.ideuslibexample.dialogs.DialogParamsBuilder;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by blackmamba on 01.12.16.
 */

public class RxBusShowDialog {
    private static RxBusShowDialog instance;

    private BehaviorSubject<DialogParams> subject = BehaviorSubject.create();

    public static RxBusShowDialog instanceOf() {
        if (instance == null) {
            instance = new RxBusShowDialog();
        }
        return instance;
    }

    public void setRxBusShowDialog(DialogModel dialogModel) {
        DialogParams dialogParams = new DialogParamsBuilder()
                .setDialogModel(dialogModel)
                .createDialogParams();
        subject.onNext(dialogParams);
    }

    public void setRxBusShowDialog(DialogParams dialogParams) {
        subject.onNext(dialogParams);
    }

    public void setRxBusCommit() {
        subject.onNext(null);
    }

    public Observable<DialogParams> getEvents() {
        return subject;
    }
}