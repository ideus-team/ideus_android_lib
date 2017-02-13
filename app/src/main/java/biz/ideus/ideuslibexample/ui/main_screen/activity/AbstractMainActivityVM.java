package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslibexample.network.WebSocketClient;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;


/**
 * Created by blackmamba on 24.11.16.
 */
//@PerActivity
public abstract class AbstractMainActivityVM extends AbstractViewModelToolbar<StartView> {

    protected Context context;
    protected static WebSocketClient webSocketClient;

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
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null) {
            webSocketClient.closeWebSocket();
        }

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}


