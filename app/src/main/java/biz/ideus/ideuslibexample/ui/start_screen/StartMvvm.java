package biz.ideus.ideuslibexample.ui.start_screen;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslib.ui_base.viewmodel.MvvmViewModel;

/**
 * Created by user on 18.11.2016.
 */

public interface StartMvvm {
    interface View extends MvvmView {

    }

    interface ViewModel extends MvvmViewModel<StartMvvm.View> {

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

}
