package biz.ideus.ideuslibexample.data.remote.socket_chat.socket_request_model;

import com.google.gson.Gson;

/**
 * Created by blackmamba on 24.01.17.
 */

public class SocketRequestBuilder {

    public String command;

    public RequestSocketParams requestSocketParams;

    private SocketRequest socketRequest;

    public SocketRequest getSocketRequest(){
        return socketRequest;
    }

    public SocketRequestBuilder(RequestSocketParams requestSocketParams) {
        this.requestSocketParams = requestSocketParams;
        this.command = requestSocketParams.getCommand();
    }

    public String createJsonRequest(){
        socketRequest = new SocketRequest();
        socketRequest.setCommand(this.command);
        socketRequest.setRequestSocketParams(this.requestSocketParams);
        return createJson(socketRequest);
    }

    public String createJson(SocketRequest socketRequest){
        String json =  new Gson().toJson(socketRequest);
        return json;
    }
}
