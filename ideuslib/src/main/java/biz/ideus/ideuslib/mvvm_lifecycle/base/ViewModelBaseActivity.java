package biz.ideus.ideuslib.mvvm_lifecycle.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.ViewModelHelper;
import biz.ideus.ideuslib.mvvm_lifecycle.ViewModelProvider;


public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>> extends ViewModelBaseEmptyActivity implements IView  {

    @NonNull
    protected final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();
    @Nullable
    private ViewModelProvider mViewModelProvider;
    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        mViewModelProvider = ViewModelProvider.newInstance(this);
        super.onCreate(savedInstanceState);

        mViewModeHelper.onCreate(this, savedInstanceState, getViewModelClass(), getIntent().getExtras());
    }

    /**
     * Call this after your view is ready - usually on the end of {@link android.app.Activity#onCreate(Bundle)}
     * @param view view
     */
    @SuppressWarnings("unused")
    public void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @Nullable
    public abstract Class<R> getViewModelClass();

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @SuppressWarnings("unused")
    @NonNull
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @Override
    public void removeViewModel() {
        mViewModeHelper.removeViewModel(this);
    }
}
