package biz.ideus.ideuslibexample.ui.toolbar;

import android.databinding.Bindable;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.baseAdapters.BR;
import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.databinding.ToolbarStandardPowerBinding;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;


public abstract class ToolbarSettingsPower extends BaseViewModel {

    protected BaseActivity mActivity;

    private String title;


    private View leftBtn;

    private View rightBtn;

    public ToolbarSettingsPower() {
    }

    public View getLeftBtn() {
        return leftBtn;
    }
    public View getRightBtn() {
        return rightBtn;
    }

    protected abstract View getLeftBtn(LayoutInflater inflater, ViewGroup viewGroup);

    protected abstract View getRightBtn(LayoutInflater inflater, ViewGroup viewGroup);

    public abstract void onLeftBtnClicked(View view);

    public abstract void onRightBtnClicked(View view);

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    /**
     * Magic class for settings toolbar.
     *
     * @param mActivity Base Activity for more action. (Back button or e.t.c.)
     * @param toolbarPowerBinding Reference for toolbar views. (mvvm principles)
     * @param toolbarTitle Title for setting.
     */
    public void init(BaseActivity mActivity,
                     ToolbarStandardPowerBinding toolbarPowerBinding,
                     String toolbarTitle) {
        this.mActivity = mActivity;
        this.title = toolbarTitle;
        toolbarPowerBinding.setToolbarPower(this);
       notifyPropertyChanged(BR.title);
        toolbarPowerBinding.getRoot().setVisibility(View.VISIBLE);
        ((Toolbar) toolbarPowerBinding.getRoot()).setContentInsetsAbsolute(0, 0);

        leftBtn = getLeftBtn(
                LayoutInflater.from(mActivity),
                toolbarPowerBinding.flBtnToolbarLeft
        );
        rightBtn = getRightBtn(
                LayoutInflater.from(mActivity),
                toolbarPowerBinding.flBtnToolbarRight
        );

        if (leftBtn != null) {
            toolbarPowerBinding.flBtnToolbarLeft.addView(leftBtn);
            toolbarPowerBinding.flBtnToolbarLeft.setOnClickListener(this::onLeftBtnClicked);
        }

        if (rightBtn != null) {
            toolbarPowerBinding.flBtnToolbarRight.addView(rightBtn);
            toolbarPowerBinding.flBtnToolbarRight.setOnClickListener(this::onRightBtnClicked);
        }
    }

    public abstract ToolbarSettingsPower instantiate();
}
