package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.BuildConfig;
import biz.ideus.ideuslibexample.boarder.ui.main_screen.activity.BoardActivityMain;
import biz.ideus.ideuslibexample.data.remote.socket_chat.WebSocketClient;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;


/**
 * Created by blackmamba on 24.11.16.
 */
//@PerActivity
public class MainActivityVM extends AbstractViewModel<StartView> {
    private Context context;
    public static WebSocketClient webSocketClient;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (Hawk.contains(USER_TOKEN)) {
            webSocketClient = WebSocketClient.getInstance();
        }

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = getView().getViewModelBindingConfig().getContext();
        if (!Hawk.contains(USER_TOKEN)) {
            goToLoginScreen();
        } else {
            checkCurrentMainActivity();
    }


}

    private void checkCurrentMainActivity(){
        if (BuildConfig.FLAVOR.contentEquals("boarderFlavor")) {
           context.startActivity(new Intent(context, BoardActivityMain.class));
            ((MainActivity) context).finish();
        }

    }
    private void goToLoginScreen() {
            context.startActivity(new Intent(context, StartActivity.class));
        ((MainActivity) context).finish();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(webSocketClient != null){
            webSocketClient.closeWebSocket();
        }

    }
}


