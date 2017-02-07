package biz.ideus.ideuslibexample.boarder.ui.main_screen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.remote.socket_chat.WebSocketClient;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;

import static biz.ideus.ideuslibexample.utils.Constants.USER_TOKEN;



/**
 * Created by blackmamba on 07.02.17.
 */

public class BoardMainVM extends AbstractViewModelToolbar<BoardMainView> {

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
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
       context = getView().getViewModelBindingConfig().getContext();
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.boards);
    }

}
