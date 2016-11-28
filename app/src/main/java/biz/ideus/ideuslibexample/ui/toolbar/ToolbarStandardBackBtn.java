package biz.ideus.ideuslibexample.ui.toolbar;

import android.view.View;

import biz.ideus.ideuslibexample.R;


public class ToolbarStandardBackBtn extends ToolbarStandard {

    @Override
    public int getImageResourceLeftBtn() {
        return R.drawable.ic_left_arrow;
    }

    @Override
    public void onLeftBtnClicked(View view) {
        mActivity.onBackPressed();
    }

    @Override
    public ToolbarStandard instantiate() {
        return new ToolbarStandardBackBtn();
    }
}
