package biz.ideus.ideuslibexample.rx_buses;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 13.12.16.
 */

public class RxBusGoogleAuthEvent {
    private static RxBusGoogleAuthEvent instance;

    private PublishSubject<GoogleSignInAccount> subject = PublishSubject.create();

    public static RxBusGoogleAuthEvent instanceOf() {
        if (instance == null) {
            instance = new RxBusGoogleAuthEvent();
        }
        return instance;
    }

    public void setRxBusGoogleAuthEvent(GoogleSignInAccount googleAccount) {
        subject.onNext(googleAccount);
    }
    public void onCompleted() {
        subject.onCompleted();
    }


    public Observable<GoogleSignInAccount> getEvents() {
        return subject;
    }
}

