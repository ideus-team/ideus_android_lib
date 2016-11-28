package biz.ideus.ideuslibexample.ui.fragments.home_fragments.people_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentPeopleBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.toolbar.ToolbarType;

/**
 * Created by blackmamba on 25.11.16.
 */

public class PeopleFragment extends BaseFragment<FragmentPeopleBinding, PeopleFragmentVM> implements MvvmView {
    @Override
    public String getToolbarName(){
        return getString(R.string.people);
    }

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.DEFAULT;
    }
    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return setAndBindContentView(inflater, container, R.layout.fragment_people, savedInstanceState);
    }
}
