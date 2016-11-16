package biz.ideus.ideuslibexample.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import biz.ideus.ideuslib.listeners.SwipeImageTouchListener;
import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemImageViewerPagerBinding;

/**
 * Created by blackmamba on 10.11.16.
 */

public class ImageViewerPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> imageUrlList;
    private ItemImageViewerPagerBinding itemImageBinding;
    private SwipeImageTouchListener.AnimationListener animationListener;

    public ItemImageViewerPagerBinding getItemImageBinding() {
        return itemImageBinding;
    }

    public ImageViewerPagerAdapter(Context context, List<String> imageUrlList
            , SwipeImageTouchListener.AnimationListener animationListener) {
        this.mContext = context;
        this.imageUrlList = imageUrlList;
        this.animationListener = animationListener;

    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        itemImageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_image_viewer_pager, container, false);
        itemImageBinding.ivImage.loadImage(imageUrlList.get(position));
        itemImageBinding.llImageContainer
                .setOnTouchListener(new SwipeImageTouchListener(itemImageBinding.llImageContainer
                        , animationListener));
        container.addView(itemImageBinding.getRoot());
        return itemImageBinding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}