package biz.ideus.ideuslibexample.ui.image_viewer_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslib.mvvm_lifecycle.binding.ViewModelBindingConfig;
import biz.ideus.ideuslibexample.BR;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.FragmentImageViewerBinding;
import biz.ideus.ideuslibexample.ui.base.BaseFragment;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;


/**
 * Created by blackmamba on 10.11.16.
 */

public class ImageViewerFragment extends BaseFragment<StartView, ImageViewerVM, FragmentImageViewerBinding>
        implements StartView {

    public static String CURRENT_IMAGE_POSITION = "CURRENT_IMAGE_POSITION";
    public static String URL_LIST = "URL_LIST";

    private ImageViewerPagerAdapter pagerAdapter;
    private List<String> imageUrlList = new ArrayList<>();
    private int imagePosition;

    public ImageViewerFragment() {
    }

    @SuppressLint("ValidFragment")
    public ImageViewerFragment(List<String> imageList, int imagePosition) {
        this.imageUrlList = imageList;
        this.imagePosition = imagePosition;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        restoreFromBandle(savedInstanceState);
        initAdapter();
    }

    private void restoreFromBandle(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            imagePosition = savedInstanceState.getInt(CURRENT_IMAGE_POSITION);
            imageUrlList = savedInstanceState.getStringArrayList(URL_LIST);

        }
    }

    private void initAdapter() {
        pagerAdapter = new ImageViewerPagerAdapter(getActivity(), imageUrlList, imagePosition);
        getBinding().pager.setAdapter(pagerAdapter);
        getBinding().pager.setCurrentItem(imagePosition, false);
        getBinding().pager.addOnPageChangeListener(pagerAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().setAdapter(pagerAdapter);
        setModelView(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_IMAGE_POSITION, getViewModel().getCurrentImagePosition());
        outState.putStringArrayList(URL_LIST, (ArrayList) imageUrlList);
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_image_viewer, BR.viewModel, getActivity());
    }

    @Nullable
    @Override
    public Class<ImageViewerVM> getViewModelClass() {
        return ImageViewerVM.class;
    }

}
