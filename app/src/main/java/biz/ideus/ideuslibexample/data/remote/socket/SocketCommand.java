package biz.ideus.ideuslibexample.data.remote.socket;

/**
 * Created by blackmamba on 25.01.17.
 */

public enum SocketCommand {

    MESSAGE("message")
    ,AUTORISE("authorize")
    , MESSAGE_SENT("message_sent")
    , RECEIVE_MESSAGE("receive_message")
    , DEL_CHAT_ROOM("del_chat_room")
    , ADD_USER_TO_CHAT("add_user_to_chat")
    , USERS_ADDED("users_added")
    , CREATE_CHAT_ROOM("create_chat_room")
    , ROOM_CREATED("room_created")
    , DEL_USER_FROM_CHAT("del_user_from_chat")
    , USERS_DELETED("users_deleted");

     String commandName;

    SocketCommand(String commandName) {
        this.commandName = commandName;
    }

    public static SocketCommand getSocketCommandByValue(String state){
        for(SocketCommand socketCommand : SocketCommand.values()) {
            if (socketCommand.commandName.equals(state))
                return socketCommand;
        }
        return null;
    }


}
