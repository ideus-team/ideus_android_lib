package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.interfaces.SearchBar;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragmentVM extends AbstractViewModelToolbar<StartView> implements SearchBar {
    private Context context;

   public ObservableField<Integer> visibilitySearch = new ObservableField<>();
   public ObservableField<Boolean> isSearchFieldFocus = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        visibilitySearch.set(View.GONE);
        isSearchFieldFocus.set(false);

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.home);
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

    @BindingAdapter("focus")
    public static void setFocus(EditText editText, boolean isShowKeyboard) {
        if (isShowKeyboard) {
            editText.post(() -> {
                editText.requestFocus();
                editText.onKeyUp(KeyEvent.KEYCODE_DPAD_CENTER, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
            });
        } else {
        }
    }


    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
Log.d("CharSequence", text.toString());
    }

    @Override
    public void onCancelClick(View view) {
        visibilitySearch.set(View.GONE);
        ((BaseActivity)context).hideKeyboard();
        isSearchFieldFocus.set(false);

    }



    @Override
    public void onClickRightBtn(View view) {
        visibilitySearch.set(View.VISIBLE);
        isSearchFieldFocus.set(true);
        


    }
}

