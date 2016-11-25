package biz.ideus.ideuslibexample.ui.fragments.home_fragments.settings_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentSettingsBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding, SettingsFragmentVM> implements MvvmView {

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return setAndBindContentView(inflater, container, R.layout.fragment_settings, savedInstanceState);
    }
}
