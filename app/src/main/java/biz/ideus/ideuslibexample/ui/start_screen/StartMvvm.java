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

        void onFaceBookClick(View view);
        void onTwitterClick(View view);
        void onGoogleClick(View view);
        void onSignInClick(View view);
        void onSignUpClick(View view);
        void onShowPasswordClick(View view);
        void onForgotPasswordClick(View view);
        void onClearEmailClick(View view);
        void onClearPasswordClick(View view);
    }
}
