package biz.ideus.ideuslibexample.ui.main_screen.fragments.image_viewer_fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemImageViewerPagerBinding;

/**
 * Created by blackmamba on 10.11.16.
 */

public class ImageViewerPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private int imagePosition;
    private List<String> imageUrlList;
    private PageSelectedlistener selectedlistener;
    private ItemImageViewerPagerBinding itemImageBinding;

    public void setSelectedlistener(PageSelectedlistener selectedlistener) {
        this.selectedlistener = selectedlistener;
       this.onPageSelected(imagePosition);
    }

    public ItemImageViewerPagerBinding getItemImageBinding() {
        return itemImageBinding;
    }

    public ImageViewerPagerAdapter(Context context, List<String> imageUrlList, int imagePosition) {
        this.mContext = context;
        this.imageUrlList = imageUrlList;
        this.imagePosition = imagePosition;
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
        container.addView(itemImageBinding.getRoot());
        return itemImageBinding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectedlistener.onPageSelected(position, getCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
   public interface PageSelectedlistener{
       void onPageSelected(int position, int allImageCount);
   }
}