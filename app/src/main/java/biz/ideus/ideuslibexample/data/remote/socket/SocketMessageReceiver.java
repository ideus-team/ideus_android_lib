package biz.ideus.ideuslibexample.data.remote.socket;

import biz.ideus.ideuslibexample.rx_buses.RxChatMessageEvent;

import static biz.ideus.ideuslibexample.data.remote.socket.SocketCommand.MESSAGE_SENT;

/**
 * Created by blackmamba on 25.01.17.
 */

public class SocketMessageReceiver {

    public void dispatchRxBus(Object response, String command) {
        switch (SocketCommand.getSocketCommandByValue(command)) {
            case AUTORISE:
                break;
            case MESSAGE:
                break;
            case MESSAGE_SENT:
                RxChatMessageEvent.instanceOf().setRxChatMessageEvent(new SocketMessageWrapper(response, MESSAGE_SENT));
                break;
            case RECEIVE_MESSAGE:
                break;
            case DEL_CHAT_ROOM:
                break;
            case ADD_USER_TO_CHAT:
                break;
            case USERS_ADDED:
                break;
            case CREATE_CHAT_ROOM:
                break;
            case ROOM_CREATED:
                break;
            case DEL_USER_FROM_CHAT:
                break;
            case USERS_DELETED:
                break;
        }
    }
    public class SocketMessageWrapper{
        private Object response;
        private SocketCommand socketCommand;

        public Object getResponse() {
            return response;
        }

        public SocketCommand getSocketCommand() {
            return socketCommand;
        }

        public SocketMessageWrapper(Object response, SocketCommand socketCommand){
            this.response = response;
            this.socketCommand = socketCommand;
        }
    }
}
