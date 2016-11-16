package biz.ideus.ideuslibexample.fragments;

import android.annotation.SuppressLint;
import android.view.View;

import biz.ideus.ideuslib.fragment.DLibBindingFragment;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentTermsOfPolicyBinding;

/**
 * Created by blackmamba on 16.11.16.
 */

public class TermsAndPrivacyFragment extends DLibBindingFragment<FragmentTermsOfPolicyBinding> {
    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_terms_of_policy;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onInit(View rootView) {
        binding.webView.setVerticalScrollBarEnabled(false);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl("file:///android_asset/contract.html");


    }
}
