package biz.ideus.ideuslibexample.data.local;


import java.util.List;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import rx.Observable;

/**
 * Created by blackmamba on 10.02.17.
 */

public interface IBoardRequeryApi {
    Observable<BoardEntity> storeBoard(BoardEntity boardEntity);
    Observable<Iterable<BoardEntity>> storeBoardList(Iterable<BoardEntity> boardEntityList);
    BoardEntity getBoardById(String boardId);
    List<BoardEntity> getBoardList();
}
