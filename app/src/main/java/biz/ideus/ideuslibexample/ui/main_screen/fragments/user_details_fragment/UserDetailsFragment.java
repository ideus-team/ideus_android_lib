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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;
import static biz.ideus.ideuslibexample.utils.Constants.PERCENTAGE_ALPHA;

/**
 * Created by blackmamba on 12.01.17.
 */

public class UserDetailsFragment extends BaseFragment<StartView, UserDetailsVM, FragmentUserDetailsBinding>
        implements StartView {

    private float percentage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(PERCENTAGE_ALPHA, percentage);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);

        if (savedInstanceState != null) {
            percentage = savedInstanceState.getFloat(PERCENTAGE_ALPHA);
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

        setPeopleEntityToView(getViewModel().getPeopleId());
    }



    private void setPeopleEntityToView(String peopleId){
        requeryApi.getPeopleEntity(peopleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(peopleEntity1 -> {
                    getBinding().setPeople(peopleEntity1);
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



