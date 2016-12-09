package biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.tutorial_fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.TutorialFragmentBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.TutorialView;

/**
 * Created by blackmamba on 23.11.16.
 */

public abstract class BaseTutorialFragment extends BaseFragment<TutorialView, TutorialFragmentVM, TutorialFragmentBinding> implements TutorialView{


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentComponent().inject(this);
        setModelView(this);
        getBinding().imageViewCircle.setBackground(getImage());
        getBinding().tvTitle.setText(getTitle());
        if(getBinding().tvAboutTitle != null)
        getBinding().tvAboutTitle.setText(getAbout());
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

    public abstract String getTitle();

    public abstract String getAbout();

    public abstract Drawable getImage();

}
