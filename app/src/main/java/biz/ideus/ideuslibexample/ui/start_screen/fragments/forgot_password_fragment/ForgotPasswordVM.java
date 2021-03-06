package biz.ideus.ideuslibexample.ui.start_screen.fragments.forgot_password_fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.dialogs.RxBusShowDialog;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.DialogStore;
import biz.ideus.ideuslibexample.data.model.request.ResetPasswordRequest;
import biz.ideus.ideuslibexample.data.model.response.ResetPasswordAnswer;
import biz.ideus.ideuslibexample.data.remote.CheckError;
import biz.ideus.ideuslibexample.data.remote.NetSubscriber;
import biz.ideus.ideuslibexample.data.remote.NetSubscriberSettings;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.netApi;

/**
 * Created by blackmamba on 16.11.16.
 */
//@PerFragment
public class ForgotPasswordVM extends AbstractViewModelToolbar<StartView> {
    private Context context;
    private boolean isValidEmail = false;


    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();
    public final ObservableField<Integer> visibilityClearEmailImage = new ObservableField<>();
    public final ObservableField<CharSequence> email = new ObservableField<>();


    public final ObservableField<Boolean> isValidField = new ObservableField<>();


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        isValidField.set(false);
        visibilityClearEmailImage.set(View.GONE);
    }


    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    public void onClickClearFieldImage(View view) {
                email.set(Editable.Factory.getInstance().newEditable(""));

    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        email.set(s);
        Observable.just(s.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext(currentText -> {
                    visibilityClearEmailImage.set(getVisibility(currentText));
                })
                .flatMap(text -> Observable.just(UtilsValidationETFields.validateEmail(text, Constants.EMAIL_PATTERN)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    isValidEmail = aBoolean;
                    isValidField.set(aBoolean);
                    titleColorEmail.set(getColor(aBoolean, context));
                });
    }

    private boolean isValidData() {
        if (!isValidEmail)
            Utils.toast(context, context.getString(R.string.invalid_email));
        return isValidEmail;
    }

    public void onClickSendPassword(View view) {
        if (isValidData()) {
            sendPassword();
        }
    }

    private void sendPassword(){

        NetSubscriberSettings netSubscriberSettings = new NetSubscriberSettings(NetSubscriber.ProgressType.CIRCULAR);
        netApi.resetPassword(new ResetPasswordRequest(email.get().toString()))
                .lift(new CheckError<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<ResetPasswordAnswer>(netSubscriberSettings) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.CHANGE_PASSWORD_SUCCESS());
                    }
                    @Override
                    public void onNext(ResetPasswordAnswer answer) {

                    }
                });
    }

    private int getColor(Boolean isValid, Context context) {
        return (isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color);
    }

    private int getVisibility(String currentText) {
        return (!currentText.equals("")) ? View.VISIBLE : View.INVISIBLE;
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.forgot_password);
    }
    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }
}


