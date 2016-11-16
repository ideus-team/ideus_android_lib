package biz.ideus.ideuslibexample.activities;

import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivitySignUpBinding;
import biz.ideus.ideuslibexample.view_models.SignUpActivityVM;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpActivity extends DLibBindingActivity<ActivitySignUpBinding> {
    private SignUpActivityVM signUpActivityVM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void onInit(View rootView) {
        signUpActivityVM = new SignUpActivityVM(faceBookCallbackManager, twitterAuthClient, googleApiClient, this);
        binding.setSignUpVM(signUpActivityVM);
    }
}
