package biz.ideus.ideuslibexample.ui.toolbar;

//public class ToolbarStandard extends ToolbarSettingsPower {
//
//    private int imageResourceLeftBtn = 0;
//    private int imageResourceRightBtn = 0;
//
//    private View.OnClickListener clickListenerLeftBtn;
//    private View.OnClickListener clickListenerRightBtn;
//
//    public int getImageResourceLeftBtn() {
//        return 0;
//    }
//
//    public int getImageResourceRightBtn() {
//        return 0;
//    }
//
//    public ToolbarStandard addLeftBtn(int imageResource, View.OnClickListener onClickListener) {
//        this.imageResourceLeftBtn = imageResource;
//        this.clickListenerLeftBtn = onClickListener;
//        return this;
//    }
//
//    public ToolbarStandard addRightBtn(int imageResource, View.OnClickListener onClickListener) {
//        this.imageResourceRightBtn = imageResource;
//        this.clickListenerRightBtn = onClickListener;
//        return this;
//    }
//
//    @Override
//    public View getLeftBtn(LayoutInflater inflater, ViewGroup viewGroup) {
//        if (imageResourceLeftBtn == 0) {
//            imageResourceLeftBtn = getImageResourceLeftBtn();
//        }
//        if (imageResourceLeftBtn == 0) {
//            return null;
//        }
//
//        ImageView imageView = (ImageView) inflater.inflate(
//                R.layout.toolbar_item_button,
//                viewGroup,
//                false
//        );
//        imageView.setImageResource(
//                imageResourceLeftBtn
//        );
//        return imageView;
//    }
//
//    @Override
//    public View getRightBtn(LayoutInflater inflater, ViewGroup viewGroup) {
//        if (getImageResourceRightBtn() == 0) {
//            return null;
//        }
//
//        ImageView imageView = (ImageView) inflater.inflate(
//                R.layout.toolbar_item_button,
//                viewGroup,
//                false
//        );
//        imageView.setImageResource(
//                getImageResourceRightBtn()
//        );
//        return imageView;
//    }
//
//    @Override
//    public void onLeftBtnClicked(View view) {
//        if (clickListenerLeftBtn != null) {
//            clickListenerLeftBtn.onClick(view);
//        }
//    }
//
//    @Override
//    public void onRightBtnClicked(View view) {
//        if (clickListenerRightBtn != null) {
//            clickListenerRightBtn.onClick(view);
//        }
//    }
//
//    @Override
//    public ToolbarStandard instantiate() {
//        return new ToolbarStandard();
//    }
//}
