package biz.ideus.ideuslibexample.ui.base;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.base.ViewModelBaseFragment;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.injection.components.DaggerFragmentComponent;
import biz.ideus.ideuslibexample.injection.components.FragmentComponent;
import biz.ideus.ideuslibexample.injection.modules.FragmentModule;

/* Base class for Fragments when using a view model with data binding.
 * This class provides the binding and the view model to the subclass. The
 * view model is injected and the binding is created when the content view is set.
 * Each subclass therefore has to call the following code in onCreateView():
 *    if(viewModel == null) { fragmentComponent().inject(this); }
 *    return setAndBindContentView(inflater, container, R.layout.my_fragment_layout, savedInstanceState);
 *
 * After calling these methods, the binding and the view model is initialized.
 * saveInstanceState() and restoreInstanceState() methods of the view model
 * are automatically called in the appropriate lifecycle events when above calls
 * are made.
 *
 * Your subclass must implement the MvvmView implementation that you use in your
 * view model. */
public abstract class BaseFragment<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
        extends ViewModelBaseFragment<T, R>
        implements IView {

    private FragmentComponent mFragmentComponent;

    protected final FragmentComponent fragmentComponent() {
        if(mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .appComponent(SampleApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModelHelper().performBinding(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getViewModelHelper().performBinding(this);
        final ViewDataBinding binding = getViewModelHelper().getBinding();
        if (binding != null) {
            return binding.getRoot();
        } else {
            throw new IllegalStateException("Binding cannot be null. Perform binding before calling getBinding()");
        }
    }

    @SuppressWarnings("unused")
    @NotNull
    public B getBinding() {
        try {
            return (B) getViewModelHelper().getBinding();
        } catch (ClassCastException ex) {
            throw new IllegalStateException("Method getViewModelBindingConfig() has to return same " +
                    "ViewDataBinding type as it is set to base Fragment");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentComponent = null;
    }
}

//
//    protected B binding;
//    @Inject protected V viewModel;
//
//    private FragmentComponent mFragmentComponent;
//
//    protected final FragmentComponent fragmentComponent() {
//        if(mFragmentComponent == null) {
//            mFragmentComponent = DaggerFragmentComponent.builder()
//                    .appComponent(SampleApplication.getAppComponent())
//                    .fragmentModule(new FragmentModule(this))
//                    .build();
//        }
//
//        return mFragmentComponent;
//    }
//
//    /* Use this method to inflate the content view for your Fragment. This method also handles
//     * creating the binding, setting the view model on the binding and attaching the view. */
//    protected final View setAndBindContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutResId, Bundle savedInstanceState) {
//        if(viewModel == null) { throw new IllegalStateException("viewModel must not be null and should be injected via fragmentComponent().inject(this)"); }
//        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
//        //binding.setVariable(BR.vm, viewModel);
//        //noinspection unchecked
//        viewModel.attachView((MvvmView) this, savedInstanceState);
//        return binding.getRoot();
//    }
//
//    @Override
//    @CallSuper
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if(viewModel != null) { viewModel.saveInstanceState(outState); }
//    }
//
//    @Override
//    @CallSuper
//    public void onDestroyView() {
//        super.onDestroyView();
//        if(viewModel != null) { viewModel.detachView(); }
//        binding = null;
//        viewModel = null;
//    }
//
//    @Override
//    @CallSuper
//    public void onDestroy() {
//        mFragmentComponent = null;
//        super.onDestroy();
//    }
//}
