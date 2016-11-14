package biz.ideus.ideuslibexample.activities;

import android.content.Intent;
import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;
import biz.ideus.ideuslibexample.view_models.LoginActivityVM;

import static biz.ideus.ideuslibexample.SampleApplication.faceBookCallbackManager;
import static biz.ideus.ideuslibexample.SampleApplication.twitterAuthClient;

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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        faceBookCallbackManager.onActivityResult(requestCode, resultCode, data);
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

}
