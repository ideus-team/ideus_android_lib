package biz.ideus.ideuslibexample.ui.start_screen.fragments.forgot_password_fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */
//@PerFragment
public class ForgotPasswordVM extends AbstractViewModelToolbar<StartView> {
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
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.CHANGE_PASSWORD_SUCCESS);
        }
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.sign_up);
    }
    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }
}


