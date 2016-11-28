package biz.ideus.ideuslibexample.ui.fragments.sign_up_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentSignUpBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.toolbar.ToolbarType;

/**
 * Created by blackmamba on 11.11.16.
 */

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, SignUpFragmentVM> implements MvvmView {


    @Override
    public String getToolbarName(){
        return getString(R.string.sign_up);
    }

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.BACK_BTN_TOOLBAR;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return setAndBindContentView(inflater, container,R.layout.fragment_sign_up,savedInstanceState);
    }
}
