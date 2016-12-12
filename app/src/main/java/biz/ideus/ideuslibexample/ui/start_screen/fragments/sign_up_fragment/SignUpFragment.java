package biz.ideus.ideuslibexample.ui.start_screen.fragments.sign_up_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentSignUpBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpFragment extends BaseFragment<StartView, SignUpFragmentVM, FragmentSignUpBinding> implements StartView {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentComponent().inject(this);
        setModelView(this);
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
