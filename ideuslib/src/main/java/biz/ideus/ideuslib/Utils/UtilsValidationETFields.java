package biz.ideus.ideuslib.Utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.R;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 15.11.16.
 */

public class UtilsValidationETFields {
    private Activity activity;
    private OnValidateField onValidateField;

    public void setOnValidateField(OnValidateField onValidateField) {
        this.onValidateField = onValidateField;
    }

    public UtilsValidationETFields(Activity activity) {
        this.activity = activity;
    }

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;
        return email.matches(emailPattern);
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
    public boolean validateName(String name) {
        return name.length() > 3;
    }


    public void onTextChangedEmail(String text) {
        Observable.just(text)
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImageEmail((!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE);
                })
                .flatMap(currentText -> Observable.just(validateEmail(currentText)))
                .map(isValid -> {
                    onValidateField.setTitleColorEmail((isValid) ? ContextCompat.getColor(activity, R.color.black)
                            : ContextCompat.getColor(activity, R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidEmail(aBoolean);
                });
    }

    public void onTextChangedPassword(String text) {
        Observable.just(text)
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImagePassword((!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE);
                })
                .flatMap(currentText -> Observable.just(validatePassword(currentText)))
                .map(isValid -> {
                    onValidateField.setTitleColorPassword((isValid) ? ContextCompat.getColor(activity, R.color.black)
                            : ContextCompat.getColor(activity, R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidPassword(aBoolean);
                });

    }
    public void onTextChangedName(String text) {
        Observable.just(text)
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    ((OnValidateSignUpScreen) onValidateField).setVisibilityImageName((!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE);
                })
                .flatMap(currentText -> Observable.just(validateName(currentText)))
                .map(isValid -> {
                    ((OnValidateSignUpScreen) onValidateField).setTitleColorName((isValid) ? ContextCompat.getColor(activity, R.color.black)
                            : ContextCompat.getColor(activity, R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    ((OnValidateSignUpScreen) onValidateField).isValidName(aBoolean);
                });

    }


}
