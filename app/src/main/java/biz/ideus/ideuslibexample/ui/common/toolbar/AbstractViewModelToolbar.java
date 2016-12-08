package biz.ideus.ideuslibexample.ui.common.toolbar;

import android.graphics.drawable.Drawable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;

/**
 * Created by user on 02.12.2016.
 */

public abstract class AbstractViewModelToolbar<T extends IView> extends AbstractViewModel<T>
        implements IToolBar {


    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }

    @Override
    public boolean isRightBtnVisible() {
        return false;
    }

    @Override
    public void onClickLeftBtn(View view) {
        ((BaseActivity) getView().getViewModelBindingConfig().getContext()).onBackPressed();
    }

    @Override
    public void onClickRightBtn(View view) {

    }

    @Override
    public Drawable setImageRightBtn() {
        return null;
    }

    @Override
    public Drawable setImageLeftBtn() {
        return getView().getViewModelBindingConfig().getContext().getResources().getDrawable(R.drawable.ic_left_arrow);
    }

}
