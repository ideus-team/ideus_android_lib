package biz.ideus.ideuslibexample.interfaces;

import android.content.Intent;

/**
 * Created by blackmamba on 24.11.16.
 */

public interface OnActivityResultInterface {
    void onActivityResultCurrentActivity( int requestCode, int resultCode, Intent intent);
}
