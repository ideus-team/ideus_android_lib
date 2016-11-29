package biz.ideus.ideuslibexample.ui.fragments.forgot_pass_fragment;

/**
 * Created by blackmamba on 16.11.16.
 */
//@PerFragment
public class ForgotPasswordVM {}
      //  extends BaseViewModel<BaseMvvmInterface.View> {
//    private Context context;
//    private boolean isValidEmail = false;
//
//    @Bindable
//    public final ObservableField<Integer> titleColorEmail = new ObservableField<>();
//
//    @Bindable
//    public final ObservableField<Boolean> isValidField = new ObservableField<>();
//
//    @Inject
//    public ForgotPasswordVM(@AppContext Context context) {
//        this.context = context;
//        isValidField.set(false);
//    }
//
//    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
//        Observable.just(s.toString())
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .flatMap(text -> Observable.just(UtilsValidationETFields.validateEmail(text, Constants.EMAIL_PATTERN)))
//                .map(isValid -> {
//                    titleColorEmail.set((isValid) ? ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.black)
//                            : ContextCompat.getColor(context, biz.ideus.ideuslib.R.color.error_color));
//                    return isValid;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(aBoolean -> {
//                    isValidEmail = aBoolean;
//                    isValidField.set(aBoolean);
//                });
//    }
//
//    private boolean isValidData() {
//        if (!isValidEmail)
//            Utils.toast(context, context.getString(R.string.invalid_email));
//        return isValidEmail;
//    }
//
//    public void onClickSendPassword(View view) {
//        if (isValidData()) {
//            showSuccessDialog((StartActivity) view.getContext());
//        }
//    }
//
//    public void showSuccessDialog(BaseActivity activity) {
//        CustomAttentionDialog dialog = new CustomAttentionDialog(activity);
//        dialog.setOnBtnDialogClickListener(view1 -> dialog.hide())
//                .setOnCloseClickListener(view12 -> dialog.hide())
//                .setAboutActionText(context.getString(R.string.password_reset_title))
//                .setVisbilityStatusImage(View.INVISIBLE)
//                .setColorTitle(Color.BLACK)
//                .setBtnName(context.getString(R.string.ok))
//                .setTitle(context.getString(R.string.password_reset))
//                .show();
//    }
//
//}
