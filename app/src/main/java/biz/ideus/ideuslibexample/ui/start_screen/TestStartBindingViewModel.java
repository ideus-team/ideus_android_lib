package biz.ideus.ideuslibexample.ui.start_screen;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;

/**
 * Created by user on 28.11.2016.
 */

public class TestStartBindingViewModel extends AbstractViewModel<ITestStartView> {

    public final ObservableField<String> text = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        Log.d("ACTIVITY", "TestStartBindingViewModel");
    }

    public void onFaceBookClick(View view) {

    }


    public void onTwitterClick(View view) {
        Toast.makeText(view.getContext(), "Twitter", Toast.LENGTH_LONG).show();
    }


    public void onGoogleClick(View view) {

    }


    public void onSignInClick(View view) {

    }


    public void onSignUpClick(View view) {

    }


    public void onShowPasswordClick(View view) {

    }


    public void onForgotPasswordClick(View view) {

    }


    public void onClearEmailClick(View view) {

    }


    public void onClearPasswordClick(View view) {

    }

}
