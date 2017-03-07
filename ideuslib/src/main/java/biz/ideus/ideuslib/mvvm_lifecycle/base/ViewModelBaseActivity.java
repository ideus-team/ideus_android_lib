package biz.ideus.ideuslib.mvvm_lifecycle.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.ProxyViewHelper;
import biz.ideus.ideuslib.mvvm_lifecycle.ViewModelHelper;
import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;


public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>> extends ViewModelBaseEmptyActivity implements IView  {

    @NonNull
    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @NonNull
    public ViewModelHelper<T, R> getmViewModeHelper() {
        return mViewModeHelper;
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<? extends AbstractViewModel<T>> viewModelClass = getViewModelClass();
        // try to extract the ViewModel class from the implementation
        if (viewModelClass == null) {
            //noinspection unchecked
            viewModelClass = (Class<? extends AbstractViewModel<T>>) ProxyViewHelper.getGenericType(getClass(), AbstractViewModel.class);
        }
        mViewModeHelper.onCreate(this, savedInstanceState, viewModelClass, getIntent().getExtras());
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
    public Class<R> getViewModelClass() {
        return null;
    }

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

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }
}
