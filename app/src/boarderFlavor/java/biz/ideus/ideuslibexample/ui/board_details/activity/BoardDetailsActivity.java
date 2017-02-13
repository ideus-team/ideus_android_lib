package biz.ideus.ideuslibexample.ui.board_details.activity;

import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.databinding.ActivityMainBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivityVM;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by user on 08.02.2017.
 */

public class BoardDetailsActivity  extends BaseActivity<StartView, MainActivityVM, ActivityMainBinding>
        implements StartView {
    @Nullable
    @Override
    public Class<MainActivityVM> getViewModelClass() {
        return null;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }
}
