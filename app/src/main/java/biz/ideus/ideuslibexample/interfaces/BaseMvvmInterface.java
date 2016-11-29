package biz.ideus.ideuslibexample.interfaces;

/**
 * Created by user on 18.11.2016.
 */

public interface BaseMvvmInterface{
    interface View {

    }

    interface StartActivityVmListener {
        void onFaceBookClick(android.view.View view);
        void onTwitterClick(android.view.View view);
        void onGoogleClick(android.view.View view);
        void onSignInClick(android.view.View view);
        void onSignUpClick(android.view.View view);
        void onShowPasswordClick(android.view.View view);
        void onForgotPasswordClick(android.view.View view);
        void onClearEmailClick(android.view.View view);
        void onClearPasswordClick(android.view.View view);
    }
    interface TutorialVmListener  {
        void onSkipAllClick(android.view.View view);
        void onGetStartedClick(android.view.View view);

    }

}
