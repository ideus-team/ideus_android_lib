package biz.ideus.ideuslibexample.view_models;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.view_models.ViewModel;
import biz.ideus.ideuslibexample.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */

public class ForgotPasswordVM extends ViewModel {
    private Activity activity;
    private boolean isValidEmail = false;

    @Bindable
    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();
    private UtilsValidationETFields utilsValidationETFields;

    public ForgotPasswordVM(Activity activity) {
        this.activity = activity;
        utilsValidationETFields = new UtilsValidationETFields(activity);
    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        Observable.just(s.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap(text -> Observable.just(utilsValidationETFields.validateEmail(text)))
                .map(isValid -> {
                    titleColorEmail.set((isValid) ? ContextCompat.getColor(activity, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(activity, biz.ideus.ideuslib.R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                   isValidEmail = aBoolean;
                });
    }

    private boolean isValidData() {
        if (!isValidEmail)
            Utils.toast(activity.getString(R.string.invalid_email));
            return isValidEmail;
    }

    public void onClickSendPassword(View view){
        if(isValidData()){

        }
    }

}
