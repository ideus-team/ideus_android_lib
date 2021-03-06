package biz.ideus.ideuslibexample.data.remote.network_change;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslib.Utils.NetworkUtil;
import biz.ideus.ideuslib.dialogs.RxBusShowDialog;
import biz.ideus.ideuslibexample.data.DialogStore;
import biz.ideus.ideuslibexample.rx_buses.RxBusNetworkConnected;
import rx.Subscription;

import static biz.ideus.ideuslibexample.utils.Constants.NO_INTERNET_CONNECTION;

/**
 * Created by blackmamba on 30.01.17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (NetworkUtil.isNetworkConnected(context)) {
           Hawk.put(NO_INTERNET_CONNECTION, false);
            RxBusNetworkConnected.getInstance().setNetworkConnected();
        } else if(!NetworkUtil.isNetworkConnected(context) && !(boolean)Hawk.get(NO_INTERNET_CONNECTION)) {
            Hawk.put(NO_INTERNET_CONNECTION, true);
            RxBusShowDialog.instanceOf().setRxBusShowDialog(DialogStore.NO_INTERNET_CONNECTION());
        }
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}