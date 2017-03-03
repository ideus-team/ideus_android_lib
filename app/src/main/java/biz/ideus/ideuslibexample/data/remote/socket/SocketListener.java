package biz.ideus.ideuslibexample.data.remote.socket;

import biz.ideus.ideuslibexample.data.remote.socket.socket_response_model.data.SocketAutorisedData;
import biz.ideus.ideuslibexample.network.response.data.BoardStoryData;
import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;
import biz.ideus.ideuslibexample.network.response.data.StoryData;

/**
 * Created by user on 28.02.2017.
 */

public interface SocketListener {
    interface BoardStory {
        void board_created(BoardStoryData data);
        void board_found(BoardStoryData data);
        void board_updated(BoardStoryData data);
    }

    interface MainScreen {
        void authorized(SocketAutorisedData data);
        void boards(GetBoardsListData data);
        void board_list_created(StoryData data);
    }
}
