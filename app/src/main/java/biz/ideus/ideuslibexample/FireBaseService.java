package biz.ideus.ideuslibexample;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslibexample.utils.Constants;

/**
 * Created by user on 16.01.2017.
 */

public class FireBaseService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Hawk.put(Constants.PUSH_TOKEN, refreshedToken);
       // PreferencesUtil.setPushToken(refreshedToken);
        Log.d("TAG", "Refreshed token: " + refreshedToken);
    }

}

