package biz.ideus.ideuslibexample.ui.start_screen.fragments.terms_of_service_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;

/**
 * Created by blackmamba on 22.11.16.
 */

public class TermsOfServiceVM extends AbstractViewModelToolbar<StartView> {
    private Context context;

public ObservableField<String> uri = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        uri.set(Constants.TERMS_OF_SERVICE_URI);

    }



    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("setContent")
    public static void setContent(WebView webView, String url) {
        if (url != null) {
    webView.setVerticalScrollBarEnabled(false);
       webView.getSettings().setJavaScriptEnabled(true);
       webView.loadUrl(url);
        } else {
        }
    }


    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }


    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.terms_of_service);
    }
    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }
}


