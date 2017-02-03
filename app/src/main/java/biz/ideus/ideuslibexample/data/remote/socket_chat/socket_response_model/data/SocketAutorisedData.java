package biz.ideus.ideuslibexample.data.remote.socket_chat.socket_response_model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketAutorisedData {
    @SerializedName("value")
    private boolean isAutorise;

    public boolean isAutorise() {
        return isAutorise;
    }
}
