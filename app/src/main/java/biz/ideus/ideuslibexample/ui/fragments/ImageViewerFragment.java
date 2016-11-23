package biz.ideus.ideuslibexample.ui.fragments;

/**
 * Created by blackmamba on 10.11.16.
 */
/*
public class ImageViewerFragment extends DLibBindingFragment<FragmentImageViewerBinding>
        implements SwipeImageTouchListener.AnimationListener {
    private List<String> imageUrlList = new ArrayList<>();
    private ImageViewerPagerAdapter imageViewerPagerAdapter;
    private int imagePosition;

    public ImageViewerFragment setImageUrlList(List<String> imageList, int imagePosition) {
        this.imageUrlList = imageList;
        this.imagePosition = imagePosition;
        return this;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_image_viewer;
    }

    @Override
    public void onInit(View rootView) {
        imageViewerPagerAdapter = new ImageViewerPagerAdapter(mActivity, imageUrlList, this);
        binding.pager.setAdapter(imageViewerPagerAdapter);
        binding.pager.setCurrentItem(imagePosition, false);
        binding.tvCountImages.setText("/" + imageUrlList.size());
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset
                    , int positionOffsetPixels) {
                binding.tvCurrentCountImages.setText(position + 1 + "");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void closeFragment() {
        getFragmentManager().popBackStack();
    }
    }


*/