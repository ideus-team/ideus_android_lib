package biz.ideus.ideuslib.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.trello.rxlifecycle.components.RxFragment;

import biz.ideus.ideuslib.R;
import biz.ideus.ideuslib.Utils.BackPressImpl;
import biz.ideus.ideuslib.activity.DLibBindingActivity;
import biz.ideus.ideuslib.interfaces.OnBackPressListener;

/**
 * Created by user on 08.11.2016.
 */

public abstract class DLibBindingFragment<T extends ViewDataBinding> extends RxFragment
        implements OnBackPressListener {
    protected T binding;
    protected DLibBindingActivity mActivity;
    protected View mFragmentRootView;

    public Fragment getLastAddedFragment() {
        return lastAddedFragment;
    }

    private Fragment lastAddedFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (DLibBindingActivity) activity;
    }

    public abstract int getFragmentLayoutId();

    public abstract void onInit(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (getFragmentLayoutId() == 0) {
            throw new RuntimeException("You must pass to 'getFragmentLayoutId()' your layout.");
        }

        binding = DataBindingUtil.inflate(inflater, getFragmentLayoutId(), container, false);


        onInit(mFragmentRootView);

        return mFragmentRootView.getRootView();
    }



    public DLibBindingActivity getBaseActivity() {
        return mActivity;
    }


    public String getScreenName() {
        return null;
    }

//    protected View onInit(LayoutInflater inflater, ViewGroup viewGroup) {
//        return inflater.inflate(getFragmentLayoutId(), viewGroup, false);
//    }

    public void addChildHomeFragment(DLibBindingFragment fragment, int containerId) {
        addChildFragment(fragment, containerId, "home");
    }

    private void addChildFragment(DLibBindingFragment fragment, int containerId, String tag) {

        if (lastAddedFragment == null || !lastAddedFragment.isAdded()
                || !(lastAddedFragment.getClass().equals(fragment.getClass()))) {
            lastAddedFragment = fragment;
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out
                    , android.R.animator.fade_in, android.R.animator.fade_out);
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.replace(containerId, fragment, tag).commit();
        }
    }

    public void replaceChildFragment(DLibBindingFragment fragment, int containerId) {
        if (lastAddedFragment == null || !lastAddedFragment.isAdded()
                || !(lastAddedFragment.getClass().equals(fragment.getClass()))) {
            lastAddedFragment = fragment;
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
            transaction.replace(containerId, fragment, null).commit();
        }
    }

    public void replaceSpecialisationFragment(DLibBindingFragment fragment, int containerId) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        transaction.replace(containerId, fragment, null).commit();
    }

    @Override
    public boolean onBackPressed() {
        Log.d("backpress","fragment");
        return new BackPressImpl(this).onBackPressed();
    }



    public void openKeyboard(final View view) {
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager keyboard = (InputMethodManager) mActivity
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    keyboard.showSoftInput(view, 0);
                }
            }, 50);
        }
    }

    public void hideKeyboard(final View view) {
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager inputMethodManager = (InputMethodManager) mActivity
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }, 50);
        }
    }


}
