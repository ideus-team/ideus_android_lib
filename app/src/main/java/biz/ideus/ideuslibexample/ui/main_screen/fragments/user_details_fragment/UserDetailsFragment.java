package biz.ideus.ideuslibexample.ui.main_screen.fragments.user_details_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentUserDetailsBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 12.01.17.
 */

public class UserDetailsFragment extends BaseFragment<StartView, UserDetailsVM, FragmentUserDetailsBinding>
        implements StartView {

    protected float percentage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("percentage", percentage);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
        if (savedInstanceState != null) {
            percentage = savedInstanceState.getFloat("percentage");
        }
        if (percentage == 0) {
            scrollUp();
        }

        getBinding().appbar.addOnOffsetChangedListener(new AppBarChangeOffsetListener(percentage) {
            @Override
            public void onChangePercentOffset(float percent) {
                percentage = percent;
                changeBackgroundColor(percentage);
            }

            @Override
            public void onCloseFragment(float percent) {
                changeBackgroundColor(percent);
                getFragmentManager().popBackStack();
            }
        });
    }

    private void changeBackgroundColor(float percentage) {
        int color = getActivity().getResources().getColor(R.color.color_main);
        int newColor = Color.argb((int) (percentage * 255), Color.red(color), Color.green(color), Color.blue(color));
        getBinding().collapsingToolbar.setBackgroundColor(newColor);
        getBinding().elementUserDetails.llProfile.setBackgroundColor(newColor);
        getBinding().toolbar.rlMainToolbar.setAlpha(percentage);
    }


    private void scrollUp() {
        getBinding().appbar.setExpanded(false, true);
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_user_details, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<UserDetailsVM> getViewModelClass() {
        return UserDetailsVM.class;
    }
}


