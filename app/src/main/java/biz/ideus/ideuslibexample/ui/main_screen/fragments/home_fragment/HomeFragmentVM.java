package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragmentVM extends BaseSearchVM{

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.home);
    }



    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        Log.d("CharSequence", text.toString());
    }

    @Override
    public void onCancelClick(View view) {
        ((BaseActivity) context).hideKeyboard();
        visibilitySearch.set(View.GONE);
        isFocus.set(false);

    }

}

