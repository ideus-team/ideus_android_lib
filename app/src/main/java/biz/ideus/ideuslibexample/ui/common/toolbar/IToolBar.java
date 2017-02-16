package biz.ideus.ideuslibexample.ui.common.toolbar;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by user on 02.12.2016.
 */

public interface IToolBar {
    boolean isLeftBtnVisible();
    boolean isRightBtnVisible();
    void onClickLeftBtn(View view);
    void onClickRightBtn(View view);
    Drawable getImageLeftBtn();
    Drawable getImageRightBtn();
    String getToolbarTitle();
    void setToolbarTitle(String title);
    void setVisibilityLinearProgress(boolean isVisible);

}
