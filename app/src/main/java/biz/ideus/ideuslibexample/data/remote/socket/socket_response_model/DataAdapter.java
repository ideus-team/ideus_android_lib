package biz.ideus.ideuslibexample.data.remote.socket.socket_response_model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import biz.ideus.ideuslib.Utils.JsonUtils;
import biz.ideus.ideuslibexample.data.remote.socket.AbsWebSocketClient;
import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;

/**
 * Created by user on 28.02.2017.
 */

public class DataAdapter implements JsonDeserializer<SocketCommonResponse> {
    @Override
    public SocketCommonResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = json.getAsJsonObject();
        SocketCommonResponse result = new SocketCommonResponse();
        String command = JsonUtils.getString(root, "command");
        result.setCommandFromServer(command);
        switch (command) {
            case "board_created" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), BoardStoryData.class));
                break;
            case "authorized" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), SocketAutorisedData.class));
                break;
            case "board_list_created" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), StoryData.class));
                break;
            case "board_found" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), BoardStoryData.class));
                break;
            case "board_updated" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), BoardStoryData.class));
                break;
            case "board_list_deleted" :
                break;
            case "boards" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), GetBoardsListData.class));
                break;
            case "board_list_updated" : result.setData(AbsWebSocketClient.gson.fromJson(root.getAsJsonObject("data"), BoardStoryData.class));
                break;

        }
        return result;
    }
}
