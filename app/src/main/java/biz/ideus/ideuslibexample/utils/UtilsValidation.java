package biz.ideus.ideuslibexample.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 18.11.16.
 */

public class UtilsValidation {
    private Context context;
    private OnValidateField onValidateField;
    public static final int MIN_COUNT_CHARACTER_NAME = 3;
    public static final int MIN_COUNT_CHARACTER_PASSWORD = 6;

    public void setOnValidateField(OnValidateField onValidateField) {
        this.onValidateField = onValidateField;
    }

    public UtilsValidation(Context context) {
        this.context = context;
    }


    public void onTextChangedEmail(String text) {
        Observable.just(text)
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImageEmail((!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE);
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateEmail(currentText, Constants.EMAIL_PATTERN)))
                .map(isValid -> {
                    onValidateField.setTitleColorEmail((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
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
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validatePassword(currentText, MIN_COUNT_CHARACTER_PASSWORD)))
                .map(isValid -> {
                    onValidateField.setTitleColorPassword((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
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
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateName(currentText, MIN_COUNT_CHARACTER_NAME)))
                .map(isValid -> {
                    ((OnValidateSignUpScreen) onValidateField).setTitleColorName((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    ((OnValidateSignUpScreen) onValidateField).isValidName(aBoolean);
                });

    }
}
