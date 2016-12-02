package biz.ideus.ideuslibexample.ui.common.toolbar;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslib.mvvm_lifecycle.IView;

/**
 * Created by user on 02.12.2016.
 */

public abstract class AbstractViewModelToolbar<T extends IView> extends AbstractViewModel<T>
    implements IToolBar{

    @Override
    public boolean isLeftBtnVisible() {
        return true;
    }

    @Override
    public boolean isRightBtnVisible() {
        return false;
    }

}
