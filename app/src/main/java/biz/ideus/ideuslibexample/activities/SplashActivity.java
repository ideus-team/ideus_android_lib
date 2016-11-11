package biz.ideus.ideuslibexample.activities;

import android.content.Intent;
import android.view.View;

import java.util.concurrent.TimeUnit;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivitySplashBinding;
import rx.Observable;

/**
 * Created by blackmamba on 10.11.16.
 */

public class SplashActivity extends DLibBindingActivity<ActivitySplashBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onInit(View rootView) {
        goToLoginScreen();
    }

    private void goToLoginScreen() {
        Observable.just(1)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext(number -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .subscribe();
    }

}
