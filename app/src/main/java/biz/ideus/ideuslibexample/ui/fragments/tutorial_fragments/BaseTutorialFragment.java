package biz.ideus.ideuslibexample.ui.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.databinding.TutorialFragmentBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.view_models.TutorialFragmentVM;

/**
 * Created by blackmamba on 23.11.16.
 */

public abstract class BaseTutorialFragment extends BaseFragment<TutorialFragmentBinding, TutorialFragmentVM> implements MvvmView {
    TutorialPagerAdapter adapter;

//    public BaseTutorialFragment setAdapter(TutorialPagerAdapter adapter) {
//        this.adapter = adapter;
//        return this;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
//        viewModel.setAdapter(adapter);
        View view = setAndBindContentView(inflater, container, R.layout.tutorial_fragment, savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageViewCircle.setBackground(setImage());
        binding.tvAbout.setText(setAbout());
        binding.tvTitle.setText(setTitle());
    }

    public abstract String setTitle();

    public abstract String setAbout();

    public abstract Drawable setImage();

}
