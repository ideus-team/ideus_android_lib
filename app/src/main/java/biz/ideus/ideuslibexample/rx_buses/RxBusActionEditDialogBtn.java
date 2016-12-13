package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.dialogs.CustomAttentionDialog;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by blackmamba on 02.12.16.
 */

public class RxBusActionEditDialogBtn {

    private static RxBusActionEditDialogBtn instance;

    private BehaviorSubject<CustomAttentionDialog.DialogCommand> subject = BehaviorSubject.create();

    public static RxBusActionEditDialogBtn instanceOf() {
        if (instance == null) {
            instance = new RxBusActionEditDialogBtn();
        }
        return instance;
    }

    public void setDialogCommand(CustomAttentionDialog.DialogCommand dialogCommand) {
        subject.onNext(dialogCommand);
    }

    public Observable<CustomAttentionDialog.DialogCommand> getEvents() {
        return subject;
    }
}