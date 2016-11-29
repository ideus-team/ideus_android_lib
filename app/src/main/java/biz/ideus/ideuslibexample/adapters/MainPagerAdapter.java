package biz.ideus.ideuslibexample.adapters;

/**
 * Created by blackmamba on 24.11.16.
 */

//@PerActivity
//public class MainPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{
//    private final int PAGE_COUNT = 3;
//
//    List<BaseFragment> fragmentList = new ArrayList<>();
//
//    @Inject
//    public MainPagerAdapter(FragmentManager fm) {
//        super(fm);
//        setFragments();
//    }
//
//    public int getTabResource(int position) {
//        switch (position) {
//            case 0 : return R.layout.item_tab_left;
//            case 1 : return R.layout.item_tab_center;
//            case 2 : return R.layout.item_tab_right;
//            default:
//                return 0;
//        }
//    };
//
//    @Override
//    public Fragment getItem(int position) {
//        return fragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return PAGE_COUNT;
//    }
//
//    private void setFragments(){
//        fragmentList.add(new HomeFragment());
//        fragmentList.add(new PeopleFragment());
//        fragmentList.add(new SettingsFragment());
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//}
