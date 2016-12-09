package biz.ideus.ideuslibexample.ui.start_screen.fragments.forgot_password_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentForgotPasswordBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 16.11.16.
 */

public class ForgotPasswordFragment extends BaseFragment<StartView, ForgotPasswordVM, FragmentForgotPasswordBinding> implements StartView{

        @Nullable
        @Override
        public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            fragmentComponent().inject(this);
            setModelView(this);
            return getBinding().getRoot();
        }

    @Nullable
    @Override
    public Class<ForgotPasswordVM> getViewModelClass() {
        return ForgotPasswordVM.class;
    }
    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_forgot_password, BR.viewModel, getContext());
    }
}
