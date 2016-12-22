package biz.ideus.ideuslib.mvvm_lifecycle.binding;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.base.ViewModelBaseActivity;

/**
 * Created by user on 28.11.2016.
 */

public abstract class ViewModelBaseSampleBindingActivity <T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
        extends ViewModelBaseActivity<T, R>
        implements IView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModeHelper.performBinding(this);
//        final ViewDataBinding binding = mViewModeHelper.getBinding();
//        if (binding != null) {
//            return binding.getRoot();
//        } else {
//            throw new IllegalStateException("Binding cannot be null. Perform binding before calling getBinding()");
//        }
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getViewModelHelper().performBinding(this);
//        final ViewDataBinding binding = getViewModelHelper().getBinding();
//        if (binding != null) {
//            return binding.getRoot();
//        } else {
//            throw new IllegalStateException("Binding cannot be null. Perform binding before calling getBinding()");
//        }
//    }


    @SuppressWarnings("unused")
    @NotNull
    public B getBinding() {
        try {
            return (B) mViewModeHelper.getBinding();
        } catch (ClassCastException ex) {
            throw new IllegalStateException("Method getViewModelBindingConfig() has to return same " +
                    "ViewDataBinding type as it is set to base Fragment");
        }
    }
}
