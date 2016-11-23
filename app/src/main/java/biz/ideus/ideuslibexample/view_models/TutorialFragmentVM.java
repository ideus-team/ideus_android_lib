package biz.ideus.ideuslibexample.view_models;

import android.content.Context;

import javax.inject.Inject;

import biz.ideus.ideuslib.ui_base.viewmodel.BaseViewModel;
import biz.ideus.ideuslibexample.adapters.TutorialPagerAdapter;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerFragment;
import biz.ideus.ideuslibexample.interfaces.BaseMvvmInterface;

/**
 * Created by blackmamba on 23.11.16.
 */

@PerFragment
public class TutorialFragmentVM extends BaseViewModel<BaseMvvmInterface.View> {
    private Context context;
    private TutorialPagerAdapter adapter;

    private final int ABOUT_APP = 0;
    private final int NETWORK = 1;
    private final int PROTECTION = 2;
    private final int CUSTOMISATION = 3;

//
//    @Bindable
//    public final ObservableField<Integer> image = new ObservableField<>();
//    @Bindable
//    public final ObservableField<String> title = new ObservableField<>();
//    @Bindable
//    public final ObservableField<String> about = new ObservableField<>();

//    public void setAdapter(TutorialPagerAdapter adapter) {
//        this.adapter = adapter;
//    }

    @Inject
    public TutorialFragmentVM(@AppContext Context context) {
        this.context = context;
     //   adapter.setTutorialPagerFragmentListener(this);
    }

//    @Override
//    public void getPosition(int position) {
//switch (position){
//    case ABOUT_APP:
//        title.set(context.getString(R.string.tutorial_title_welcome));
//        about.set(context.getString(R.string.about_welcome));
//        break;
//    case NETWORK:
//        title.set(context.getString(R.string.tutorial_title_network));
//        about.set(context.getString(R.string.about_network));
//        break;
//    case PROTECTION:
//        title.set(context.getString(R.string.tutorial_title_protection));
//        about.set(context.getString(R.string.about_protection));
//        break;
//    case CUSTOMISATION:
//        title.set(context.getString(R.string.tutorial_title_customisation));
//        about.set(context.getString(R.string.about_customisation));
//        break;
//}
   // }


}
