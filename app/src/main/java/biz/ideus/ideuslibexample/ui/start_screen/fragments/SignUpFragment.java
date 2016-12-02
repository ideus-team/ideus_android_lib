package biz.ideus.ideuslibexample.ui.start_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentSignUpBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.view_models.SignUpFragmentVM;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpFragment extends BaseFragment<StartView, SignUpFragmentVM, FragmentSignUpBinding> implements StartView{

@Nullable
@Override
public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        setModelView(this);
        return getBinding().getRoot();
        }

@Nullable
@Override
public Class<SignUpFragmentVM> getViewModelClass() {
        return SignUpFragmentVM.class;
}
@Nullable
@Override
public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_sign_up, BR.viewModel, getContext());
        }
}
