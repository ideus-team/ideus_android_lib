package biz.ideus.ideuslibexample.ui.start_screen.view_models;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.CustomAttentionDialog;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */
//@PerFragment
public class ForgotPasswordVM extends AbstractViewModel<StartView> {
    private Context context;
    private boolean isValidEmail = false;


    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();


    public final ObservableField<Boolean> isValidField = new ObservableField<>();


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        isValidField.set(false);
    }


    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        Observable.just(s.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap(text -> Observable.just(UtilsValidationETFields.validateEmail(text, Constants.EMAIL_PATTERN)))
                .map(isValid -> {
                    titleColorEmail.set((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    isValidEmail = aBoolean;
                    isValidField.set(aBoolean);
                });
    }

    private boolean isValidData() {
        if (!isValidEmail)
            Utils.toast(context, context.getString(R.string.invalid_email));
        return isValidEmail;
    }

    public void onClickSendPassword(View view) {
        if (isValidData()) {
            showSuccessDialog((StartActivity) view.getContext());
        }
    }

    public void showSuccessDialog(StartActivity activity) {
        CustomAttentionDialog dialog = new CustomAttentionDialog(activity);
        dialog.setOnBtnDialogClickListener(view1 -> dialog.hide())
                .setOnCloseClickListener(view12 -> dialog.hide())
                .setAboutActionText(context.getString(R.string.password_reset_title))
                .setVisbilityStatusImage(View.INVISIBLE)
                .setColorTitle(Color.BLACK)
                .setBtnName(context.getString(R.string.ok))
                .setTitle(context.getString(R.string.password_reset))
                .show();
    }

}
