package biz.ideus.ideuslibexample.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by user on 11.11.2016.
 */

public class asdfh extends BaseObservable {
    int a;

    @Bindable
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
