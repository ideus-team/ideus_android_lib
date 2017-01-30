package biz.ideus.ideuslibexample.data.remote.socket_chat;

/**
 * Created by blackmamba on 26.01.17.
 */

public class SocketMessageWrapper {
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