package biz.ideus.ideuslibexample.fragments;

import android.view.View;

import biz.ideus.ideuslib.fragment.DLibBindingFragment;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentSignUpBinding;
import biz.ideus.ideuslibexample.view_models.SignUpFragmentVM;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpFragment extends DLibBindingFragment<FragmentSignUpBinding> {
    private SignUpFragmentVM signUpFragmentVM;

    @Override
    public int getFragmentLayoutId() {
       return R.layout.fragment_sign_up;
    }

    @Override
    public void onInit(View rootView) {
        signUpFragmentVM = new SignUpFragmentVM(mActivity);
        binding.setSignUpVM(signUpFragmentVM);
    }
}
