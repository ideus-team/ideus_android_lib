package biz.ideus.ideuslibexample.ui.common.toolbar;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ProgressBar;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.SampleApplication;

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
        //TODO make interface
        //((BaseActivity) context).onBackPressed();
    }

    @Override
    public void onClickRightBtn(View view) {

    }

    @Override
    public Drawable getImageRightBtn() {
        return null;
    }

    @Override
    public Drawable getImageLeftBtn() {
            return SampleApplication.getInstance().getResources().getDrawable(R.drawable.ic_left_arrow);
    }

    @BindingAdapter("progressColor")
    public static void setProgressColor(ProgressBar progressBar, boolean isColorChange) {
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }


    @Override
    public void setVisibilityLinearProgress(boolean isVisible){}

}
