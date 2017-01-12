package biz.ideus.ideuslibexample.ui.start_screen.activity;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.interfaces.OnValidateField;
import biz.ideus.ideuslib.interfaces.OnValidateSignUpScreen;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */

public abstract class BaseValidationVM extends AbstractViewModelToolbar<StartView> {
    protected Context context;
    public static final int MIN_COUNT_CHARACTER_NAME = 3;
    public static final int MIN_COUNT_CHARACTER_PASSWORD = 6;
    private OnValidateField onValidateField;


    public void setOnValidateField(OnValidateField onValidateField) {
        this.onValidateField = onValidateField;
    }

    public final ObservableField<CharSequence> name = new ObservableField<>();
    public final ObservableField<CharSequence> email = new ObservableField<>();

    public final ObservableField<CharSequence> password = new ObservableField<>();

    public final ObservableField<Integer> titleColorName = new ObservableField<>();

    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();

    public final ObservableField<Integer> titleColorPassword = new ObservableField<>();

    public final ObservableField<Integer>  visibilityClearNameImage = new ObservableField<>();
    public final ObservableField<Integer> visibilityClearEmailImage = new ObservableField<>();

    public final ObservableField<Integer> visibilityClearPasswordImage = new ObservableField<>();

    public final ObservableField<Boolean> isPasswordShow = new ObservableField<>();

    public final ObservableField<String> titleAutorisationScreen = new ObservableField<>();

    public final ObservableField<String> aboutTitleAutorisationScreen = new ObservableField<>();

    public final ObservableField<Integer> visibilityLoadingPage = new ObservableField<>();

    public final ObservableField<Boolean> isValidFields = new ObservableField<>();



    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        visibilityLoadingPage.set(View.GONE);
        isValidFields.set(false);

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = getView().getViewModelBindingConfig().getContext();

    }

    public void onTextChangedEmail(CharSequence text) {
        email.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImageDeleteEmail(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateEmail(currentText, Constants.EMAIL_PATTERN)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidEmail(aBoolean);
                    onValidateField.setTitleColorEmail(getColor(aBoolean, context));
                    onValidateField.setValidAutorisationBtn();
                });
    }

    public void onTextChangedPassword(CharSequence text) {
        password.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    onValidateField.setVisibilityImageDeletePassword(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validatePassword(currentText, MIN_COUNT_CHARACTER_PASSWORD)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    onValidateField.isValidPassword(aBoolean);
                    onValidateField.setTitleColorPassword(getColor(aBoolean, context));
                    onValidateField.setValidAutorisationBtn();
                });
    }


    public void onTextChangedName(CharSequence text) {
        name.set(text);
        Observable.just(text.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    ((OnValidateSignUpScreen) onValidateField).setVisibilityImageDeleteName(getVisibility(currentText));
                })
                .flatMap(currentText -> Observable.just(UtilsValidationETFields.validateName(currentText, MIN_COUNT_CHARACTER_NAME)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    ((OnValidateSignUpScreen) onValidateField).isValidName(aBoolean);
                    ((OnValidateSignUpScreen) onValidateField).setTitleColorName(getColor(aBoolean, context));
                    onValidateField.setValidAutorisationBtn();
                });

    }

    public static int getColor(Boolean isValid, Context context) {
        return (isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color);
    }

    public static int getVisibility(String currentText) {
        return (!currentText.equals("")) ? View.VISIBLE : View.GONE;
    }


    public void showLoadingPage(String title, String aboutTitle, int visibility) {
        titleAutorisationScreen.set(title);
        aboutTitleAutorisationScreen.set(aboutTitle);
        visibilityLoadingPage.set(visibility);
    }

    public void hideLoadingPage(int visibility) {
        visibilityLoadingPage.set(visibility);
    }

}
