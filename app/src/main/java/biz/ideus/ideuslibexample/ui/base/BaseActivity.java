package biz.ideus.ideuslibexample.ui.base;

import android.app.DialogFragment;
import android.databinding.ViewDataBinding;
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

import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.RefWatcher;

import org.jetbrains.annotations.NotNull;

import biz.ideus.ideuslib.Utils.NetworkUtil;
import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslib.mvvm_lifecycle.base.ViewModelBaseActivity;
import biz.ideus.ideuslibexample.SampleApplication;
import biz.ideus.ideuslibexample.data.remote.socket_chat.WebSocketClient;
import biz.ideus.ideuslibexample.dialogs.CustomDialog;
import biz.ideus.ideuslibexample.dialogs.DialogModel;
import biz.ideus.ideuslibexample.dialogs.DialogParams;
import biz.ideus.ideuslibexample.injection.components.ActivityComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerActivityComponent;
import biz.ideus.ideuslibexample.injection.modules.ActivityModule;
import biz.ideus.ideuslibexample.rx_buses.RxBusShowDialog;
import rx.Subscriber;
import rx.Subscription;

import static biz.ideus.ideuslibexample.dialogs.DialogModel.SOCKET_UNFORTUNATELY_DIALOG;
import static biz.ideus.ideuslibexample.utils.Constants.NO_INTERNET_CONNECTION;

public abstract class BaseActivity<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
        extends ViewModelBaseActivity<T, R>
        implements IView {
    protected DialogFragment dialog;
    private Snackbar snackbar;
    private ActivityComponent mActivityComponent;
    protected B binding;

    protected R viewModel;
    protected Subscription rxBusShowDialogSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.performBinding(this);
        binding = getBinding();
        viewModel = getViewModel();
        rxBusShowDialogSubscription = startRxBusShowDialogSubscription();

    }

    private void checkNetworkConnection() {
        if (!NetworkUtil.isNetworkConnected(this) && !(boolean) Hawk.get(NO_INTERNET_CONNECTION)) {
            Hawk.put(NO_INTERNET_CONNECTION, true);
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogModel.NO_INTERNET_CONNECTION);
        }
    }

    protected final ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(SampleApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return mActivityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkNetworkConnection();
    }

    public Subscription startRxBusShowDialogSubscription() {
        return RxBusShowDialog.instanceOf().getEvents().filter(s -> s != null)
                .subscribe(new Subscriber<DialogParams>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DialogParams dialogParams) {
                        Log.d("getEvents()", dialogParams.getDialogModel().toString());
                        if (dialog != null && dialog.isVisible())
                            dialog.dismiss();
                        switch (dialogParams.getDialogModel()) {
                            case PROGRESS_DIALOG:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(BaseActivity.this.getFragmentManager(), "Dialog");
                                break;
                            case HIDE_PROGRESS_DIALOG:
                                if (dialog != null)
                                    dialog.dismiss();
                                break;
                            case NO_INTERNET_CONNECTION:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(BaseActivity.this.getFragmentManager(), "Dialog");
                                break;
                            case SOCKET_UNFORTUNATELY_DIALOG:
                                if (snackbar != null) {
                                    snackbar.dismiss();
                                }
                                BaseActivity.this.showSneckBarDialog(SOCKET_UNFORTUNATELY_DIALOG.resDialogName, v -> {
                                    snackbar.dismiss()
                                    ;
                                    WebSocketClient.getInstance().connectHttpClient();
                                });
                                break;
                            default:
                                dialog = CustomDialog.instance(dialogParams);
                                dialog.show(BaseActivity.this.getFragmentManager(), "Dialog");
                                break;
                        }
                        RxBusShowDialog.instanceOf().setRxBusCommit();

                    }

                });


    }

    private void showSneckBarDialog(int title, View.OnClickListener listener) {
        snackbar = Snackbar
                .make(binding.getRoot(), getString(title), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(biz.ideus.ideuslibexample.R.color.color_main))
                .setAction(getString(biz.ideus.ideuslibexample.R.string.retry), listener);
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

    private boolean isAlreadyAddedFragment(FragmentManager fragmentManager, String fragmentTag) {
        return fragmentManager.popBackStackImmediate(fragmentTag, 0) && fragmentManager.findFragmentByTag(fragmentTag) == null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();

        if (rxBusShowDialogSubscription != null && !rxBusShowDialogSubscription.isUnsubscribed())
            rxBusShowDialogSubscription.unsubscribe();

        RefWatcher refWatcher = SampleApplication.getRefWatcher(this);
        refWatcher.watch(this);
//        if(viewModel != null) { viewModel.onDestroy(); }
//        binding = null;
//        viewModel = null;
        //   mActivityComponent = null;
    }
}
