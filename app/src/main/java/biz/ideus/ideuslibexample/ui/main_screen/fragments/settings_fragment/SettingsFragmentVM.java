package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 25.11.16.
 */

public class SettingsFragmentVM extends AbstractViewModelToolbar<StartView> {
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.settings);
    }

    @Override
    public boolean isLeftBtnVisible() {
        return false;
    }



}
