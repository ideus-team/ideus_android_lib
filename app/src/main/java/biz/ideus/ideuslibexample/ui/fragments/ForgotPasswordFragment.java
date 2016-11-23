package biz.ideus.ideuslibexample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentForgotPasswordBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.view_models.ForgotPasswordVM;

/**
 * Created by blackmamba on 16.11.16.
 */

public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordVM> implements MvvmView {

        @Nullable
        @Override
        public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            fragmentComponent().inject(this);
            return setAndBindContentView(inflater, container,R.layout.fragment_forgot_password,savedInstanceState);
        }
}
