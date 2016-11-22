package biz.ideus.ideuslibexample.ui.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.view.MvvmView;
import biz.ideus.ideuslib.ui_base.viewmodel.MvvmViewModel;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.injection.components.ActivityComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerActivityComponent;
import biz.ideus.ideuslibexample.injection.modules.ActivityModule;

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

/* Base class for Activities when using a view model with data binding.
 * This class provides the binding and the view model to the subclass. The
 * view model is injected and the binding is created when the content view is set.
 * Each subclass therefore has to call the following code in onCreate():
 *    activityComponent().inject(this);
 *    setAndBindContentView(R.layout.my_activity_layout, savedInstanceState);
 *
 * After calling these methods, the binding and the view model is initialized.
 * saveInstanceState() and restoreInstanceState() methods of the view model
 * are automatically called in the appropriate lifecycle events when above calls
 * are made.
 *
 * Your subclass must implement the MvvmView implementation that you use in your
 * view model. */
public abstract class BaseActivity<B extends ViewDataBinding, V extends MvvmViewModel> extends RxFragmentActivity {

    protected B binding;
    @Inject protected V viewModel;

    // Always open a Realm in an Activity for avoiding open/close
    // overhead (a Realm instance is cached for each thread)
//    @Inject Realm realm;

    private ActivityComponent mActivityComponent;

    /* Use this method to set the content view on your Activity. This method also handles
     * creating the binding, setting the view model on the binding and attaching the view. */
    protected final void setAndBindContentView(@LayoutRes int layoutResId, @Nullable Bundle savedInstanceState) {
        if(viewModel == null) { throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)"); }
        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, viewModel);
        //noinspection unchecked
        viewModel.attachView((MvvmView) this, savedInstanceState);
    }

    protected final ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(SampleApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return mActivityComponent;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(viewModel != null) { viewModel.saveInstanceState(outState); }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if(viewModel != null) { viewModel.detachView(); }
//        if(realm != null) { realm.close(); }
        binding = null;
        viewModel = null;
        mActivityComponent = null;
//        realm = null;

    }
}