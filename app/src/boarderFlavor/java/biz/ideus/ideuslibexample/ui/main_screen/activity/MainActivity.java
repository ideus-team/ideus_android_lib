package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;


public class MainActivity extends AbstractMainActivity {

    private BoardsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
    }


    @Nullable
    @Override
    public Class<MainActivityVM> getViewModelClass() {
        return MainActivityVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.board_main_activity, BR.viewModel, this);
    }

}
