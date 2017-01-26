package biz.ideus.ideuslibexample.data.remote.socket;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAuthorisedResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;
import okhttp3.Response;

/**
 * Created by blackmamba on 24.01.17.
 */

public abstract class SocketMessageListener {

    // input
    void setSocketWrapper(SocketMessageWrapper wrapper) {

        switch (wrapper.getSocketCommand()) {
            case AUTHORISE:
                onMessage((SocketAuthorisedResponse)  wrapper.getResponse());
                break;
            case MESSAGE:
                break;
            case MESSAGE_SENT:
                onMessage((SocketMessageResponse) wrapper.getResponse());
                break;
            case RECEIVE_MESSAGE:
                onMessage((SocketMessageResponse) wrapper.getResponse());
                break;
//            case DEL_CHAT_ROOM:
//                break;
//            case ADD_USER_TO_CHAT:
//                break;
//            case USERS_ADDED:
//                break;
//            case CREATE_CHAT_ROOM:
//                break;
//            case ROOM_CREATED:
//                break;
//            case DEL_USER_FROM_CHAT:
//                break;
//            case USERS_DELETED:
//                break;
        }
    }
    
    public void onMessage(SocketAuthorisedResponse response) {

    };

    public void onMessage(SocketMessageResponse response) {

    };

        void onMessage(SocketBaseResponse response){

        }

    public abstract void onFail(Response failResponse);
}
