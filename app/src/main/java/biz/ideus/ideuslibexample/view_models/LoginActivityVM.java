package biz.ideus.ideuslibexample.view_models;

import android.content.Intent;
import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.activities.SignUpActivity;

/**
 * Created by blackmamba on 11.11.16.
 */

public class LoginActivityVM extends ViewModel {
    private DLibBindingActivity activity;

    public LoginActivityVM(DLibBindingActivity activity) {
        this.activity = activity;
    }

    public void onClickSignUp(View view){
        goToSignUp();
    }

    private void goToSignUp(){
        activity.startActivity(new Intent(activity, SignUpActivity.class));
    }
}
