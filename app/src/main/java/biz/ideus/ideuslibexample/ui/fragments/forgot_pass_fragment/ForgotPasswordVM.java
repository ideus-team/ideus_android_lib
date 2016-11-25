package biz.ideus.ideuslibexample.ui.fragments.forgot_pass_fragment;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import biz.ideus.ideuslib.Utils.Utils;
import biz.ideus.ideuslib.Utils.UtilsValidationETFields;
import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.CustomAttentionDialog;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerFragment;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;
import biz.ideus.ideuslibexample.ui.activities.start_activity.StartActivity;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by blackmamba on 16.11.16.
 */
@PerFragment
public class ForgotPasswordVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;
    private boolean isValidEmail = false;

    @Bindable
    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();

    @Bindable
    public final ObservableField<Boolean> isDafaultBgButton = new ObservableField<>();

@Inject
    public ForgotPasswordVM(@AppContext Context context) {
        this.context = context;
    isDafaultBgButton.set(true);
    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        Observable.just(s.toString())
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap(text -> Observable.just(UtilsValidationETFields.validateEmail(text, Constants.EMAIL_PATTERN)))
                .map(isValid -> {
                    titleColorEmail.set((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
                    isDafaultBgButton.set(!isValid);
                    return isValid;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                   isValidEmail = aBoolean;
                });
    }

    private boolean isValidData() {
        if (!isValidEmail)
            Utils.toast(context, context.getString(R.string.invalid_email));
            return isValidEmail;
    }

    public void onClickSendPassword(View view){
        if(isValidData()){
            showSuccessDialog((StartActivity)view.getContext());
        }
    }

    public void showSuccessDialog(BaseActivity activity) {
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
