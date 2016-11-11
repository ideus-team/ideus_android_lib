package biz.ideus.ideuslibexample.activities;

import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.SignUpActivityBinding;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpActivity extends DLibBindingActivity<SignUpActivityBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.sign_up_activity;
    }

    @Override
    public void onInit(View rootView) {

    }
}
