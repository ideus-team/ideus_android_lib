package biz.ideus.ideuslibexample.ui.main_screen.fragments.image_viewer_fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;
import biz.ideus.ideuslibexample.ui.main_screen.MainView;

/**
 * Created by blackmamba on 08.12.16.
 */

public class ImageViewerVM extends AbstractViewModel<MainView> implements ImageViewerPagerAdapter.PageSelectedlistener {
    private Context context;
private int currentImagePosition;

    public int getCurrentImagePosition() {
        return currentImagePosition;
    }

    private ImageViewerPagerAdapter adapter;

    public void setAdapter(ImageViewerPagerAdapter adapter) {
        this.adapter = adapter;
    }


    public ObservableField<String> counterImageText = new ObservableField<>();
    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        counterImageText.set("");
    }

    @Override
    public void onBindView(@NonNull MainView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
        adapter.setSelectedlistener(this);
    }

    public void hideImageViewer(View view){
        ((BaseActivity)context).onBackPressed();
    }

    @Override
    public void onPageSelected(int position, int allImagesCount) {
        currentImagePosition = position;
        counterImageText.set(position + 1 + "/" + allImagesCount);
    }

}
