package biz.ideus.ideuslibexample.ui.start_activity;

/**
 * Created by user on 18.11.2016.
 */
//@PerActivity
public class StartActivityVM {}
        //extends AutorisationVM implements BaseMvvmInterface.StartActivityVmListener, OnValidateField {
//
//    private final Context ctx;
//    private boolean isValidEmail = false;
//    private boolean isValidPassword = false;
//
//
//    @Inject
//    public StartActivityVM(@AppContext Context context) {
//        super(context);
//        this.ctx = context.getApplicationContext();
//        this.visibilityClearEmailImage.set(View.INVISIBLE);
//        this.visibilityClearPasswordImage.set(View.INVISIBLE);
//        this.isPasswordShow.set(true);
//        utilsValidation.setOnValidateField(this);
//    }
//
//
//    private boolean isValidData(View view) {
//        if (!(isValidEmail && isValidPassword)) {
//            showAttentionDialog((StartActivity)view.getContext(), ctx.getString(R.string.invalidate_login_text));
//        }
//        return isValidEmail && isValidPassword;
//
//    }
//
//    public void onClickSelectPhoto(View view) {
//        Intent chooseImageIntent = CropImage.getPickImageChooserIntent((StartActivity) view.getContext());
//        navigator.get().startActivityForResult(chooseImageIntent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
//    }
//
//
//    @Override
//    public void onFaceBookClick(View view) {
//        onClickFaceBookLogin((StartActivity) view.getContext());
//    }
//
//    @Override
//    public void onTwitterClick(View view) {
//        onClickTwitterLogin((StartActivity) view.getContext());
//    }
//
//    @Override
//    public void onGoogleClick(View view) {
//        signInWithGooglePlus((StartActivity) view.getContext());
//    }
//
//    @Override
//    public void onSignInClick(View view) {
//        if (isValidData(view)) {
//
//
//        }
//       // showLoadingPage(ctx.getString(R.string.logging_to_app_temp), ctx.getString(R.string.about_logining_account), View.VISIBLE);
//
//    }
//
//    @Override
//    public void onSignUpClick(View view) {
//        navigator.get().addFragmentToBackStack(android.R.id.content, new SignUpFragment(), Constants.SIGN_UP_FRAGMENT, null, true, null);
//    }
//
//    @Override
//    public void onShowPasswordClick(View view) {
//        isPasswordShow.set((!((CheckBox) view).isChecked()));
//    }
//
//    @Override
//    public void onForgotPasswordClick(View view) {
//        navigator.get().addFragmentToBackStack(android.R.id.content, new ForgotPasswordFragment(), Constants.SIGN_UP_FRAGMENT, null, true, null);
//    }
//
//    @Override
//    public void onClearEmailClick(View view) {
//        email.set(Editable.Factory.getInstance().newEditable(""));
//    }
//
//    @Override
//    public void onClearPasswordClick(View view) {
//        password.set(Editable.Factory.getInstance().newEditable(""));
//    }
//
//
//    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
//        utilsValidation.onTextChangedEmail(s.toString());
//    }
//
//    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
//        utilsValidation.onTextChangedPassword(s.toString());
//    }
//
//    @Override
//    public void setVisibilityImageEmail(int visibility) {
//        visibilityClearEmailImage.set(visibility);
//    }
//
//    @Override
//    public void setVisibilityImagePassword(int visibility) {
//        visibilityClearPasswordImage.set(visibility);
//    }
//
//    @Override
//    public void setTitleColorEmail(int color) {
//        titleColorEmail.set(color);
//    }
//
//    @Override
//    public void setValidAutorisationBtn() {
//        isValidFields.set(isValidEmail && isValidPassword);
//
//    }
//
//    @Override
//    public void setTitleColorPassword(int color) {
//        titleColorPassword.set(color);
//    }
//
//    @Override
//    public void isValidEmail(boolean isValid) {
//        isValidEmail = isValid;
//    }
//
//    @Override
//    public void isValidPassword(boolean isValid) {
//        isValidPassword = isValid;
//    }
//
//}
