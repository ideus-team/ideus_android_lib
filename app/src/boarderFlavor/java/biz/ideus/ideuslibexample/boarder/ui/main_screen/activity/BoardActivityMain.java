package biz.ideus.ideuslibexample.boarder.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.BoardMainActivityBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;



public class BoardActivityMain extends BaseActivity<BoardMainView, BoardMainVM, BoardMainActivityBinding> implements BoardMainView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);
    }

    @Nullable
    @Override
    public Class<BoardMainVM> getViewModelClass() {
            return BoardMainVM.class;
        }

        @Nullable
        @Override
        public ViewModelBindingConfig getViewModelBindingConfig() {
            return new ViewModelBindingConfig(R.layout.board_main_activity, BR.viewModel, this);
        }
}
