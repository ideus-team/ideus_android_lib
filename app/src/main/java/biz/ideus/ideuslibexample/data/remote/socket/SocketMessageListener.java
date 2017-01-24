package biz.ideus.ideuslibexample.data.remote.socket;

import okhttp3.Response;

/**
 * Created by blackmamba on 24.01.17.
 */

public interface SocketMessageListener {
    void onMessage(Object response);
    void onFail(Response failResponse);
}
