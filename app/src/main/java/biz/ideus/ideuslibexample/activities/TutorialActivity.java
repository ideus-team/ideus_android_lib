package biz.ideus.ideuslibexample.activities;

import android.view.View;

import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ActivityTutorialBinding;

/**
 * Created by blackmamba on 18.11.16.
 */

public class TutorialActivity extends DLibBindingActivity<ActivityTutorialBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_tutorial;
    }

    @Override
    public void onInit(View rootView) {

    }
}
