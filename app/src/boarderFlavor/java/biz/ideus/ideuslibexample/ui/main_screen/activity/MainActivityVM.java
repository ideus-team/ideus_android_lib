package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;



/**
 * Created by blackmamba on 07.02.17.
 */

public class MainActivityVM extends AbstractMainActivityVM {



    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);

    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

}
