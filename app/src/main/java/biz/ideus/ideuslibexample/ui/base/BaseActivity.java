package biz.ideus.ideuslibexample.ui.base;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.squareup.leakcanary.RefWatcher;

import org.jetbrains.annotations.NotNull;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.base.ViewModelBaseActivity;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.dialogs.CustomDialog;
import biz.ideus.ideuslibexample.injection.components.ActivityComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerActivityComponent;
import biz.ideus.ideuslibexample.injection.modules.ActivityModule;
import biz.ideus.ideuslibexample.rx_buses.RxBusActionEditDialogBtn;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import rx.Subscription;

import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.COPY_TEXT;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.DELETE;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.DETAILS;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.EDIT;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.SKIP_UPDATE;
import static biz.ideus.ideuslibexample.dialogs.DialogCommandModel.UPDATE_NOW;
import static biz.ideus.ideuslibexample.dialogs.DialogModel.NO_INTERNET_CONNECTION;

public abstract class BaseActivity<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
extends ViewModelBaseActivity<T, R>
implements IView {
    protected DialogFragment dialog;
   private Snackbar snackbar;
    private ActivityComponent mActivityComponent;
    protected B binding;

    protected R viewModel;
    protected Subscription rxBusShowDialogSubscription, rxBusActionEditDialogBtnSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.performBinding(this);
        binding = getBinding();
        viewModel = getViewModel();
        rxBusShowDialogSubscription = startRxBusShowDialogSubscription();
        rxBusActionEditDialogBtnSubscription = startRxBusActionEditDialogBtnSubscription();
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
                .subscribe(dialogParams -> {
                    Log.d("getEvents()", dialogParams.getDialogModel().toString());
                    if(dialog != null && dialog.isVisible())
                        dialog.dismiss();
                        switch (dialogParams.getDialogModel()) {
                            case PROGRESS_DIALOG:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(getFragmentManager(), "Dialog");
                                break;
                            case NEW_VERSION_MUST_HAVE_DIALOG:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(getFragmentManager(), "Dialog");
                                break;
                            case NEW_VERSION_RECOMENDED_DIALOG:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(getFragmentManager(), "Dialog");
                                break;
                            case HIDE_PROGRESS_DIALOG:
                                if(dialog != null)
                                dialog.dismiss();
                                break;
                            case NO_INTERNET_CONNECTION:
                                showSneckBarDialog(NO_INTERNET_CONNECTION.resDialogName);
                                break;
                            default:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(getFragmentManager(), "Dialog");
                                break;
                        }
                        RxBusShowDialog.instanceOf().setRxBusCommit();

                });

    }

    public Subscription startRxBusActionEditDialogBtnSubscription() {
        return RxBusActionEditDialogBtn.instanceOf().getEvents().filter(s -> s != null)
                .subscribe(dialogCommand -> {
                    switch (dialogCommand.getDialogCommandModel()) {
                        case COPY_TEXT:
                            Log.d("dialogCommand", COPY_TEXT.name());
                            break;
                        case EDIT:
                            Log.d("dialogCommand", EDIT.name());
                            break;
                        case DETAILS:
                            Log.d("dialogCommand", DETAILS.name());
                            break;
                        case DELETE:
                            Log.d("dialogCommand", DELETE.name());
                            break;
                        case UPDATE_NOW:
                            Log.d("dialogCommand", UPDATE_NOW.name());
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.blank_paper.game.leap_frog"/*BuildConfig.APPLICATION_ID*/)));
                            RxBusActionEditDialogBtn.instanceOf().setRxBusCommit();
                            this.finish();
                            break;
                        case SKIP_UPDATE:
                            Log.d("dialogCommand", SKIP_UPDATE.name());
                            break;
                    }
                });
    }

    private void showSneckBarDialog(int title){
         snackbar = Snackbar
                .make(binding.getRoot(), getString(title), Snackbar.LENGTH_INDEFINITE)
                 .setActionTextColor(getResources().getColor(biz.ideus.ideuslibexample.R.color.color_main))
                .setAction(getString(biz.ideus.ideuslibexample.R.string.retry), view -> snackbar.dismiss());
        snackbar.show();
    }


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(BaseActivity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
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
    public void addFragmentToBackStack(Fragment fragment, Bundle args, boolean addToBackstack, String backstackTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!isAlreadyAddedFragment(fragmentManager, fragment.getClass().getSimpleName())) {
            if (args != null) {
                fragment.setArguments(args);
            }
            FragmentTransaction ft = fragmentManager.beginTransaction()
                    .setCustomAnimations(biz.ideus.ideuslib.R.anim.slide_up, biz.ideus.ideuslib.R.anim.slide_down
                            , biz.ideus.ideuslib.R.anim.slide_up, biz.ideus.ideuslib.R.anim.slide_down)
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName());
            if (addToBackstack) {
                ft.addToBackStack(backstackTag).commit();
                fragmentManager.executePendingTransactions();
            } else {
                ft.commit();
            }
        }
    }

    private boolean isAlreadyAddedFragment(FragmentManager fragmentManager, String fragmentTag){
        return fragmentManager.popBackStackImmediate(fragmentTag , 0) && fragmentManager.findFragmentByTag(fragmentTag) == null;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();

        if (rxBusShowDialogSubscription != null && !rxBusShowDialogSubscription.isUnsubscribed())
            rxBusShowDialogSubscription.unsubscribe();

        if (rxBusActionEditDialogBtnSubscription != null && !rxBusActionEditDialogBtnSubscription.isUnsubscribed())
            rxBusActionEditDialogBtnSubscription.unsubscribe();

        RefWatcher refWatcher = SampleApplication.getRefWatcher(this);
        refWatcher.watch(this);
//        if(viewModel != null) { viewModel.onDestroy(); }
//        binding = null;
//        viewModel = null;
     //   mActivityComponent = null;
    }
}
