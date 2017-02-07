package biz.ideus.ideuslibexample.data.remote.socket.socket_request_model;

/**
 * Created by blackmamba on 24.01.17.
 */

public abstract class RequestSocketParams {

    private transient String command;

    public abstract String getCommand();

    public void setCommand(String command) {
        this.command = command;
    }


}