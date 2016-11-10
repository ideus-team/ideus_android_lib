package biz.ideus.ideuslib.fragment;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by blackmamba on 10.11.16.
 */

public abstract class BaseImageViewerFragment extends DLibBindingFragment<ViewDataBinding> {
    @Override
    public int getFragmentLayoutId() {
        return 0;
    }

    @Override
    public void onInit(View rootView) {

    }

    public abstract int setResource();
}
