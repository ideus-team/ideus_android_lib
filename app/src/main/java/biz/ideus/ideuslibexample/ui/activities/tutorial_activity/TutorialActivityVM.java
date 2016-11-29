package biz.ideus.ideuslibexample.ui.activities.tutorial_activity;

/**
 * Created by blackmamba on 23.11.16.
 */
//@PerActivity
public class TutorialActivityVM {}
// extends BaseViewModel<BaseMvvmInterface.View> implements BaseMvvmInterface.TutorialVmListener, TutorialPagerAdapter.OnPagerListener {
//    private Context context;
//    private TutorialPagerAdapter adapter;
//    private final int ABOUT_APP = 0;
//    private final int NETWORK = 1;
//    private final int PROTECTION = 2;
//    private final int CUSTOMISATION = 3;
//
//    private boolean isBtnActive = false;
//    private boolean isRadioBtnWelcomeChecked = true;
//    private boolean isRadioBtnNetworkChecked;
//    private boolean isRadioBtnProtectionChecked;
//    private boolean isRadioBtnCustomChecked;
//
//    @Bindable
//    public boolean isRadioBtnWelcomeChecked() {
//        return isRadioBtnWelcomeChecked;
//    }
//
//    @Bindable
//    public boolean isRadioBtnProtectionChecked() {
//        return isRadioBtnProtectionChecked;
//    }
//
//    @Bindable
//    public boolean isRadioBtnNetworkChecked() {
//        return isRadioBtnNetworkChecked;
//    }
//
//    @Bindable
//    public boolean isRadioBtnCustomChecked() {
//        return isRadioBtnCustomChecked;
//    }
//
//    @Bindable
//    public boolean isBtnActive() {
//        return isBtnActive;
//    }
//
//
//    @Inject
//    public TutorialActivityVM(@AppContext Context context, TutorialPagerAdapter adapter) {
//        this.context = context;
//        this.adapter = adapter;
//        this.adapter.setOnPagerListener(this);
//    }
//
//
//    @Override
//    public void onSkipAllClick(View view) {
//
//    }
//
//    @Override
//    public void onGetStartedClick(View view) {
//        if (isBtnActive) {
//            navigator.get().startActivity(new Intent(((TutorialActivity) view.getContext()), MainActivity.class));
//        }
//    }
//
//    @Override
//    public void selectPage(int position) {
//        switch (position) {
//            case ABOUT_APP:
//                isRadioBtnWelcomeChecked = true;
//                notifyPropertyChanged(BR.radioBtnWelcomeChecked);
//                break;
//            case NETWORK:
//                isRadioBtnNetworkChecked = true;
//                notifyPropertyChanged(BR.radioBtnNetworkChecked);
//                break;
//            case PROTECTION:
//                isRadioBtnProtectionChecked = true;
//                notifyPropertyChanged(BR.radioBtnProtectionChecked);
//                break;
//            case CUSTOMISATION:
//                isRadioBtnCustomChecked = true;
//                isBtnActive = true;
//                notifyPropertyChanged(BR.radioBtnCustomChecked);
//                notifyPropertyChanged(BR.btnActive);
//                break;
//        }
//    }
//}
