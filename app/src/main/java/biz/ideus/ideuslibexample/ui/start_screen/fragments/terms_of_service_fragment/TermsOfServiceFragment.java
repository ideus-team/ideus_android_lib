package biz.ideus.ideuslibexample.ui.start_screen.fragments.terms_of_service_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentTermsOfServiceBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;


/**
 * Created by blackmamba on 16.11.16.
 */
public class TermsOfServiceFragment extends BaseFragment<StartView, TermsOfServiceVM, FragmentTermsOfServiceBinding> implements StartView {


    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        setModelView(this);
        return getBinding().getRoot();
    }

    @Nullable
    @Override
    public Class<TermsOfServiceVM> getViewModelClass() {
        return TermsOfServiceVM.class;
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_terms_of_service, BR.viewModel, getContext());
    }
}
