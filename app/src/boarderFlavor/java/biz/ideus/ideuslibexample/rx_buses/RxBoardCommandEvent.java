package biz.ideus.ideuslibexample.rx_buses;

import biz.ideus.ideuslibexample.ui.main_screen.BoardCommandWrapper;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 09.02.17.
 */

public class RxBoardCommandEvent {

        private static RxBoardCommandEvent instance;

        private PublishSubject<BoardCommandWrapper> subject = PublishSubject.create();

        public static RxBoardCommandEvent instanceOf() {
            if (instance == null) {
                instance = new RxBoardCommandEvent();
            }
            return instance;
        }

        public void setRxBoardCommandEvent(BoardCommandWrapper boardCommandWrapper) {
            subject.onNext(boardCommandWrapper);
        }
        public void onCompleted() {
            subject.onCompleted();
        }


        public Observable<BoardCommandWrapper> getEvents() {
            return subject;
        }
    }



