package biz.ideus.ideuslibexample.activities;

import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.view_models.LoginActivityVM;

/**
 * Created by blackmamba on 10.11.16.
 */

public class LoginActivity extends DLibBindingActivity<ActivityLoginBinding>{
    private LoginActivityVM loginActivityVM;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onInit(View rootView) {
        loginActivityVM = new LoginActivityVM(this);
        binding.setLoginVM(loginActivityVM);
    }
}
