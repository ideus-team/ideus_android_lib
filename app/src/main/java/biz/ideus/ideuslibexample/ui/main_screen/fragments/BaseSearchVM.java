package biz.ideus.ideuslibexample.ui.main_screen.fragments;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.interfaces.SearchBar;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 09.12.16.
 */

public class BaseSearchVM extends AbstractViewModelToolbar<StartView> implements SearchBar {

    protected Context context;
    public ObservableField<Integer> visibilitySearch = new ObservableField<>();
    public ObservableField<Boolean> isFocus = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        isFocus.set(false);
        visibilitySearch.set(View.GONE);
    }

    @BindingAdapter("isFocus")
    public static void setFocus(EditText editText, boolean isFocus) {
        if (isFocus) {
            editText.post(() -> {
                editText.requestFocus();
                editText.onKeyUp(KeyEvent.KEYCODE_DPAD_CENTER, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
            });
        }
    }



    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }
    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {

    }

    @Override
    public void onCancelClick(View view) {
        ((BaseActivity) context).hideKeyboard();
        visibilitySearch.set(View.GONE);
        isFocus.set(false);

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public boolean isLeftBtnVisible() {
        return false;
    }

    public boolean isRightBtnVisible() {
        return true;
    }

    @Override
    public Drawable setImageRightBtn() {
        return context.getResources().getDrawable(R.drawable.ic_search);
    }

    @Override
    public void onClickRightBtn(View view) {
        visibilitySearch.set(View.VISIBLE);
        isFocus.set(true);
    }
}
