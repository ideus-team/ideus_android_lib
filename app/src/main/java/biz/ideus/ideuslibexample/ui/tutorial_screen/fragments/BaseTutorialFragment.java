package biz.ideus.ideuslibexample.ui.tutorial_screen.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.TutorialFragmentBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.TutorialView;
import biz.ideus.ideuslibexample.ui.tutorial_screen.view_models.TutorialFragmentVM;

/**
 * Created by blackmamba on 23.11.16.
 */

public abstract class BaseTutorialFragment extends BaseFragment<TutorialView, TutorialFragmentVM, TutorialFragmentBinding> implements TutorialView{

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        setModelView(this);
        getBinding().imageViewCircle.setBackground(setImage());
        getBinding().tvAbout.setText(setAbout());
        getBinding().tvTitle.setText(setTitle());
        return getBinding().getRoot();
    }

    @Nullable
    @Override
    public Class<TutorialFragmentVM> getViewModelClass() {
        return TutorialFragmentVM.class;
    }
    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.tutorial_fragment, BR.viewModel, getContext());
    }

    public abstract String setTitle();

    public abstract String setAbout();

    public abstract Drawable setImage();

}
