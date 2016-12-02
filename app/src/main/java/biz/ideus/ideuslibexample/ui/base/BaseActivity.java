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
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.base.ViewModelBaseActivity;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.dialogs.CustomAttentionDialog;
import biz.ideus.ideuslibexample.injection.components.ActivityComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerActivityComponent;
import biz.ideus.ideuslibexample.injection.modules.ActivityModule;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import rx.Subscription;

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
public abstract class BaseActivity<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
extends ViewModelBaseActivity<T, R>
implements IView {
    private ActivityComponent mActivityComponent;
    protected B binding;
//    @Inject
    protected R viewModel;
    protected Subscription rxBusShowDialogSubscription;
   // private ActivityComponent mActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.performBinding(this);
        binding = getBinding();
        viewModel = getViewModel();
        rxBusShowDialogSubscription = startRxBusShowDialogSubscription();

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

    public Subscription startRxBusShowDialogSubscription() {
        return RxBusShowDialog.instanceOf().getEvents().filter(s -> s != null)
                .subscribe(dialogModel -> {
                    CustomAttentionDialog.instance(dialogModel, null).show(this.getFragmentManager(), "Dialog");
                });
    }


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
    public void addFragmentToBackStack(FragmentManager fm, @IdRes int containerId, Fragment fragment, String fragmentTag, Bundle args, boolean addToBackstack, String backstackTag) {
        if(args != null) {
            fragment.setArguments(args);}
        FragmentTransaction ft = fm.beginTransaction().add(containerId, fragment, fragmentTag);
        if(addToBackstack) {
            ft.addToBackStack(backstackTag).commit();
            fm.executePendingTransactions();
        } else {
            ft.commitNow();
        }
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        if (rxBusShowDialogSubscription != null && !rxBusShowDialogSubscription.isUnsubscribed())
            rxBusShowDialogSubscription.unsubscribe();
//        if(viewModel != null) { viewModel.onDestroy(); }
//        binding = null;
//        viewModel = null;
     //   mActivityComponent = null;
    }
}
