package biz.ideus.ideuslibexample.data.local;


import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import io.requery.query.Result;
import rx.Observable;

/**
 * Created by blackmamba on 10.02.17.
 */

public interface IBoardRequeryApi {
    Observable<BoardEntity> storeBoard(BoardEntity boardEntity);
    Observable<Iterable<BoardEntity>> storeBoardList(Iterable<BoardEntity> boardEntityList);
    Observable<BoardEntity> getBoardById(String boardId);
    Observable<Result<BoardEntity>> getBoardList();
}
