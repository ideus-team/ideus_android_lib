package biz.ideus.ideuslib.Utils;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by blackmamba on 15.11.16.
 */

public class RxHelper {

    public static Observable<String> getTextWatcherObservable(@NonNull final EditText editText) {

        final PublishSubject<String> subject = PublishSubject.create();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });

        return subject;
    }
}