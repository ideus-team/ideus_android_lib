package biz.ideus.ideuslibexample.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentTermsOfPolicyBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.base.viewmodel.NoOpViewModel;

/**
 * Created by blackmamba on 16.11.16.
 */

public class TermsAndPrivacyFragment extends BaseFragment<FragmentTermsOfPolicyBinding,NoOpViewModel> implements MvvmView{

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return setAndBindContentView(inflater, container,R.layout.fragment_terms_of_policy,savedInstanceState);
    }
    // setContentWebView("file:///android_asset/contract.html");
    @SuppressLint("SetJavaScriptEnabled")
    private void setContentWebView(String url) {
//        binding.webView.setVerticalScrollBarEnabled(false);
//        binding.webView.getSettings().setJavaScriptEnabled(true);
//        binding.webView.loadUrl(url);
    }

}
