package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketMessageData;

/**
 * Created by blackmamba on 30.01.17.
 */

public class MessagesData {
    @SerializedName("messages")
    private List<SocketMessageData> messagesList;
    private List<MessageEntity> messageEntitiesList;

    public List<SocketMessageData> getMessagesList() {
        return messagesList;
    }


    public List<MessageEntity> getMessageEntitiesList() {
        return createMessageEntityList(messagesList);
    }

    private List<MessageEntity> createMessageEntityList(List<SocketMessageData> messagesList) {
        messageEntitiesList = new ArrayList<>();
        for(int i = 0; i < messagesList.size();i++){
            messageEntitiesList.add(messagesList.get(i).getMessageEntity());
        }
        return messageEntitiesList;
    }

}
