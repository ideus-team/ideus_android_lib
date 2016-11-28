package biz.ideus.ideuslibexample.ui.toolbar;

import android.app.Activity;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.interfaces.ToolBarLeftListener;
import biz.ideus.ideuslibexample.interfaces.ToolBarRightListener;



/**
 * Created by user on 01.07.2016.
 */
// Для биндинга верхнего тулбара
public class ToolbarSettings extends BaseObservable
        implements ToolBarLeftListener, ToolBarRightListener {

    String mScreenName;
    Drawable leftButtonResource;
    Drawable rightButtonResource;

    int leftButtonVisibility = View.VISIBLE;
    int rightButtonVisibility = View.INVISIBLE;
    int switcherVisibility = View.INVISIBLE;

    boolean switcherEnabled, switcherChecked;
    Activity activity;

    public ToolbarSettings(Activity activity) {
        this.activity = activity;
        leftButtonResource = ResourcesCompat
                .getDrawable(activity.getResources(), R.drawable.ic_left_arrow, null);
    }


    @Bindable
    public String getScreenName() {
        return mScreenName;
    }

    public void setScreenName(String mScreenName) {
        this.mScreenName = mScreenName;
        notifyPropertyChanged(BR.screenName);
    }

    @Override
    public void onLeftButtonClick(View v) {
        activity.onBackPressed();
    }

    @Override
    public void onRightButtonClick(View v) {

    }

    @Bindable
    public Drawable getLeftButtonResource() {
        return leftButtonResource;
    }

    public void setLeftButtonResourceId(int leftButtonResourceId) {
        try {
            this.leftButtonResource = ResourcesCompat
                    .getDrawable(activity.getResources(), leftButtonResourceId, null);
        } catch (Resources.NotFoundException e) {
            this.leftButtonResource = null;
        }
        setLeftButtonVisibility(leftButtonResourceId == 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Bindable
    public Drawable getRightButtonResource() {
        return rightButtonResource;
    }

    public void setRightButtonResourceId(int rightButtonResourceId) {
        try {
            this.rightButtonResource = ResourcesCompat
                    .getDrawable(activity.getResources(), rightButtonResourceId, null);
        } catch (Resources.NotFoundException e) {
            this.rightButtonResource = null;
        }
        setRightButtonVisibility(rightButtonResourceId == 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Bindable
    public int getLeftButtonVisibility() {
        return leftButtonVisibility;
    }

    public void setLeftButtonVisibility(int leftButtonVisibility) {
        this.leftButtonVisibility = leftButtonVisibility;
        notifyPropertyChanged(BR.leftButtonVisibility);
        notifyPropertyChanged(BR.leftButtonResource);
    }

    @Bindable
    public int getRightButtonVisibility() {
        return rightButtonVisibility;
    }

    public void setRightButtonVisibility(int rightButtonVisibility) {
        this.rightButtonVisibility = rightButtonVisibility;
        notifyPropertyChanged(BR.rightButtonVisibility);
        notifyPropertyChanged(BR.rightButtonResource);
    }

    @Bindable
    public int getSwitcherVisibility() {
        return switcherVisibility;
    }

    public void setSwitcherVisibility(int switcherVisibility) {
        this.switcherVisibility = switcherVisibility;
        notifyPropertyChanged(BR.switcherVisibility);
    }

    @Bindable
    public boolean isSwitcherEnabled() {
        return switcherEnabled;
    }

    public void setSwitcherEnabled(boolean switcherEnabled) {
        this.switcherEnabled = switcherEnabled;
        notifyPropertyChanged(BR.switcherEnabled);
    }

    @Bindable
    public boolean isSwitcherChecked() {
        return switcherChecked;
    }

    public void setSwitcherChecked(boolean switcherChecked) {
        this.switcherChecked = switcherChecked;
        notifyPropertyChanged(BR.switcherChecked);
    }
}