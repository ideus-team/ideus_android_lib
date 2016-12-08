package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.widget.LinearLayout;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.databinding.FragmentHomeBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragment extends BaseFragment<StartView, HomeFragmentVM, FragmentHomeBinding>
implements StartView {

    LinearLayout mRevealView ;
    LinearLayout myView ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);

        myView = getBinding().text;
       // mRevealView = getBinding().text;

        getBinding().btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the center for the clipping circle
                int cx = (myView.getLeft() + myView.getRight()) / 2;
                int cy = (myView.getTop() + myView.getBottom()) / 2;

                // get the final radius for the clipping circle
                int dx = Math.max(cx, myView.getWidth() - cx);
                int dy = Math.max(cy, myView.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);

                float endRadius = (float) Math.hypot(
                        Math.max(cx, myView.getWidth() - cx),
                        Math.max(cy, myView.getHeight() - cy));

                // Android native animator
                Animator animator = io.codetail.animation.ViewAnimationUtils.createCircularReveal(myView, cx, cy, 10, finalRadius, View.LAYER_TYPE_NONE);


               // Animator animator = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
                animator.setInterpolator(new FastOutLinearInInterpolator());
                animator.setDuration(1600);
                animator.start();
            }
        });

//        getBinding().btn.setOnClickListener(view1 -> {
//            int cx = (mRevealView.getRight() + mRevealView.getLeft()) / 2;
//            int cy = mRevealView.getTop();
//            int endradius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());
//
//            Animator mAnimator =
//                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, endradius);
//           // mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//            mAnimator.setInterpolator(new FastOutLinearInInterpolator());
//            mAnimator.setDuration(1600);
//            mAnimator.start();
//
//        });
    }

//    public void showKeyBoard(){
//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(getBinding().itemSearch.etSearch, InputMethodManager.SHOW_IMPLICIT);
//    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_home, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<HomeFragmentVM> getViewModelClass() {
        return HomeFragmentVM.class;
    }

}

