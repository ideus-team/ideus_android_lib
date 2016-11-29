package biz.ideus.ideuslibexample.adapters;

/**
 * Created by blackmamba on 23.11.16.
 */

//@PerActivity
//public class TutorialPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{
//    private final int PAGE_COUNT = 4;
//    private OnPagerListener onPagerListener;
//
//    public void setOnPagerListener(OnPagerListener onPagerListener) {
//        this.onPagerListener = onPagerListener;
//    }
//
//    List<BaseTutorialFragment> fragmentList = new ArrayList<>();
//
//    @Inject
//    public TutorialPagerAdapter(FragmentManager fm) {
//        super(fm);
//        setFragments();
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//            return fragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return PAGE_COUNT;
//    }
//
//    private void setFragments(){
//        fragmentList.add(new AboutAppFragment());
//        fragmentList.add(new PersonalNetworkFragment());
//        fragmentList.add(new AccountProtectionFragment());
//        fragmentList.add(new CustomisationFragment());
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        onPagerListener.selectPage(position);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    public interface OnPagerListener {
//        void selectPage(int position);
//    }
//}
