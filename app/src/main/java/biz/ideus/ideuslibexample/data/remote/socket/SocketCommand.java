package biz.ideus.ideuslibexample.data.remote.socket;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketAuthorisedResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketBaseResponse;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.SocketMessageResponse;

/**
 * Created by blackmamba on 25.01.17.
 */

public enum SocketCommand {

    MESSAGE("message", SocketBaseResponse.class)
    , AUTHORISE("authorize", SocketAuthorisedResponse.class)
    , RECEIVE_MESSAGE("receive_message",SocketMessageResponse.class);
//    , DEL_CHAT_ROOM("del_chat_room")
//    , ADD_USER_TO_CHAT("add_user_to_chat")
//    , USERS_ADDED("users_added")
//    , CREATE_CHAT_ROOM("create_chat_room")
//    , ROOM_CREATED("room_created")
//    , DEL_USER_FROM_CHAT("del_user_from_chat")
//    , USERS_DELETED("users_deleted");

    public String commandName;
     public Class responseType;

    SocketCommand(String commandName, Class responseType)
    {
        this.commandName = commandName;
        this.responseType = responseType;
    }

    public static SocketCommand getSocketCommandByValue(String state){
        for(SocketCommand socketCommand : SocketCommand.values()) {
            if (socketCommand.commandName.equals(state))
                return socketCommand;
        }
        return null;
    }


}
