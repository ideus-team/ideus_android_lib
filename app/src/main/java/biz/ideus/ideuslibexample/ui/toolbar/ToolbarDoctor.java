package biz.ideus.ideuslibexample.ui.toolbar;

public class ToolbarDoctor extends ToolbarStandard {

//    private boolean withBackBtn = false;
//
//    public ToolbarDoctor(boolean withBackBtn) {
//        this.withBackBtn = withBackBtn;
//    }
//
//    @Override
//    public View getRightBtn(LayoutInflater inflater, ViewGroup viewGroup) {
//        ToolbarItemSwitcherBinding switcherBinding = DataBindingUtil.inflate(
//                inflater,
//                R.layout.toolbar_item_switcher,
//                viewGroup,
//                false
//        );
//        switcherBinding.setSwitcherViewModel(
//                MainInstance.INSTANCE.getDoctorSettings().getToolbarDoctorViewModel()
//        );
//        return switcherBinding.getRoot();
//    }
//
//    @Override
//    public int getImageResourceLeftBtn() {
//        return withBackBtn
//                ? R.drawable.ic_toolbar_arrow_back
//                : 0;
//    }
//
//    @Override
//    public biz.ideus.secondoc.view.fragment.toolbar.ToolbarStandard instantiate() {
//        return new ToolbarDoctor(withBackBtn);
//    }
//
//    @Override
//    public void onLeftBtnClicked(View view) {
//        mActivity.onBackPressed();
//    }
}
