package biz.ideus.ideuslibexample.activities;

import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityLoginBinding;

/**
 * Created by blackmamba on 10.11.16.
 */

public class LoginActivity extends DLibBindingActivity<ActivityLoginBinding>{
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onInit(View rootView) {

    }
}
