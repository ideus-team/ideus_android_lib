package biz.ideus.ideuslibexample.ui.fragments.sign_up_fragment;

/**
 * Created by blackmamba on 16.11.16.
 */
//@PerFragment
 public class SignUpFragmentVM {}
// extends AutorisationVM implements OnValidateSignUpScreen {
//    private Context ctx;
//    private boolean isValidName = false;
//    private boolean isValidEmail = false;
//    private boolean isValidPassword = false;
//
//
//    @Inject
//    public SignUpFragmentVM(@AppContext Context context) {
//        super(context);
//        this.ctx = context;
//        visibilityLoadingPage.set(View.GONE);
//        this.visibilityClearEmailImage.set(View.INVISIBLE);
//        this.visibilityClearPasswordImage.set(View.INVISIBLE);
//        this.visibilityClearNameImage.set(View.INVISIBLE);
//        this.isPasswordShow.set(true);
//        utilsValidation.setOnValidateField(this);
//    }
//
//
//    public void onClickClearFieldImage(View view) {
//        switch (view.getId()) {
//            case R.id.iv_cancel_email:
//                email.set(Editable.Factory.getInstance().newEditable(""));
//                break;
//            case R.id.iv_cancel_password:
//                password.set(Editable.Factory.getInstance().newEditable(""));
//                break;
//            case R.id.iv_cancel_name:
//                name.set(Editable.Factory.getInstance().newEditable(""));
//                break;
//        }
//    }
//
//    public void onTextChangedName(CharSequence s, int start, int before, int count) {
//        utilsValidation.onTextChangedName(s.toString());
//
//    }
//
//    private boolean isValidData(BaseActivity activity) {
//        if (!(isValidEmail && isValidPassword && isValidName)) {
//           showAttentionDialog(activity, ctx.getString(R.string.invalidate_sign_in_text));
//        }
//        return isValidEmail && isValidPassword && isValidName;
//
//    }
//
//    public void onCreateAccountClick(View view) {
//        if (isValidData((StartActivity)view.getContext())) {
//
//        }
//
//       // showLoadingPage(ctx.getString(R.string.creating_an_account), ctx.getString(R.string.about_creating_account), View.VISIBLE);
//        navigator.get().startActivity(new Intent(((StartActivity)view.getContext()) , TutorialActivity.class));
//    }
//
//    public void onCheckedChangedTermAndPolicy(View v) {
//        isTermAndPolicy.set((!((CheckBox) v).isChecked()));
//    }
//
//    public void onClickGooglePlus(View view) {
//        signInWithGooglePlus((StartActivity) view.getContext());
//    }
//
//    public void onClickTwitterLogin(View view) {
//        onClickTwitterLogin((StartActivity) view.getContext());
//    }
//
//    public void onClickFaceBookLogin(View view) {
//        onClickFaceBookLogin((StartActivity) view.getContext());
//    }
//
//    public void onClickTermsAndPolicy(View view) {
//
//    }
//
//    // CheckBox change listener
//    public void onCheckedChanged(View v) {
//        isPasswordShow.set((!((CheckBox) v).isChecked()));
//    }
//
//    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
//        utilsValidation.onTextChangedEmail(s.toString());
//    }
//
//    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
//        utilsValidation.onTextChangedPassword(s.toString());
//
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
//        isValidFields.set(isValidEmail && isValidPassword && isValidName);
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
//    @Override
//    public void setVisibilityImageName(int visibility) {
//        visibilityClearNameImage.set(visibility);
//    }
//
//    @Override
//    public void isValidName(boolean isValid) {
//        isValidName = isValid;
//    }
//
//    @Override
//    public void setTitleColorName(int color) {
//        titleColorName.set(color);
//    }
//}
