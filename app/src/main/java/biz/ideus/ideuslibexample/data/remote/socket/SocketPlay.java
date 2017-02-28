package biz.ideus.ideuslibexample.data.remote.socket;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;

/**
 * Created by user on 28.02.2017.
 */

public interface SocketPlay {
    interface BoardStory {
        void gotData(BoardStoryData data);
        void gotData(SocketAutorisedData data);
        void gotData(StoryData data);
    }

    interface MainScreen {
        void gotData(BoardStoryData data);
        void gotData(SocketAutorisedData data);
        void gotData(StoryData data);
    }
}
